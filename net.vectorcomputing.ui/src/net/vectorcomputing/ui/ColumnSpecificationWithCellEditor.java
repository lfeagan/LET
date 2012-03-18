package net.vectorcomputing.ui;

import org.eclipse.jface.viewers.CellEditor;

public class ColumnSpecificationWithCellEditor extends AbstractColumnSpecification<ColumnSpecificationWithCellEditor> implements IColumnSpecification<ColumnSpecificationWithCellEditor> {

	private final CellEditor cellEditor;
	
	public ColumnSpecificationWithCellEditor(String name, CellEditor cellEditor) {
		super(name);
		this.cellEditor = cellEditor;
	}
	
	public CellEditor getCellEditor() {
		return cellEditor;
	}

}
