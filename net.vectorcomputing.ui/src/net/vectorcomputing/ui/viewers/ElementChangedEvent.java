package net.vectorcomputing.ui.viewers;

public class ElementChangedEvent implements IElementChangedEvent {

	private final Object element;
	private final String property;
	private final Object oldValue;
	private final Object newValue;

	public ElementChangedEvent(Object element, String property, Object newValue) {
		this(element, property, null, newValue);
	}

	public ElementChangedEvent(Object element, String property, Object oldValue, Object newValue) {
		this.element = element;
		this.property = property;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	@Override
	public Object getElement() {
		return element;
	}

	@Override
	public String getProperty() {
		return property;
	}

	@Override
	public Object getOldValue() {
		return oldValue;
	}

	@Override
	public Object getNewValue() {
		return newValue;
	}

}
