package net.vectorcomputing.print.ui.handler;

import java.util.Iterator;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.ui.dialog.DialogUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.hibernate.Session;

public class DeleteMediaHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				if (ss.isEmpty()) {
					return null;
				}
				
				Iterator iter = ss.iterator();

				Session session = null;
				try {
					session = PrintPlugin.getSessionFactory().openSession();
					session.beginTransaction();					
					while (iter.hasNext()) {
						Object element = iter.next();
						session.delete(element);
					}
					session.getTransaction().commit();
					new RefreshMediaViewHandler().equals(event);
				} finally {
					if (session != null) {
						session.close();
					}
				}
			}
		} catch (ExecutionException e) {
			DialogUtil.openError("Execution Error", "Unable to delete media", new Status(IStatus.ERROR, "foo", null, e)); 
		}
		
		return null;
	}
	
}
