package net.vectorcomputing.print.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class InkCartridgeNewWizard extends Wizard implements INewWizard {

	InkCartridgeWizardPage page;
	
	public InkCartridgeNewWizard() {
		setWindowTitle("New Ink Cartridge");
	}

	@Override
	public void addPages() {
		page = new InkCartridgeWizardPage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		try {
			page.saveInkCartridge();
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
