package net.vectorcomputing.print.ui.wizard;

import net.vectorcomputing.print.ui.handler.RefreshCartridgeSpecificationsViewHandler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class CartridgeSpecificationNewWizard extends Wizard implements INewWizard {

	public static final String ID = "net.vectorcomputing.print.ui.wizard.CartridgeSpecification";
	
	CartridgeSpecificationWizardPage page;
	
	public CartridgeSpecificationNewWizard() {
		setWindowTitle("New Ink Cartridge Specification");
	}

	@Override
	public void addPages() {
		page = new CartridgeSpecificationWizardPage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		try {
			page.saveCartridgeSpecification();
			RefreshCartridgeSpecificationsViewHandler refresh = new RefreshCartridgeSpecificationsViewHandler();
			refresh.execute(new ExecutionEvent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

}
