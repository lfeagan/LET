package net.vectorcomputing.print.ui.provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.vectorcomputing.print.accounting.Price;
import net.vectorcomputing.print.accounting.PriceHistory;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class PriceHistoryCellLabelProvider extends CellLabelProvider {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		
		if(element instanceof Price) {
			Price price = (Price) element;
			int index = cell.getColumnIndex();
			switch (index) {
			case 1: // Date
				Calendar date = price.getDate();
				if (date != null) {
					cell.setText(dateFormat.format(date.getTime()));
				}
				break;
			case 2: // Price
				cell.setText(price.getPrice().toString());
				break;
			default:
				break;
			}
		} else if (element instanceof PriceHistory) {
			PriceHistory priceHistory = (PriceHistory) element;
			int index = cell.getColumnIndex();
			switch (index) {
			case 0: // UUID
				cell.setText(priceHistory.getUUID().toString());
				break;
			default:
				break;
			}
		}
	}
}
