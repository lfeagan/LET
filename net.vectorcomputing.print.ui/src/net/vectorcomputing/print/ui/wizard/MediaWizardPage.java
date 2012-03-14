package net.vectorcomputing.print.ui.wizard;

import net.vectorcomputing.print.ui.widget.MediaComposite;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class MediaWizardPage extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public MediaWizardPage() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new FillLayout());
		MediaComposite mediaComposite = new MediaComposite(container, SWT.NONE);
		
		setControl(container);
	}

}
