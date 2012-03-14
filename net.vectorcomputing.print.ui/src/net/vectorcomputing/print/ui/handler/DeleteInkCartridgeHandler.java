package net.vectorcomputing.print.ui.handler;

import net.vectorcomputing.ui.dialog.DialogUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class DeleteInkCartridgeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				for (Object obj : ss.toList()) {
					System.out.println("obj = " + obj.getClass().getSimpleName());
				}
			}
		} catch (ExecutionException e) {
			DialogUtil.openError("Execution Error", "Unable to delete ink cartridge", new Status(IStatus.ERROR, "foo", null, e)); 
		}
		
		return null;
	}
	
}
