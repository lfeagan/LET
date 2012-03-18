package net.vectorcomputing.ui.viewers;

import org.eclipse.core.runtime.ListenerList;

public abstract class AbstractColumnValueIO implements IColumnValueIO {

	protected final ListenerList elementChangedlisteners = new ListenerList();

	@Override
	public void addElementChangedListener(IElementChangedListener elementChangedListener) {
		elementChangedlisteners.add(elementChangedListener);
	}

	@Override
	public void removeElementChangedListener(IElementChangedListener elementChangedListener) {
		elementChangedlisteners.remove(elementChangedListener);
	}

	@Override
	public void clearElementChangedListeners() {
		elementChangedlisteners.clear();
	}

	@Override
	public Object[] getElementChangedListeners() {
		return elementChangedlisteners.getListeners();
	}

	@Override
	public boolean isElementChangedListenersEmpty() {
		return elementChangedlisteners.isEmpty();
	}

	@Override
	public int sizeOfElementChangedListeners() {
		return elementChangedlisteners.size();
	}

	protected void elementChanged(IElementChangedEvent event) {
		for (Object listener : elementChangedlisteners.getListeners()) {
			((IElementChangedListener) listener).elementChanged(event);
		}
	}

}
