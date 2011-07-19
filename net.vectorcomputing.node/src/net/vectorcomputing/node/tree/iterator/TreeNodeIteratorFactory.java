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

import net.vectorcomputing.node.tree.TreeNode;

/**
 * Interface for creating a new tree node iterator starting with a specific tree
 * node.
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public interface TreeNodeIteratorFactory<T extends TreeNode<T>> {

	/**
	 * Creates a new node iterator that starts with the specified tree node.
	 * 
	 * @param node
	 *            the tree node to create an iterator on
	 * @return a new tree node iterator
	 */
	public TreeNodeIterator<T> create(T node);

	/**
	 * Sets the maximum depth the iterator is allowed to go to.
	 * 
	 * @param depthLimit
	 *            the maximum depth the iterator is allowed to go to
	 */
	public void setDepthLimit(int depthLimit);

	/**
	 * Unsets the depth limit (no depth limit).
	 */
	public void unsetDepthLimit();

}
