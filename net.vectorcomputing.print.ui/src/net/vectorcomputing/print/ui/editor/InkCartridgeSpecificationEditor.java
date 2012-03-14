package net.vectorcomputing.print.ui.editor;

import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.widget.InkCartridgeSpecificationComposite;
import net.vectorcomputing.print.ui.widget.PriceHistoryComposite;

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

public class InkCartridgeSpecificationEditor extends EditorPart implements IDirty {

	public static final String ID = "net.vectorcomputing.print.ui.editor.inkCartridgeSpecificationEditor"; //$NON-NLS-1$
	private boolean isDirty;
	private CTabFolder tabFolder;
	private CTabItem cartridgeTab;
	private CTabItem priceHistoryTab;
	private InkCartridgeSpecificationComposite inkCartridgeSpecificationComposite;
	private PriceHistoryComposite priceHistoryComposite;
	
	private InkCartridgeSpecification inkCartridgeSpecification;
	
	public InkCartridgeSpecificationEditor() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		
		HibernateEditorInput hei = (HibernateEditorInput) getEditorInput();
		if (hei.getObject() instanceof InkCartridgeSpecification) {
			inkCartridgeSpecification = (InkCartridgeSpecification) hei.getObject();
		} else {
			throw new PartInitException("Editor input must be an InkCartridgeSpecification (was " + hei.getObject().getClass().getName() + ")");
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
		
		cartridgeTab = new CTabItem(tabFolder, SWT.NONE);
		cartridgeTab.setText("Cartridge");
		inkCartridgeSpecificationComposite = new InkCartridgeSpecificationComposite(tabFolder, SWT.NONE);
		inkCartridgeSpecificationComposite.setInkCartridgeSpecification(inkCartridgeSpecification);
		inkCartridgeSpecificationComposite.setDirty(this);
		
		cartridgeTab.setControl(inkCartridgeSpecificationComposite);
		
		priceHistoryTab = new CTabItem(tabFolder, SWT.NONE);
		priceHistoryTab.setText("Price History");
		priceHistoryComposite = new PriceHistoryComposite(tabFolder, SWT.NONE, inkCartridgeSpecification.getUuid());
		priceHistoryTab.setControl(priceHistoryComposite);
		
		tabFolder.setSelection(0);
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		HibernateEditorInput hei = (HibernateEditorInput) getEditorInput();
		Session session = hei.getSessionFactory().openSession();
		session.beginTransaction();
		inkCartridgeSpecificationComposite.updateInkCartridgeSpecification();
		session.update(inkCartridgeSpecification);
		session.getTransaction().commit();
		session.close();
		
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
	public final void setIsDirty(boolean isDirty) {
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
