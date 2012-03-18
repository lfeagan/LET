package net.vectorcomputing.print.ui.widget;

import java.util.Comparator;

import net.vectorcomputing.print.accounting.Cartridge;
import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.ui.column.CartridgeColumns;
import net.vectorcomputing.print.ui.provider.CartridgeCellLabelProvider;
import net.vectorcomputing.print.ui.provider.CartridgesContentProvider;
import net.vectorcomputing.ui.viewers.IElementChangedListener;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class CartridgesComposite extends Composite {

	private final TableViewer viewer;
	private final Table table;
	private final CartridgeColumns columns;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CartridgesComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		viewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = viewer.getTable();

		columns = new CartridgeColumns(viewer);
		viewer.setContentProvider(new CartridgesContentProvider());
		viewer.setLabelProvider(new CartridgeCellLabelProvider());
		viewer.setInput(new Object());
//		viewer.setComparator(new ViewerComparator(new CartridgeComparator()));
	}
	
	private static final class CartridgeComparator implements Comparator<Cartridge> {
		@Override
		public int compare(Cartridge ink1, Cartridge ink2) {
			return ink1.getName().compareTo(ink2.getName());
		}
	};

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void refresh() {
		viewer.refresh();
	}
	
	public TableViewer getTableViewer() {
		return viewer;
	}

	public Table getTable() {
		return table;
	}
	
	public ISelectionProvider getSelectionProvider() {
		return viewer;
	}
	
	public void setCartridgeSpecification(CartridgeSpecification cartridgeSpecification) {
		viewer.setInput(cartridgeSpecification);
	}

	public void addElementChangedListener(IElementChangedListener elementChangedListener) {
		columns.addElementChangedListener(elementChangedListener);
	}
	
}
