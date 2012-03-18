package net.vectorcomputing.print.ui.column;

import org.eclipse.jface.viewers.ColumnViewer;

public class PriceHistoryColumns extends AbstractColumnViewerColumns {
	
	public PriceHistoryColumns(ColumnViewer viewer) {
		super(viewer);
	}
	
	@Override
	protected final void initializeValues() {
		PriceHistoryColumn[] columns = PriceHistoryColumn.values();
		for (int i=0; i < columns.length; ++i) {
			PriceHistoryColumn column = columns[i];
			values.add(column.newColumnViewerColumn(i));
		}
	}

}
