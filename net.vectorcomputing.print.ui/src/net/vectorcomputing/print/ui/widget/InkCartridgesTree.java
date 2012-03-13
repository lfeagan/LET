package net.vectorcomputing.print.ui.widget;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.ui.provider.InkCartridgeSpecificationCellLabelProvider;
import net.vectorcomputing.print.ui.provider.InkCartridgeSpecificationsContentProvider;
import net.vectorcomputing.ui.ColumnHeaders;
import net.vectorcomputing.ui.ColumnSpecification;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class InkCartridgesTree extends Composite {

	private static final ColumnHeaders columnHeaders = new ColumnHeaders();
	
	static {
		columnHeaders.add(new ColumnSpecification("UUID").setHidden(true).setWidth(250));
		columnHeaders.add(new ColumnSpecification("ID"));
		columnHeaders.add(new ColumnSpecification("Name"));
		columnHeaders.add(new ColumnSpecification("Abbreviation"));
		columnHeaders.add(new ColumnSpecification("Fill Volume (mL)"));
		columnHeaders.add(new ColumnSpecification("Remaining Volume (mL)"));
		columnHeaders.add(new ColumnSpecification("Install Date"));
		columnHeaders.add(new ColumnSpecification("Disposal Date"));
		columnHeaders.add(new ColumnSpecification("Price"));
	}
	
	private final TreeViewer viewer;
	private final Tree tree;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InkCartridgesTree(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		viewer = new TreeViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		tree = viewer.getTree();

		columnHeaders.configureTreeColumns(viewer);
		viewer.setContentProvider(new InkCartridgeSpecificationsContentProvider());
		viewer.setLabelProvider(new InkCartridgeSpecificationCellLabelProvider());
		viewer.setInput(PrintPlugin.getSessionFactory());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
