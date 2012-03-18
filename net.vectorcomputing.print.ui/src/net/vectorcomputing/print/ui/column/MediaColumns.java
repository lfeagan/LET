package net.vectorcomputing.print.ui.column;

import net.vectorcomputing.print.accounting.Media;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;

public class MediaColumns extends AbstractColumnViewerColumns {
	
	public MediaColumns(ColumnViewer viewer) {
		super(viewer);
	}
	
	@Override
	protected final void initializeValues() {
		MediaColumn[] columns = MediaColumn.values();
		for (int i=0; i < columns.length; ++i) {
			MediaColumn column = columns[i];
			values.add(column.newColumnViewerColumn(i));
		}
	}

	public CellLabelProvider buildCellLabelProvider() {
		return new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Object element = cell.getElement();
				if (element instanceof Media) {
					Media media = (Media) element;
					int index = cell.getColumnIndex();
					switch (index){
					case 0: // UUID
						cell.setText(media.getUuid().toString());
						break;
					case 1: // Maker
						cell.setText(media.getMaker());
						break;
					case 2: // Model
						cell.setText(media.getModel());
						break;
					case 3: // Type
						cell.setText(media.getType().name());
						break;
					case 4: // Finish
						cell.setText(media.getFinish().getName());
						break;
					case 5: // Width
						cell.setText(Double.toString(media.getSize().getWidth()));
						break;
					case 6: // Length
						cell.setText(Double.toString(media.getSize().getLength()));
						break;
					case 7: // Quantity
						cell.setText(Integer.toString(media.getQuantity()));
						break;
					case 9: // Price
						cell.setText(media.getPrice().toString());
						break;
					default:
						break;
					}
				}
			}
		};
	}

}
