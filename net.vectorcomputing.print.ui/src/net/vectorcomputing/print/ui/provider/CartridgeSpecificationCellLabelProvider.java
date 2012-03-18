package net.vectorcomputing.print.ui.provider;

import net.vectorcomputing.print.accounting.CartridgeSpecification;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class CartridgeSpecificationCellLabelProvider extends CellLabelProvider {

	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		if (element instanceof CartridgeSpecification){
			CartridgeSpecification spec = (CartridgeSpecification) element;
			int index = cell.getColumnIndex();
			switch (index){
			case 0:
				cell.setText(spec.getUuid().toString());
				break;
			case 1:
				cell.setText(spec.getMaker());
				break;
			case 2:
				cell.setText(spec.getModel());
				break;
			case 3:
				cell.setText(spec.getAbbreviation());
				break;
			case 4:
				cell.setText(Double.toString(spec.getFillVolume()));
				break;
			default:
				break;
			}
		}
	}
}