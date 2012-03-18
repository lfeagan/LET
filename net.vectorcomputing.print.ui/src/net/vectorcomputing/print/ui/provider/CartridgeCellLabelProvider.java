package net.vectorcomputing.print.ui.provider;

import java.util.Calendar;

import net.vectorcomputing.print.accounting.Cartridge;
import net.vectorcomputing.print.preferences.PrintPreferences;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class CartridgeCellLabelProvider extends CellLabelProvider {

	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		if (element instanceof Cartridge){
			Cartridge ink = (Cartridge) element;
			int index = cell.getColumnIndex();
			switch (index){
			case 0: // UUID
				cell.setText(ink.getUuid().toString());
				break;
			case 1: // Maker
				cell.setText(ink.getMaker());
				break;
			case 2: // Model
				cell.setText(ink.getModel());
				break;
			case 3: // Abbreviation
				cell.setText(ink.getAbbreviation());
				break;
			case 4: // Name
				cell.setText(ink.getName());
				break;
			case 5: // Fill Volume
				cell.setText(Double.toString(ink.getFillVolume()));
				break;
			case 6: // Remaining Volume
				cell.setText(Double.toString(ink.getRemainingVolume()));
				break;
			case 7: // Installation Date
				Calendar installation = ink.getInstallDate();
				if (installation != null) {
					cell.setText(PrintPreferences.getDateFormat().format(installation.getTime()));
				}
				break;
			case 8: // Disposal Date
				Calendar disposal = ink.getDisposeDate();
				if (disposal != null) {
					cell.setText(PrintPreferences.getDateFormat().format(disposal.getTime()));
				}
				break;
			case 9: // Price
				cell.setText(ink.getPrice().toString());
				break;
			default:
				break;
			}
		}
	}
}
