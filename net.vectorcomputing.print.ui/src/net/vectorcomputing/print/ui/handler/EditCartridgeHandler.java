package net.vectorcomputing.print.ui.handler;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.Cartridge;
import net.vectorcomputing.print.ui.editor.HibernateEditorInput;
import net.vectorcomputing.print.ui.editor.CartridgeEditor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Opens an editor on an ink cartridge
 *
 */
public class EditCartridgeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {		
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();

		HibernateEditorInput input = new HibernateEditorInput(PrintPlugin.getSessionFactory(), getSelectedCartridge(event));
		try {
			page.openEditor(input, CartridgeEditor.ID, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Cartridge getSelectedCartridge(ExecutionEvent event) {
		try {
			ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				for (Object obj : ss.toList()) {
					if (obj instanceof Cartridge) {
						return (Cartridge) obj;
					}
				}
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
