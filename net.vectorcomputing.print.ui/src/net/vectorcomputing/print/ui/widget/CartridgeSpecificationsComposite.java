package net.vectorcomputing.print.ui.widget;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.ui.column.CartridgeSpecificationColumns;
import net.vectorcomputing.print.ui.provider.CartridgeSpecificationCellLabelProvider;
import net.vectorcomputing.print.ui.provider.CartridgeSpecificationsContentProvider;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class CartridgeSpecificationsComposite extends Composite {
	
	private final TableViewer viewer;
	private final Table table;
	private final CartridgeSpecificationColumns columns;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CartridgeSpecificationsComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		
		viewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION | style);
		columns = new CartridgeSpecificationColumns(viewer);
		table = viewer.getTable();
		viewer.setContentProvider(new CartridgeSpecificationsContentProvider());
		viewer.setLabelProvider(new CartridgeSpecificationCellLabelProvider());
		viewer.setInput(new Object());
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public CartridgeSpecification getSelection() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		return (CartridgeSpecification) selection.getFirstElement();
	}
	
	public Set<CartridgeSpecification> getSelections() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 0) {
			return Collections.emptySet();
		}
		
		Set<CartridgeSpecification> set = new HashSet<CartridgeSpecification>();
		for (Object element : selection.toList()) {
			set.add((CartridgeSpecification) element);
		}
		return set;
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
	
}
