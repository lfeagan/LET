package net.vectorcomputing.print.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class InkCartridgeSpecificationNewWizard extends Wizard implements INewWizard {

	InkCartridgeSpecificationWizardPage page;
	
	public InkCartridgeSpecificationNewWizard() {
		setWindowTitle("New Ink Cartridge Specification");
	}

	@Override
	public void addPages() {
		page = new InkCartridgeSpecificationWizardPage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		try {
			page.saveInkCartridgeSpecification();
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
