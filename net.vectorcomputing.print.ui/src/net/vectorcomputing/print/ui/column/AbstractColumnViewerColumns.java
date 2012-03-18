package net.vectorcomputing.print.ui.column;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import net.vectorcomputing.ui.ColumnHeaders;
import net.vectorcomputing.ui.IColumnSpecification;
import net.vectorcomputing.ui.viewers.AbstractCellModifier;
import net.vectorcomputing.ui.viewers.ColumnViewerColumnUtils;
import net.vectorcomputing.ui.viewers.IColumnValueIO;
import net.vectorcomputing.ui.viewers.IColumnViewerColumn;
import net.vectorcomputing.ui.viewers.IElementChangedListener;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

public abstract class AbstractColumnViewerColumns {

	protected final List<IColumnViewerColumn> values = new ArrayList<IColumnViewerColumn>(10);
	private final TreeMap<Integer,IColumnViewerColumn> map = new TreeMap<Integer,IColumnViewerColumn>();
	private final ColumnHeaders<IColumnSpecification> columnHeaders = new ColumnHeaders<IColumnSpecification>();

	public List<IColumnViewerColumn> values() {
		return values;
	}

	protected AbstractColumnViewerColumns(ColumnViewer viewer) {
		initializeValues();
		initializeMap();
		configureViewer(viewer);
	}
	
	protected abstract void initializeValues();
	
	protected void initializeMap() {
		for (IColumnViewerColumn column : values()) {
			map.put(column.getIndex(), column);
			columnHeaders.add(column.getColumnSpecification());
		}
	}

	protected void configureViewer(ColumnViewer viewer) {
		viewer.setColumnProperties(getColumnProperties());
		viewer.setCellModifier(new CartridgeColumns.CellModifier(viewer));
		if (viewer instanceof TableViewer) {
			TableViewer tableViewer = (TableViewer) viewer;
			Table table = tableViewer.getTable();
			getColumnHeaders().configureTableColumns(tableViewer);
			viewer.setCellEditors(getCellEditors(table));
		} else if (viewer instanceof TreeViewer) {
			TreeViewer treeViewer = (TreeViewer) viewer;
			Tree tree = treeViewer.getTree();
			getColumnHeaders().configureTreeColumns(treeViewer);
			viewer.setCellEditors(getCellEditors(tree));
		} else {
			throw new RuntimeException("Specified viewer is not a table or a tree viewer");
		}
	}
	
	protected class CellModifier extends AbstractCellModifier<IColumnViewerColumn> implements ICellModifier {
		public CellModifier(ColumnViewer viewer) {
			super(viewer);
		}
		
		@Override
		public IColumnViewerColumn getColumn(String property) {
			return ColumnViewerColumnUtils.getProperty(values(), property);
		}
	}

	public String[] getColumnProperties() {
		return ColumnViewerColumnUtils.getColumnProperties(values());
	}

	public IColumnViewerColumn getProperty(String property) {
		return ColumnViewerColumnUtils.getProperty(values(), property);
	}

	public CellEditor[] getCellEditors(Composite parent) {
		return ColumnViewerColumnUtils.getCellEditors(map, parent);
	}
	
	public ColumnHeaders<IColumnSpecification> getColumnHeaders() {
		return columnHeaders;
	}
	
	public void addElementChangedListener(IElementChangedListener elementChangedListener) {
		for (IColumnViewerColumn column : values()) {
			IColumnValueIO io = column.getValueIO();
			if (io != null) {
				io.addElementChangedListener(elementChangedListener);
			}
		}
	}

	public void removeElementChangedListener(IElementChangedListener elementChangedListener) {
		for (IColumnViewerColumn column : values()) {
			IColumnValueIO io = column.getValueIO();
			if (io != null) {
				io.removeElementChangedListener(elementChangedListener);
			}
		}
	}
	

}
