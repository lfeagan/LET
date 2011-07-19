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
package net.vectorcomputing.node.tree.iterator.fast;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;
import net.vectorcomputing.node.tree.iterator.TreeNodeIteratorFactory;

/**
 * A tree node iterator factory that creates a
 * {@link FastPreOrderTreeNodeIterator}.
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public class FastPreOrderTreeNodeIteratorFactory<T extends TreeNode<T>> implements TreeNodeIteratorFactory<T> {
	
	private int depthLimit;
	
	public FastPreOrderTreeNodeIteratorFactory() {
		this.depthLimit = TreeNodeIterator.NO_DEPTH_LIMIT;
	}

	public FastPreOrderTreeNodeIteratorFactory(final int depthLimit) {
		this.depthLimit = depthLimit;
	}
	
	@Override
	public void setDepthLimit(final int maxDepth) {
		this.depthLimit = maxDepth;
	}
	
	@Override
	public void unsetDepthLimit() {
		this.depthLimit = TreeNodeIterator.NO_DEPTH_LIMIT;
	}
	
	@Override
	public TreeNodeIterator<T> create(final T root) {
		if (depthLimit == -1) {
			return new FastPreOrderTreeNodeIterator<T>(root);
		} else {
			return new FastPreOrderTreeNodeIterator<T>(root);
		}
	}

}
