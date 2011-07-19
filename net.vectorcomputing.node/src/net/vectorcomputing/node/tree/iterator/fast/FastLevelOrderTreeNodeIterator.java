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

import java.util.List;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;

/**
 * A tree node iterator that traverses a tree in level-order. For
 * high-performance, a list is populated during construction that contains the
 * entire iteration.
 * 
 * The iterator will return all tree nodes at depth N before any tree node at
 * depth N+1 or greater is returned. The tree nodes within a depth are returned
 * starting with the left-most child.
 * 
 * The fast variant of the level order tree node iterator should be used when:
 * <ol>
 * <li>memory usage is not a concern (perhaps the tree of nodes to be iterated
 * over is small or a large amount of memory is known to be available),</li>
 * <li>high-performance is needed, and</li>
 * <li>most or all of the iteration list will be read.</li>
 * </ol>
 * 
 * @param <T>
 *            the type of tree node to iterate over
 */
public class FastLevelOrderTreeNodeIterator<T extends TreeNode<T>> extends AbstractFastTreeNodeIterator<T> {

	public FastLevelOrderTreeNodeIterator(T root) {
		this(root, TreeNodeIterator.NO_DEPTH_LIMIT);
	}

	/**
	 * Constructor for a level-order tree node iterator that populates a list
	 * containing the entire iteration during construction for fast iteration.
	 * 
	 * @param rootNode
	 *            The tree node to use as the root for the purpose of this
	 *            iteration. Does not necessarily have to be the true root tree
	 *            it is present in.
	 * @param maxDepth
	 *            The maximum depth to iterate to.
	 */
	public FastLevelOrderTreeNodeIterator(final T rootNode, final int maxDepth) {
		super(rootNode, maxDepth);
	}

	@Override
	protected void populateNodeList() {
		nodeList.add(rootNode);
		populateNodeListRecursive(rootNode, 1);
	}

	private void populateNodeListRecursive(final T parent, final int currentDepth) {
		updateMaximumDepthVisited(currentDepth);
		if (parent.hasChildren() && isDepthValid(currentDepth)) {
			final List<? extends T> children = parent.getChildren();
			nodeList.addAll(children);
			for (final T child : children) {
				populateNodeListRecursive(child, currentDepth + 1);
			}
		}
	}

}
