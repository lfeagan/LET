package net.vectorcomputing.print.ui.editor;

import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.ui.widget.CartridgeSpecificationComposite;
import net.vectorcomputing.print.ui.widget.CartridgesComposite;
import net.vectorcomputing.print.ui.widget.PriceHistoryComposite;
import net.vectorcomputing.ui.viewers.IElementChangedEvent;
import net.vectorcomputing.ui.viewers.IElementChangedListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.hibernate.Session;

public class CartridgeSpecificationEditor extends EditorPart implements IElementChangedListener {

	public static final String ID = "net.vectorcomputing.print.ui.editor.CartridgeSpecificationEditor"; //$NON-NLS-1$
	private boolean isDirty;
	private CTabFolder tabFolder;
	private CTabItem specificationTab;
	private CTabItem cartridgesTab;
	private CTabItem priceHistoryTab;
	private CartridgeSpecificationComposite cartridgeSpecificationComposite;
	private CartridgesComposite cartridgesComposite;
	private PriceHistoryComposite priceHistoryComposite;
	
	private CartridgeSpecification cartridgeSpecification;
	
	public CartridgeSpecificationEditor() {
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		
		HibernateEditorInput hei = (HibernateEditorInput) getEditorInput();
		if (hei.getObject() instanceof CartridgeSpecification) {
			cartridgeSpecification = (CartridgeSpecification) hei.getObject();
		} else {
			throw new PartInitException("Editor input must be an CartridgeSpecification (was " + hei.getObject().getClass().getName() + ")");
		}		
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tabFolder = new CTabFolder(container, SWT.BORDER);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		specificationTab = new CTabItem(tabFolder, SWT.NONE);
		specificationTab.setText("Specification");
		cartridgeSpecificationComposite = new CartridgeSpecificationComposite(tabFolder, SWT.NONE);
		cartridgeSpecificationComposite.setCartridgeSpecification(cartridgeSpecification);
		cartridgeSpecificationComposite.addElementChangedListener(this);
		specificationTab.setControl(cartridgeSpecificationComposite);
		
		cartridgesTab = new CTabItem(tabFolder, SWT.NONE);
		cartridgesTab.setText("Cartridges");
		cartridgesComposite = new CartridgesComposite(tabFolder, SWT.NONE);
		cartridgesComposite.setCartridgeSpecification(cartridgeSpecification);
		cartridgesComposite.addElementChangedListener(this);
		cartridgesTab.setControl(cartridgesComposite);
		
		priceHistoryTab = new CTabItem(tabFolder, SWT.NONE);
		priceHistoryTab.setText("Price History");
		priceHistoryComposite = new PriceHistoryComposite(tabFolder, SWT.NONE, cartridgeSpecification);
		priceHistoryComposite.addElementChangedListener(this);
		priceHistoryTab.setControl(priceHistoryComposite);
		
		tabFolder.setSelection(0);
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		HibernateEditorInput hei = (HibernateEditorInput) getEditorInput();
		Session session = hei.getSessionFactory().openSession();
		session.beginTransaction();
		cartridgeSpecificationComposite.updateCartridgeSpecification();
		session.update(cartridgeSpecification);
		session.getTransaction().commit();
		session.close();
		
		priceHistoryComposite.save();
		
		setIsDirty(false);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void doSaveAs() {
		doSave(null);
	}

	@Override
	public final void elementChanged(IElementChangedEvent event) {
		setIsDirty(true);
	}
	
	private void setIsDirty(boolean isDirty) {
		if (this.isDirty != isDirty) {
			this.isDirty = isDirty;
			firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
		}		
	}

	@Override
	public boolean isDirty() {
		return isDirty;
	}
	
	@Override
	public void setFocus() {
		// Set the focus
	}

}
