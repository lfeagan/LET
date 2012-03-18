package net.vectorcomputing.ui.viewers;

import net.vectorcomputing.ui.IColumnSpecification;

import org.eclipse.jface.viewers.CellEditor;

public interface IColumnViewerColumn {

	public abstract int getIndex();

	public abstract String getProperty();

	public abstract IColumnSpecification getColumnSpecification();

	public abstract CellEditor getCellEditor();
	
	public abstract IColumnValueIO getValueIO();
	
}