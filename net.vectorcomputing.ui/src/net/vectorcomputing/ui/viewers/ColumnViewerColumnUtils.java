package net.vectorcomputing.ui.viewers;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

public class ColumnViewerColumnUtils {

	public static <T extends IColumnViewerColumn> T getProperty(T[] values, String property) {
		for (T value : values) {
			if (value.getProperty().equals(property)) {
				return value;
			}
		}
		return null;
	}

	public static <T extends IColumnViewerColumn> T getProperty(List<T> values, String property) {
		for (T value : values) {
			if (value.getProperty().equals(property)) {
				return value;
			}
		}
		return null;
	}

	public static <T extends IColumnViewerColumn> CellEditor[] getCellEditors(Map<Integer,T> map, Composite parent) {
		try {
			final int size = map.size();
			final CellEditor[] editors = new CellEditor[size];
			for (int i=0; i < size; ++i) {
				IColumnViewerColumn icscolumn = map.get(i);
				editors[i] = icscolumn.getCellEditor();
				if (editors[i] != null) {
					editors[i].create(parent);
				} else {
					editors[i] = new TextCellEditor(parent);
				}
			}
			return editors;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static <T extends IColumnViewerColumn> String[] getColumnProperties(T[] values) {
		final int size = values.length;
		final String[] properties = new String[size];
		for (int i=0; i < size; ++i) {
			properties[i] = values[i].getProperty();
		}
		return properties;
	}

	public static <T extends IColumnViewerColumn> String[] getColumnProperties(List<T> values) {
		final int size = values.size();
		final String[] properties = new String[size];
		for (int i=0; i < size; ++i) {
			properties[i] = values.get(i).getProperty();
		}
		return properties;
	}

	
//	public static void configureViewer(ColumnViewer viewer) {
//		viewer.setColumnProperties(CartridgeColumn.getColumnProperties());
//		viewer.setCellModifier(new CartridgeColumn.CellModifier(viewer));
//		if (viewer instanceof TableViewer) {
//			TableViewer tableViewer = (TableViewer) viewer;
//			Table table = tableViewer.getTable();
//			getColumnHeaders().configureTableColumns(tableViewer);
//			viewer.setCellEditors(CartridgeColumn.getCellEditors(table));
//		} else if (viewer instanceof TreeViewer) {
//			TreeViewer treeViewer = (TreeViewer) viewer;
//			Tree tree = treeViewer.getTree();
//			getColumnHeaders().configureTreeColumns(treeViewer);
//			viewer.setCellEditors(CartridgeColumn.getCellEditors(tree));
//		} else {
//			throw new RuntimeException("Specified viewer is not a table or a tree viewer");
//		}
//	}

	
}
