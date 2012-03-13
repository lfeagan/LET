package net.vectorcomputing.print.ui.wizard;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.widget.InkCartridgeSpecificationComposite;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.hibernate.Session;

public class InkCartridgeSpecificationWizardPage extends WizardPage {

	private InkCartridgeSpecificationComposite inkCartridgeSpecificationComposite;
	
	/**
	 * Create the wizard.
	 */
	public InkCartridgeSpecificationWizardPage() {
		super("inkCartridgeSpecificationPage");
		setTitle("Ink Cartridge Specification");
		setDescription("Specifies the characteristics of an ink cartridge");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		inkCartridgeSpecificationComposite = new InkCartridgeSpecificationComposite(container, SWT.NONE);
	}
	
	public void saveInkCartridgeSpecification() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		InkCartridgeSpecification ics = inkCartridgeSpecificationComposite.build();
		session.save(ics);
		session.getTransaction().commit();
		session.close();
	}
}
