package net.vectorcomputing.print.ui.provider;

import java.util.Calendar;

import net.vectorcomputing.print.accounting.Price;
import net.vectorcomputing.print.preferences.PrintPreferences;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class PricesTableLabelProvider implements ITableLabelProvider {
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof Price) {
			Price price = (Price) element;
			switch (columnIndex) {
			case 0: // Date
				Calendar date = price.getDate();
				if (date != null) {
					return PrintPreferences.getDateFormat().format(date.getTime());
				}
				break;
			case 1: // Price
				return price.getPrice().toString();
			default:
				break;
			}
		}
		return null;
	}

}
