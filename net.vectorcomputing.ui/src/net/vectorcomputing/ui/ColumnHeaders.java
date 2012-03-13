package net.vectorcomputing.ui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

public class ColumnHeaders {

	private final List<ColumnSpecification> specs = new ArrayList<ColumnSpecification>();

	public void add(ColumnSpecification spec) {
		specs.add(spec);
	}

	public void addAll(Collection<ColumnSpecification> specs) {
		this.specs.addAll(specs);
	}

	public void addAll(ColumnSpecification[] specs) {
		for (ColumnSpecification spec : specs) {
			add(spec);
		}
	}

	public void configureTreeColumns(TreeViewer treeViewer) {
		for (ColumnSpecification spec : specs) {
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE);
			column.getColumn().setText(spec.getName());
			column.getColumn().setResizable(spec.isResizable());
			column.getColumn().setMoveable(spec.isMovable());
			column.getColumn().setWidth(spec.getWidth());

			if (spec.isHidden()) {
				column.getColumn().setWidth(0);
				column.getColumn().setResizable(false);
			}

		}

		Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	}

	public void reconfigureTreeColumns(TreeViewer treeViewer) {
		for (TreeColumn column : treeViewer.getTree().getColumns()) {
			// Consider using the getData(String key) capability for finding the
			// right mapping between columns and specifications
			ColumnSpecification spec = getColumnSpecification(column.getText());
			if (spec != null) {
				column.setText(spec.getName());
				column.setResizable(spec.isResizable());
				column.setMoveable(spec.isMovable());
				column.setWidth(spec.getWidth());

				if (spec.isHidden()) {
					column.setWidth(0);
					column.setResizable(false);
				}
			}
		}

	}

	public ColumnSpecification getColumnSpecification(String name) {
		for (ColumnSpecification spec : specs) {
			if (spec.getName().equals(name)) {
				return spec;
			}
		}
		return null;
	}

	public void configureTableColumns(TableViewer tableViewer) {
		for (ColumnSpecification spec : specs) {
			TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
			column.getColumn().setText(spec.getName());
			column.getColumn().setResizable(spec.isResizable());
			column.getColumn().setMoveable(spec.isMovable());
			column.getColumn().setWidth(spec.getWidth());
		}

		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	public void reconfigureTableColumns(TableViewer tableViewer) {
		for (TableColumn column : tableViewer.getTable().getColumns()) {
			ColumnSpecification spec = getColumnSpecification(column.getText());
			if (spec != null) {
				column.setText(spec.getName());
				column.setResizable(spec.isResizable());
				column.setMoveable(spec.isMovable());
				column.setWidth(spec.getWidth());

				if (spec.isHidden()) {
					column.setWidth(0);
					column.setResizable(false);
				}
			}
		}
	}

}
