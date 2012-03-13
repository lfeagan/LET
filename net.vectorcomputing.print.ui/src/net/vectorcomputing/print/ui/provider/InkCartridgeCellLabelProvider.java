package net.vectorcomputing.print.ui.provider;

import java.util.Calendar;

import net.vectorcomputing.print.accounting.InkCartridge;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class InkCartridgeCellLabelProvider extends CellLabelProvider {

	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		if (element instanceof InkCartridge){
			InkCartridge ink = (InkCartridge) element;
			int index = cell.getColumnIndex();
			switch (index){
			case 0: // UUID
				cell.setText(ink.getUUID().toString());
				break;
			case 1: // ID
				cell.setText(ink.getId());
				break;
			case 2: // Name
				cell.setText(ink.getName());
				break;
			case 3: // Abbreviation
				cell.setText(ink.getAbbreviation());
				break;
			case 4: // Fill Volume
				cell.setText(Double.toString(ink.getFillVolume()));
				break;
			case 5: // Remaining Volume
				cell.setText(Double.toString(ink.getRemainingVolume()));
				break;
			case 6: // Installation Date
				Calendar installation = ink.getInstallDate();
				if (installation != null) {
					cell.setText(installation.toString());
				}
				break;
			case 7: // Disposal Date
				Calendar disposal = ink.getDisposeDate();
				if (disposal != null) {
					cell.setText(disposal.toString());
				}
				break;
			case 8: // Price
				cell.setText("1.23");
				break;
			default:
				break;
			}
		}
	}
}
