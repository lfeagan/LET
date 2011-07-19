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

import java.util.List;

import net.vectorcomputing.node.directed.DirectedNode;

/**
 * Interface for an object that is part of a bi-navigable tree.
 * 
 * @param <T>
 *            the tree node's type
 */
public interface TreeNode<T extends TreeNode<T>> extends DirectedNode<T> {

	/**
	 * Returns the root of this tree of nodes by iterating through parents
	 * of this tree node until a tree node without a parent is found.
	 * 
	 * @return the root of this tree of nodes
	 */
	public T getRoot();

	/**
	 * Determines if this tree node has a parent.
	 * 
	 * @return <code>true</code> if this tree node has a parent
	 */
	public boolean hasParent();

	/**
	 * Returns this tree node's parent.
	 * 
	 * @return this tree node's parent or <code>null</code> if this tree node
	 *         does not have a parent
	 */
	public T getParent();

	/**
	 * Determines if this tree node has children.
	 * 
	 * @return <code>true</code> if this tree node has children
	 */
	public boolean hasChildren();

	/**
	 * Returns an unmodifiable list that contains references to the children of
	 * this tree node.
	 * 
	 * @return an unmodifiable list containing references to the children of
	 *         this tree node
	 */
	public List<? extends T> getChildren();

	/**
	 * Returns the number of children this tree node has.
	 * 
	 * @return the number of children this tree node has
	 */
	public int sizeOfChildren();

	/**
	 * Returns the tree node at the specified index in this tree node's list of
	 * children.
	 * 
	 * @param index
	 *            the offset into this tree node's list of children
	 * @return the child tree node at the specified index
	 * @throws IndexOutOfBoundsException
	 *             if the index is outside the bounds of this tree node's list
	 *             of children
	 */
	public T getChild(final int index);

}
