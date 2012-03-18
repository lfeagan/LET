package net.vectorcomputing.ui.viewers;

import org.eclipse.core.runtime.ListenerList;

/**
 * Interface for extract the current value of a property or modifying the value
 * of a property.
 */
public interface IColumnValueIO {

	public Object getValue(Object element);

	public void modify(Object element, Object value);

	/**
	 * Adds a listener to the element changed listener list.
	 * 
	 * @param elementChangedListener
	 *            the element changed listener to add to the list
	 * @see ListenerList#add(Object)
	 */
	public void addElementChangedListener(IElementChangedListener elementChangedListener);

	/**
	 * Removes an element changed listener from the list.
	 * 
	 * @param elementChangedListener
	 *            the element changed listener to remove
	 * @see ListenerList#remove(Object)
	 */
	public void removeElementChangedListener(IElementChangedListener elementChangedListener);

	/**
	 * Removes all element changed listeners.
	 * 
	 * @see ListenerList#clear()
	 */
	public void clearElementChangedListeners();
	
	/**
	 * Returns an array containing the current element changed listeners.
	 * 
	 * @return an array containing the current element changed listeners
	 * @see ListenerList#getListeners()
	 */
	public Object[] getElementChangedListeners();

	/**
	 * Returns <code>true</code> if the element changed listeners list is empty.
	 * 
	 * @return <code>true</code> if the element changed listeners list is empty
	 * @see ListenerList#isEmpty()
	 */
	public boolean isElementChangedListenersEmpty();
	
	/**
	 * Returns the size of the element changed listeners list.
	 * 
	 * @return the size of the element changed listeners list
	 * @see ListenerList#size()
	 */
	public int sizeOfElementChangedListeners();
	
}