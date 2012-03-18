package net.vectorcomputing.ui.viewers;

/**
 * Classes that need to be notified on column changes should implement this
 * interface and add themselves as listeners to the they want to listen to.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @since 2.0
 */
public interface IElementChangedListener {

	/**
	 * Called when there is a change in an element of a viewer this listener is
	 * registered with.
	 * 
	 * @param event
	 *            a change event that describes the kind of the element change
	 */
	public void elementChanged(IElementChangedEvent event);

}
