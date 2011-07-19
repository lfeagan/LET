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

public class FastPostOrderTreeNodeIterator <T extends TreeNode<T>> extends AbstractFastTreeNodeIterator<T> {
	
	public FastPostOrderTreeNodeIterator(final T root) {
		this(root, TreeNodeIterator.NO_DEPTH_LIMIT);
	}

	public FastPostOrderTreeNodeIterator(final T rootNode, final int depthLimit) {
		super(rootNode, depthLimit);
	}

	@Override
	protected void populateNodeList() {
		populateNodeListRecursive(rootNode, 0);
	}

	private void populateNodeListRecursive(final T parent, final int currentDepth) {
		updateMaximumDepthVisited(currentDepth);
		if (parent.hasChildren() && isDepthValid(currentDepth)) {
			for (T child : parent.getChildren()) {
				populateNodeListRecursive(child, currentDepth + 1);
			}
		}
		nodeList.add(parent);
	}
	
}
