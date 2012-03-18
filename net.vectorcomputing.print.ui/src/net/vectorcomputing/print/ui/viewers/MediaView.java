package net.vectorcomputing.print.ui.viewers;

import java.util.UUID;

import net.vectorcomputing.print.ui.widget.MediasComposite;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

public class MediaView extends ViewPart {

	public static final String ID = "net.vectorcomputing.print.ui.view.MediaView"; //$NON-NLS-1$
	public static final String COMMAND_SEPARATOR_NAME = "commands"; //$NON-NLS-1$

	private MediasComposite mediaTree = null;
	private UUID uuid;
	
	public MediaView() {
		
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		mediaTree = new MediasComposite(container, SWT.NONE);	

		createActions();
		initializeToolBar();
		initializeMenu();
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
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(new Separator(COMMAND_SEPARATOR_NAME));
		toolbarManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
		menuManager.add(new Separator(COMMAND_SEPARATOR_NAME));
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	public void refresh() {
		if (mediaTree != null && !mediaTree.isDisposed()) {
			mediaTree.refresh();
		}
	}
	
}
