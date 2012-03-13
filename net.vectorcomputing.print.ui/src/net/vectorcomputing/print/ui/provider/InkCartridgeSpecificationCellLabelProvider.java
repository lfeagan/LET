package net.vectorcomputing.print.ui.provider;

import net.vectorcomputing.print.accounting.InkCartridgeSpecification;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class InkCartridgeSpecificationCellLabelProvider extends CellLabelProvider {

	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		if (element instanceof InkCartridgeSpecification){
			InkCartridgeSpecification spec = (InkCartridgeSpecification) element;
			int index = cell.getColumnIndex();
			switch (index){
			case 0:
				cell.setText(spec.getUUID().toString());
				break;
			case 1:
				cell.setText(spec.getMaker());
				break;
			case 2:
				cell.setText(spec.getName());
				break;
			case 3:
				cell.setText(Double.toString(spec.getFillVolume()));
				break;
			default:
				break;
			}
		}
	}
}