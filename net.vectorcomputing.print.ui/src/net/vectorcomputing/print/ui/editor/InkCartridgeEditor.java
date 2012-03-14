package net.vectorcomputing.print.ui.editor;

import net.vectorcomputing.print.accounting.InkCartridge;
import net.vectorcomputing.print.ui.widget.InkCartridgeComposite;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class InkCartridgeEditor extends EditorPart {

	public static final String ID = "net.vectorcomputing.print.ui.editor.inkCartridgeEditor"; //$NON-NLS-1$
	private CTabFolder tabFolder;
	private CTabItem cartridgeTab;
	private CTabItem priceHistoryTab;
	private InkCartridgeComposite inkCartridgeComposite;
	private PriceHistoryComposite priceHistoryComposite;
	
	private InkCartridge inkCartridge;
	
	public InkCartridgeEditor() {
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
		inkCartridgeComposite = new InkCartridgeComposite(tabFolder, SWT.NONE);
		inkCartridgeComposite.setInkCartridge(inkCartridge);
		
		cartridgeTab.setControl(inkCartridgeComposite);
		
		priceHistoryTab = new CTabItem(tabFolder, SWT.NONE);
		priceHistoryTab.setText("Price History");
		priceHistoryComposite = new PriceHistoryComposite(tabFolder, SWT.NONE, inkCartridge.getSpecification().getUuid());
		priceHistoryTab.setControl(priceHistoryComposite);
		
		tabFolder.setSelection(0);
	}
	
	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		
		HibernateEditorInput hei = (HibernateEditorInput) getEditorInput();
		inkCartridge = (InkCartridge) hei.getObject();
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
