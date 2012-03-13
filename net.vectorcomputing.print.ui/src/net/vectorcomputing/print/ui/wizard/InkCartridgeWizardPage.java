package net.vectorcomputing.print.ui.wizard;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.InkCartridge;
import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.widget.InkCartridgeComposite;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.hibernate.Session;

public class InkCartridgeWizardPage extends WizardPage {

	private InkCartridgeComposite inkCartridgeComposite;
	
	/**
	 * Create the wizard.
	 */
	public InkCartridgeWizardPage() {
		super("inkCartridgePage");
		setTitle("Ink Cartridge");
		setDescription("Specifies an ink cartridge instance");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		inkCartridgeComposite = new InkCartridgeComposite(container, SWT.NONE);
	}
	
	public void saveInkCartridge() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		InkCartridge ics = inkCartridgeComposite.build();
		session.save(ics);
		session.getTransaction().commit();
		session.close();
	}

}
