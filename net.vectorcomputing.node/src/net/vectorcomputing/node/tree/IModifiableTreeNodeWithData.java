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

public interface IModifiableTreeNodeWithData<D, T extends ModifiableTreeNode<T>>
		extends ModifiableTreeNode<T> {

	/**
	 * Returns the data stored at this tree node.
	 * 
	 * @return the data stored at this tree node
	 */
	public D getData();

	/**
	 * Sets the data stored at this tree node.
	 * 
	 * @param data
	 *            the data to store at this tree node
	 */
	public D setData(D data);

	/**
	 * Creates and adds a new child to this tree node and sets the child's data.
	 * 
	 * @param data
	 *            the data to store in the new child
	 * @return the new tree node that was created to store the data
	 */
	public IModifiableTreeNodeWithData<D, T> addChildWithData(D data);

}
