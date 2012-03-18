package net.vectorcomputing.print.ui.column;

import org.eclipse.jface.viewers.ColumnViewer;

public class CartridgeSpecificationColumns extends AbstractColumnViewerColumns {
	
	public CartridgeSpecificationColumns(ColumnViewer viewer) {
		super(viewer);
	}
	
	@Override
	protected final void initializeValues() {
		CartridgeSpecificationColumn[] columns = CartridgeSpecificationColumn.values();
		for (int i=0; i < columns.length; ++i) {
			CartridgeSpecificationColumn column = columns[i];
			values.add(column.newColumnViewerColumn(i));
		}
	}

}