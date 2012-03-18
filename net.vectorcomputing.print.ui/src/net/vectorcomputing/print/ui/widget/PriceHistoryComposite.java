package net.vectorcomputing.print.ui.widget;

import java.math.BigDecimal;
import java.util.UUID;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.accounting.PriceHistory;
import net.vectorcomputing.print.ui.column.PriceHistoryColumn;
import net.vectorcomputing.print.ui.column.PriceHistoryColumns;
import net.vectorcomputing.print.ui.provider.PricesContentProvider;
import net.vectorcomputing.print.ui.provider.PricesTableLabelProvider;
import net.vectorcomputing.ui.viewers.ElementChangedEvent;
import net.vectorcomputing.ui.viewers.IElementChangedEvent;
import net.vectorcomputing.ui.viewers.IElementChangedListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.hibernate.Session;

public class PriceHistoryComposite extends Composite implements IElementChangedListener {
	
	private final TableViewer viewer;
	private final Table table;
	private final PriceHistory priceHistory;
	private final PriceHistoryColumns columns;
	private final ListenerList elementChangedlisteners = new ListenerList();

	public PriceHistoryComposite(Composite parent, int style, UUID uuid) {
		this(parent, style, PriceHistory.getPriceHistory(uuid));
	}	
	
	public PriceHistoryComposite(Composite parent, int style, CartridgeSpecification specification) {
		this(parent, style, specification.getPriceHistory());
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PriceHistoryComposite(Composite parent, int style, final PriceHistory priceHistory) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		this.priceHistory = priceHistory;
		
		viewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = viewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		columns = new PriceHistoryColumns(viewer);
		columns.addElementChangedListener(this);
		viewer.setContentProvider(new PricesContentProvider());
		viewer.setLabelProvider(new PricesTableLabelProvider());
		viewer.setInput(priceHistory);
//		viewer.setComparator(new ViewerComparator(PriceHistoryColumn.Date.getComparator()));
		
		Composite actionComposite = new Composite(this, SWT.NONE);
		actionComposite.setLayout(new GridLayout(1, true));
		actionComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		Button button = new Button(actionComposite, SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BigDecimal price = new BigDecimal("0.00");
				priceHistory.addCost(price);
				elementChanged(new ElementChangedEvent(priceHistory, PriceHistoryColumn.Price.getProperty(), price));
				viewer.refresh();
			}
		});
		button.setText("Add");
		
		Button btnNewButton = new Button(actionComposite, SWT.NONE);
		btnNewButton.setText("Delete");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void refresh() {
		viewer.refresh();
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
		
		Menu menu = menuManager.createContextMenu(table);
		table.setMenu(menu);
//		getSite().registerContextMenu(ID, menuManager, viewer);
	}
	
	private void fillContextMenu(IMenuManager menuManager) {
//		menuManager.add(undoAction);
//		menuManager.add(redoAction);
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	public void addElementChangedListener(IElementChangedListener elementChangedListener) {
		elementChangedlisteners.add(elementChangedListener);
	}

	@Override
	public void elementChanged(IElementChangedEvent event) {
		for (Object listener : elementChangedlisteners.getListeners()) {
			((IElementChangedListener) listener).elementChanged(event);
		}
		viewer.refresh();
	}
	
	public void save() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(priceHistory);
		session.getTransaction().commit();
		session.close();
	}
	
}
