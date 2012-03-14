package net.vectorcomputing.print.ui.widget;

import net.vectorcomputing.print.ui.provider.InkCartridgeCellLabelProvider;
import net.vectorcomputing.print.ui.provider.InkCartridgesContentProvider;
import net.vectorcomputing.ui.ColumnHeaders;
import net.vectorcomputing.ui.ColumnSpecification;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class InkCartridgesComposite extends Composite {

	private static final ColumnHeaders columnHeaders = new ColumnHeaders();
	
	static {
		columnHeaders.add(new ColumnSpecification("UUID").setHidden(true).setWidth(250));
		columnHeaders.add(new ColumnSpecification("Maker"));
		columnHeaders.add(new ColumnSpecification("Model"));
		columnHeaders.add(new ColumnSpecification("Abbreviation"));
		columnHeaders.add(new ColumnSpecification("Name"));
		columnHeaders.add(new ColumnSpecification("Fill Volume (mL)"));
		columnHeaders.add(new ColumnSpecification("Remaining Volume (mL)"));
		columnHeaders.add(new ColumnSpecification("Install Date"));
		columnHeaders.add(new ColumnSpecification("Disposal Date"));
		columnHeaders.add(new ColumnSpecification("Price"));
	}
	
	private final TreeViewer treeViewer;
	private final Tree tree;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InkCartridgesComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		treeViewer = new TreeViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		tree = treeViewer.getTree();

		columnHeaders.configureTreeColumns(treeViewer);
		treeViewer.setContentProvider(new InkCartridgesContentProvider());
		treeViewer.setLabelProvider(new InkCartridgeCellLabelProvider());
		treeViewer.setInput(new Object());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void refresh() {
		treeViewer.refresh();
	}
	
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public Tree getTree() {
		return tree;
	}
	
	public ISelectionProvider getSelectionProvider() {
		return treeViewer;
	}
	
}
