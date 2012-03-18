package net.vectorcomputing.print.ui.widget;

import net.vectorcomputing.print.ui.column.MediaColumns;
import net.vectorcomputing.print.ui.provider.MediaCellLabelProvider;
import net.vectorcomputing.print.ui.provider.MediaContentProvider;
import net.vectorcomputing.ui.viewers.IElementChangedListener;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class MediasComposite extends Composite {

	private final TableViewer viewer;
	private final Table table;
	private final MediaColumns columns;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MediasComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		viewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION | style);
		table = viewer.getTable();

		columns = new MediaColumns(viewer);
		viewer.setContentProvider(new MediaContentProvider());
		viewer.setLabelProvider(new MediaCellLabelProvider());
		viewer.setInput(new Object());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void refresh() {
		viewer.refresh();
	}
	
	public TableViewer getTableViewer() {
		return viewer;
	}

	public Table getTable() {
		return table;
	}
	
	public ISelectionProvider getSelectionProvider() {
		return viewer;
	}

	public void addElementChangedListener(IElementChangedListener elementChangedListener) {
		columns.addElementChangedListener(elementChangedListener);
	}
}