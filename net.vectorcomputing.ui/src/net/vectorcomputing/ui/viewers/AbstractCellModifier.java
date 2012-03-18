package net.vectorcomputing.ui.viewers;


import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

public abstract class AbstractCellModifier<T extends IColumnViewerColumn> implements ICellModifier {

	protected final ColumnViewer viewer;

	public AbstractCellModifier(ColumnViewer viewer) {
		this.viewer = viewer;
	}

	public abstract T getColumn(String property);
	
	@Override
	public boolean canModify(Object element, String property) {
		try {
			IColumnViewerColumn column = getColumn(property);
			return (column != null && column.getCellEditor() != null);
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public void modify(Object element, String property, Object value) {
		try {
			TableItem item = (TableItem) element;

			T column = getColumn(property);
			if (column == null) {
				return;
			}
			
			IColumnValueIO io = column.getValueIO();
			if (io != null) {
				io.modify(item.getData(), value);
			}
			
			viewer.update(item.getData(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getValue(Object element, String property) {
		try {
			T column = getColumn(property);
			if (column == null) {
				return "";
			}
			
			IColumnValueIO io = column.getValueIO();
			if (io != null) {
				return io.getValue(element);
			} else {
				return null;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
}