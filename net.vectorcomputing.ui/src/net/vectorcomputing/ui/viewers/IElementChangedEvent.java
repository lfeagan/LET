package net.vectorcomputing.ui.viewers;

/**
 * Element change events are fired by a viewer when it is changed from the last
 * clean state. Element change listeners can use these events to update
 * accordingly.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 2.0
 */
public interface IElementChangedEvent {
	
	/**
	 * Returns the element that changed.
	 * @return
	 */
	public Object getElement();
	
	/**
	 * Returns the property associated with the changed cell in the viewer.
	 * 
	 * @return the property associated with the changed cell in the viewer
	 */
	public String getProperty();
	
	public Object getOldValue();
	
	public Object getNewValue();

}
