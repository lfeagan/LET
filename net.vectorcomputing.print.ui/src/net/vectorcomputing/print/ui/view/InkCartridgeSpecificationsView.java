package net.vectorcomputing.print.ui.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.FillLayout;
import net.vectorcomputing.print.ui.widget.InkCartridgeSpecificationsTree;

public class InkCartridgeSpecificationsView extends ViewPart {

	public static final String ID = "net.vectorcomputing.print.ui.view.InkCartridgeSpecificationsView"; //$NON-NLS-1$

	public InkCartridgeSpecificationsView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		InkCartridgeSpecificationsTree inkCartridgeSpecificationsTable = new InkCartridgeSpecificationsTree(container, SWT.NONE);		
		
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void dispose() {
		super.dispose();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
