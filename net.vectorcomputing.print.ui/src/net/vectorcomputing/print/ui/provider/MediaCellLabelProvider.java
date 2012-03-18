package net.vectorcomputing.print.ui.provider;

import net.vectorcomputing.print.accounting.Media;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class MediaCellLabelProvider extends CellLabelProvider {

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
}
