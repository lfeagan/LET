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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.TreeNodes;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;

import org.eclipse.core.runtime.Assert;

/**
 * Abstract class providing the bulk of the implementation necessary when
 * creating a fast tree node iterator. Implementors should only implement the
 * {@link AbstractFastTreeNodeIterator#populateNodeList()} method. While
 * traversing the tree as part of this method, implementors should call
 * {@link AbstractFastTreeNodeIterator#updateMaximumDepthVisited(int)}.
 * 
 * A fast node iterator builds a list containing all tree nodes that will be
 * visited as part of construction. While this makes it quite fast at iterating
 * over the tree nodes, it consumes memory proportional to the number of tree
 * nodes being iterated.
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public abstract class AbstractFastTreeNodeIterator<T extends TreeNode<T>> implements TreeNodeIterator<T> {

	/**
	 * The tree node to treat as though it were the root of the tree during this
	 * iteration.
	 */
	protected final T rootNode;

	/**
	 * The depth to limit iteration to.
	 */
	protected final int depthLimit;

	/**
	 * The list of tree nodes to be iterated.
	 */
	protected final ArrayList<T> nodeList = new ArrayList<T>();

	/**
	 * The iterator over the list of tree nodes.
	 */
	protected final Iterator<T> nodeListIterator;

	/**
	 * The tree node presently being visited.
	 */
	private T currentNode;

	/**
	 * The maximum depth visited while building the list of tree nodes to be
	 * iterated.
	 */
	private int maximumDepthVisited = -1;

	/**
	 * Constructor for a fast tree node iterator with no depth limit.
	 * 
	 * @param rootNode
	 *            the effective root of the tree for this iterator
	 */
	protected AbstractFastTreeNodeIterator(final T rootNode) {
		this(rootNode, TreeNodeIterator.NO_DEPTH_LIMIT);
	}

	/**
	 * Constructor for a fast tree node iterator with a depth limit.
	 * 
	 * @param rootNode
	 *            the effective root of the tree for this iterator
	 * @param depthLimit
	 *            The maximum depth to iterate to.
	 */
	protected AbstractFastTreeNodeIterator(final T rootNode, final int depthLimit) {
		Assert.isNotNull(rootNode, "rootNode"); //$NON-NLS-1$

		this.rootNode = rootNode;
		this.depthLimit = depthLimit;
		this.currentNode = null;
		populateNodeList();
		this.nodeListIterator = nodeList.iterator();
	}

	/**
	 * Populates the list of tree nodes with all nodes that are to be iterated.
	 */
	protected abstract void populateNodeList();

	@Override
	public final int depth() {
		return TreeNodes.getDistanceToParent(currentNode, rootNode);
	}

	/**
	 * Returns the maximum depth this iterator visited.
	 * 
	 * @return the maximum depth this iterator visited
	 */
	public int getMaximumDepthVisited() {
		return maximumDepthVisited;
	}

	/**
	 * Returns an unmodifiable list containing the tree nodes iterated by a fast
	 * node iterator.
	 * 
	 * @return an unmodifiable list containing the tree nodes iterated by a fast
	 *         node iterator
	 */
	public final List<T> getNodeList() {
		return Collections.unmodifiableList(nodeList);
	}

	@Override
	public final boolean hasNext() {
		return nodeListIterator.hasNext();
	}

	/**
	 * Determines if the specified depth satisfies the depth limit constraints.
	 * 
	 * @return <code>true</code> if depth satisfies the depth limit constraints
	 */
	protected final boolean isDepthValid(final int depth) {
		return depth <= depthLimit || depthLimit == TreeNodeIterator.NO_DEPTH_LIMIT;
	}

	@Override
	public final T next() {
		currentNode = nodeListIterator.next();
		return currentNode;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	protected boolean updateMaximumDepthVisited(final int currentDepth) {
		if (currentDepth > maximumDepthVisited) {
			maximumDepthVisited = currentDepth;
			return true;
		}
		return false;
	}

}
