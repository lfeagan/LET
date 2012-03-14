package net.vectorcomputing.print.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class MediaNewWizard extends Wizard implements INewWizard {
	
	public static final String ID = "net.vectorcomputing.print.ui.wizard.media";

	private MediaWizardPage page;

	public MediaNewWizard() {
		setWindowTitle("New Media");
	}

	@Override
	public void addPages() {
		page = new MediaWizardPage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		try {
//			page.saveMedia();
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
