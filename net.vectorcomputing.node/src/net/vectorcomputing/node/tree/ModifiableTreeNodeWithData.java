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

import net.vectorcomputing.node.tree.iterator.fast.FastLevelOrderTreeNodeIterator;

/**
 * A tree node that stores data.
 * 
 * @param <D>
 *            the type of data stored at each tree node
 */
public class ModifiableTreeNodeWithData<D> extends
		AbstractModifiableTreeNode<ModifiableTreeNodeWithData<D>> implements
		IModifiableTreeNodeWithData<D, ModifiableTreeNodeWithData<D>> {

	private D data;

	/**
	 * Constructor for a new modifiable tree node with no data.
	 */
	public ModifiableTreeNodeWithData() {
		this.data = null;
	}

	/**
	 * Constructor for a new modifiable tree node with data.
	 * 
	 * @param data
	 *            the data to store at this tree node
	 */
	public ModifiableTreeNodeWithData(D data) {
		this.data = data;
	}

	@Override
	public synchronized D getData() {
		return data;
	}

	@Override
	public synchronized D setData(D data) {
		D previousData = this.data;
		this.data = data;
		return previousData;
	}

	@Override
	public ModifiableTreeNodeWithData<D> addChildWithData(D data) {
		ModifiableTreeNodeWithData<D> newNode = new ModifiableTreeNodeWithData<D>(data);
		addChild(newNode);
		return newNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		final FastLevelOrderTreeNodeIterator<ModifiableTreeNodeWithData<D>> iterator = new FastLevelOrderTreeNodeIterator<ModifiableTreeNodeWithData<D>>(
				this);
		return iterator.getNodeList().toArray(
				(T[]) java.lang.reflect.Array.newInstance(a.getClass()
						.getComponentType(), size()));
	}

}
