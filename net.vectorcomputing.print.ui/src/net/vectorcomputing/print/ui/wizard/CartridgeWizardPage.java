package net.vectorcomputing.print.ui.wizard;

import java.util.Iterator;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.Cartridge;
import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.ui.widget.CartridgeComposite;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.hibernate.Session;

public class CartridgeWizardPage extends WizardPage {
	
	private CartridgeSpecification specification = null;
	private CartridgeComposite cartridgeComposite;
	
	/**
	 * Create the wizard.
	 */
	public CartridgeWizardPage() {
		this(null);
	}
	
	public CartridgeWizardPage(IStructuredSelection selection) {
		super("CartridgePage");
		setTitle("Ink Cartridge");
		setDescription("Creates a new ink cartridge");
		
		if (!selection.isEmpty()) {
			Iterator<?> iter = selection.iterator();
			while (iter.hasNext()) {
				Object element = iter.next();
				if (element instanceof CartridgeSpecification) {
					specification = (CartridgeSpecification) element;
					break;
				}
			}
		}
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		cartridgeComposite = new CartridgeComposite(container, SWT.NONE);
		if (specification != null) {
			cartridgeComposite.setCartridgeSpecification(specification);
		}
	}
	
	public void saveCartridge() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		Cartridge ics = cartridgeComposite.getCartridge();
		session.save(ics);
		session.getTransaction().commit();
		session.close();
	}

}
