/*******************************************************************************
 * Copyright 2011 Lance Feagan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.vectorcomputing.node.tree.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.vectorcomputing.node.tree.TreeNode;

/**
 * The interface for an iterator over {@link TreeNode}s.
 * 
 * @param <T>
 *            the type of tree node to iterate
 * @see Iterator
 */
public interface TreeNodeIterator<T extends TreeNode<T>> extends Iterator<T> {

	public static final int NO_DEPTH_LIMIT = -1;

	/**
	 * Returns the depth of the current tree node.
	 * 
	 * @return the depth of the current tree node
	 */
	public int depth();

	/**
	 * Returns <code>true</code> if the iteration has more elements. (In other
	 * words, returns true if next would return an element rather than throwing
	 * an exception.)
	 * 
	 * @return <code>true</code> if the iterator has more elements.
	 */
	@Override
	public boolean hasNext();

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element in the iteration.
	 * @throws NoSuchElementException
	 *             iteration has no more elements.
	 */
	@Override
	public T next();

	/**
	 * Removes from the underlying collection the last element returned by the
	 * iterator (optional operation). This method can be called only once per
	 * call to next. The behaviour of an iterator is unspecified if the
	 * underlying collection is modified while the iteration is in progress in
	 * any way other than by calling this method.
	 * 
	 * @throws UnsupportedOperationException
	 *             if the remove operation is not supported by this Iterator.
	 * @throws IllegalStateException
	 *             if the next method has not yet been called, or the remove
	 *             method has already been called after the last call to the
	 *             next method.
	 */
	@Override
	public void remove();

}
