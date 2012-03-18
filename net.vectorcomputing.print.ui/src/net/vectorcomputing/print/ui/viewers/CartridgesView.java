package net.vectorcomputing.print.ui.viewers;

import net.vectorcomputing.print.ui.widget.CartridgesComposite;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;
import org.eclipse.ui.part.ViewPart;

public class CartridgesView extends ViewPart {

	public static final String ID = "net.vectorcomputing.print.ui.view.CartridgesView"; //$NON-NLS-1$
	public static final String COMMAND_SEPARATOR_NAME = "commands"; //$NON-NLS-1$
	
	private CartridgesComposite cartridgesTree = null;
	private IUndoContext undoContext;
	private UndoActionHandler undoAction;
	private RedoActionHandler redoAction;

	public CartridgesView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		cartridgesTree = new CartridgesComposite(container, SWT.NONE);

		this.getSite().setSelectionProvider(cartridgesTree.getSelectionProvider());
		undoContext = new ObjectUndoContext(this);

		createActions();
		createContextMenu();
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
		undoAction = new UndoActionHandler(getSite(), undoContext);
		redoAction = new RedoActionHandler(getSite(), undoContext);
		IActionBars actionBars = getViewSite().getActionBars();
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
		actionBars.updateActionBars();
	}

	private void createContextMenu() {
		MenuManager menuManager = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuManager.setRemoveAllWhenShown(true);

		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(manager);
			}
		});
		
		Table table = cartridgesTree.getTable();
		Menu menu = menuManager.createContextMenu(table);
		table.setMenu(menu);
		getSite().registerContextMenu(ID, menuManager, cartridgesTree.getTableViewer());
	}
	
	private void fillContextMenu(IMenuManager menuManager) {
		menuManager.add(undoAction);
		menuManager.add(redoAction);
		// add separator for commands
		menuManager.add(new Separator(COMMAND_SEPARATOR_NAME));
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
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
		if (cartridgesTree != null && !cartridgesTree.isDisposed()) {
			cartridgesTree.refresh();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IUndoContext.class)) {
			// used by undo/redo actions to get their undo context
			return undoContext;
		} else {
			return super.getAdapter(key);
		}
	}
	
}
