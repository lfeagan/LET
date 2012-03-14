package net.vectorcomputing.print.ui.provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.vectorcomputing.print.accounting.Price;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class PricesTableLabelProvider implements ITableLabelProvider {

	private final SimpleDateFormat dateFormat;

	public PricesTableLabelProvider(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
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
					return dateFormat.format(date.getTime());
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
