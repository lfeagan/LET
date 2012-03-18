package net.vectorcomputing.print.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class CartridgeNewWizard extends Wizard implements INewWizard {

	public static final String ID = "net.vectorcomputing.print.ui.wizard.Cartridge";

	private IWorkbench workbench;
	private IStructuredSelection selection;
	private CartridgeWizardPage page;
	
	public CartridgeNewWizard() {
		setWindowTitle("New Ink Cartridge");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}
	
	@Override
	public void addPages() {
		page = new CartridgeWizardPage(selection);
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		try {
			page.saveCartridge();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
