package net.vectorcomputing.print.ui.provider;

import java.util.Collections;
import java.util.UUID;

import net.vectorcomputing.print.accounting.PriceHistory;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PriceHistoryTreeContentProvider implements ITreeContentProvider {

	private static final Object[] EMPTY_ARRAY = Collections.EMPTY_LIST.toArray();

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof UUID) {
			UUID uuid = (UUID) inputElement;
			PriceHistory[] ph = new PriceHistory[1];
			ph[0] = PriceHistory.getPriceHistory(uuid);
			return ph;
		} else {
			// return the price history for all items
			return PriceHistory.getAll().toArray();
		}
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof PriceHistory) {
			PriceHistory ph = (PriceHistory) parentElement;
			return ph.getPrices().toArray();
		} else {
			return EMPTY_ARRAY;
		}
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof PriceHistory) {
			PriceHistory ph = (PriceHistory) element;
			return (ph.getPrices().size() > 0);
		} else {
			return false;
		}
	}

}