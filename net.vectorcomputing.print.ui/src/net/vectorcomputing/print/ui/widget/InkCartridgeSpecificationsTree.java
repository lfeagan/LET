package net.vectorcomputing.print.ui.widget;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.provider.InkCartridgeSpecificationCellLabelProvider;
import net.vectorcomputing.print.ui.provider.InkCartridgeSpecificationsContentProvider;
import net.vectorcomputing.ui.ColumnHeaders;
import net.vectorcomputing.ui.ColumnSpecification;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class InkCartridgeSpecificationsTree extends Composite {
	
	private static final ColumnHeaders columnHeaders = new ColumnHeaders();
	
	static {
		columnHeaders.add(new ColumnSpecification("UUID").setHidden(true).setWidth(250));
		columnHeaders.add(new ColumnSpecification("Maker"));
		columnHeaders.add(new ColumnSpecification("Name"));
		columnHeaders.add(new ColumnSpecification("Fill Volume (mL)"));
	}
	
	private final TreeViewer viewer;
	private final Tree tree;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InkCartridgeSpecificationsTree(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		viewer = new TreeViewer(this, SWT.BORDER | SWT.FULL_SELECTION | style);
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
	
	public InkCartridgeSpecification getSelection() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		return (InkCartridgeSpecification) selection.getFirstElement();
	}
	
	public Set<InkCartridgeSpecification> getSelections() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 0) {
			return Collections.emptySet();
		}
		
		Set<InkCartridgeSpecification> set = new HashSet<InkCartridgeSpecification>();
		for (Object element : selection.toList()) {
			set.add((InkCartridgeSpecification) element);
		}
		return set;
	}
	
}
