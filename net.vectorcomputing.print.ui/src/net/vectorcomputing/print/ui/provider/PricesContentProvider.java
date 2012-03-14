package net.vectorcomputing.print.ui.provider;

import java.util.Collections;
import java.util.UUID;

import net.vectorcomputing.print.accounting.PriceHistory;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PricesContentProvider implements IStructuredContentProvider {

	private static final Object[] EMPTY_ARRAY = Collections.EMPTY_LIST.toArray();

	@Override
	public void dispose() {
		// do nothing
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// do nothing
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof UUID) {
			UUID uuid = (UUID) inputElement;
			PriceHistory ph = PriceHistory.getPriceHistory(uuid);
			return ph.getPrices().toArray();
		} else if (inputElement instanceof PriceHistory) {
			PriceHistory ph = (PriceHistory) inputElement;
			return ph.getPrices().toArray();
		} else {
			return EMPTY_ARRAY;
		}
	}

}
