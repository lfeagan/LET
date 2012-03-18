package net.vectorcomputing.ui.viewers;

import net.vectorcomputing.ui.IColumnSpecification;

import org.eclipse.jface.viewers.CellEditor;

public class ColumnViewerColumn implements IColumnViewerColumn {

	protected final int index;
	protected final IColumnSpecification columnSpecification;
	protected final CellEditor cellEditor;
	protected final IColumnValueIO valueIO;

	public ColumnViewerColumn(int index, IColumnSpecification columnSpecification, CellEditor cellEditor, IColumnValueIO valueIO) {
		this.index = index;
		this.columnSpecification = columnSpecification;
		this.cellEditor = cellEditor;
		this.valueIO = valueIO;
	}
	
	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getProperty() {
		return columnSpecification.getName();
	}

	@Override
	public IColumnSpecification getColumnSpecification() {
		return columnSpecification;
	}

	@Override
	public CellEditor getCellEditor() {
		return cellEditor;
	}

	@Override
	public IColumnValueIO getValueIO() {
		return valueIO;
	}
	
	public void addElementChangedListener(IElementChangedListener elementChangedListener) {
		getValueIO().addElementChangedListener(elementChangedListener);
	}

}