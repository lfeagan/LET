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

/**
 * A pre-order (depth first) iterator that builds a list of all tree nodes to be
 * traversed during construction and then simply iterates that list. Consumes
 * memory proportional to the number of tree nodes.
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public class FastPreOrderTreeNodeIterator<T extends TreeNode<T>> extends AbstractFastTreeNodeIterator<T> {
	
	public FastPreOrderTreeNodeIterator(final T root) {
		this(root, TreeNodeIterator.NO_DEPTH_LIMIT);
	}

	public FastPreOrderTreeNodeIterator(final T rootNode, final int maxDepth) {
		super(rootNode, maxDepth);
	}

	@Override
	protected void populateNodeList() {
		populateNodeListRecursive(rootNode, 0);
	}

	private void populateNodeListRecursive(final T parent, final int currentDepth) {
		updateMaximumDepthVisited(currentDepth);
		nodeList.add(parent);
		if (parent.hasChildren() && isDepthValid(currentDepth)) {
			for (T child : parent.getChildren()) {
				populateNodeListRecursive(child, currentDepth + 1);
			}
		}
	}
	
}
