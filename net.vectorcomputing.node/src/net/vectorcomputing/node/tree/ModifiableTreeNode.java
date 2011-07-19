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
package net.vectorcomputing.node.tree;

public interface ModifiableTreeNode<T extends TreeNode<T>> extends TreeNode<T> {

	/**
	 * Sets this tree node's parent.
	 * 
	 * @param parent
	 *            this tree node's new parent
	 * @return this tree node's previous parent or <code>null</code> if this
	 *         tree node did not previously have a parent
	 */
	public T setParent(T parent);

	/**
	 * Detaches this tree node from its parent by removing it from its current
	 * parent's list of children and setting its parent reference to
	 * <code>null</code>.
	 * 
	 * @return this tree node's previous parent
	 */
	public T unsetParent();

	/**
	 * Adds a new tree node to this tree node's list of children. If the child
	 * has a parent, it is removed from its current parent's list of children
	 * and this tree node is set as its parent.
	 * 
	 * @see java.util.List#add(Object)
	 * @param child
	 *            the tree node to add to this tree node's list of children
	 * @return <code>true</code> (as specified by
	 *         {@link java.util.List#add(Object)}
	 * @throws UnsupportedOperationException
	 *             if the <code>add</code> operation is not supported by this
	 *             modifiable tree node
	 * @throws NullPointerException
	 *             if the specified child is null
	 */
	public boolean addChild(T child);

	/**
	 * Removes a child from this tree node's list of children. The child tree
	 * node's parent is unset as specified by {@link #unsetParent()}.
	 * 
	 * @see java.util.List#remove(Object)
	 * @param child
	 *            the child to be removed from this tree node's list of children
	 * @return <code>true</code> if this tree node's list of children contained the
	 *         specified element
	 * @throws NullPointerException
	 *             if the child specified is null
	 * @throws UnsupportedOperationException
	 *             if the <code>removeChild</code> operation is not supported by
	 *             this modifiable tree node
	 */
	public boolean removeChild(T child);

}
