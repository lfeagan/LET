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

public class ColumnHeaders<CS extends IColumnSpecification> {

	private final List<CS> specs = new ArrayList<CS>();

	public void add(CS spec) {
		specs.add(spec);
	}

	public void addAll(Collection<CS> specs) {
		this.specs.addAll(specs);
	}

	public void addAll(CS[] specs) {
		for (CS spec : specs) {
			add(spec);
		}
	}
	
	public boolean isEmpty() {
		return specs.isEmpty();
	}
	
	public List<String> getNames() {
		List<String> names = new ArrayList<String>(specs.size());
		for (IColumnSpecification spec : specs) {
			names.add(spec.getName());
		}
		return names;
	}

	public void configureTreeColumns(TreeViewer treeViewer) {
		for (IColumnSpecification spec : specs) {
			TreeViewerColumn viewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
			TreeColumn treeColumn = viewerColumn.getColumn();
			treeColumn.setText(spec.getName());
			treeColumn.setResizable(spec.isResizable());
			treeColumn.setMoveable(spec.isMovable());
			treeColumn.setWidth(spec.getWidth());

			if (spec.isHidden()) {
				treeColumn.setWidth(0);
				treeColumn.setResizable(false);
			}

		}

		Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	}

	public void reconfigureTreeColumns(TreeViewer treeViewer) {
		for (TreeColumn treeColumn : treeViewer.getTree().getColumns()) {
			// Consider using the getData(String key) capability for finding the
			// right mapping between columns and specifications
			IColumnSpecification spec = getColumnSpecification(treeColumn.getText());
			if (spec != null) {
				treeColumn.setText(spec.getName());
				treeColumn.setResizable(spec.isResizable());
				treeColumn.setMoveable(spec.isMovable());
				treeColumn.setWidth(spec.getWidth());

				if (spec.isHidden()) {
					treeColumn.setWidth(0);
					treeColumn.setResizable(false);
				}
			}
		}

	}

	public IColumnSpecification getColumnSpecification(String name) {
		for (IColumnSpecification spec : specs) {
			if (spec.getName().equals(name)) {
				return spec;
			}
		}
		return null;
	}

	public void configureTableColumns(TableViewer tableViewer) {
		for (IColumnSpecification spec : specs) {
			TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
			TableColumn tableColumn = viewerColumn.getColumn();
			tableColumn.setText(spec.getName());
			tableColumn.setResizable(spec.isResizable());
			tableColumn.setMoveable(spec.isMovable());
			tableColumn.setWidth(spec.getWidth());

			if (spec.isHidden()) {
				tableColumn.setWidth(0);
				tableColumn.setResizable(false);
			}
		}

		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	public void reconfigureTableColumns(TableViewer tableViewer) {
		for (TableColumn tableColumn : tableViewer.getTable().getColumns()) {
			IColumnSpecification spec = getColumnSpecification(tableColumn.getText());
			if (spec != null) {
				tableColumn.setText(spec.getName());
				tableColumn.setResizable(spec.isResizable());
				tableColumn.setMoveable(spec.isMovable());
				tableColumn.setWidth(spec.getWidth());

				if (spec.isHidden()) {
					tableColumn.setWidth(0);
					tableColumn.setResizable(false);
				}
			}
		}
	}

}
