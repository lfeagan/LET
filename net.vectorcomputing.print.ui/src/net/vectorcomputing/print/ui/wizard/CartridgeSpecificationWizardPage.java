package net.vectorcomputing.print.ui.wizard;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.ui.widget.CartridgeSpecificationComposite;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.hibernate.Session;

public class CartridgeSpecificationWizardPage extends WizardPage {

	private CartridgeSpecificationComposite cartridgeSpecificationComposite;
	
	/**
	 * Create the wizard.
	 */
	public CartridgeSpecificationWizardPage() {
		super("CartridgeSpecificationPage");
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
		
		cartridgeSpecificationComposite = new CartridgeSpecificationComposite(container, SWT.NONE);
	}
	
	public void saveCartridgeSpecification() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		CartridgeSpecification ics = cartridgeSpecificationComposite.getCartridgeSpecification();
		session.save(ics);
		session.getTransaction().commit();
		session.close();
	}
}
