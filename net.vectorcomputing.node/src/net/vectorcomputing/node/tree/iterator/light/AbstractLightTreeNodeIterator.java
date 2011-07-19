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
package net.vectorcomputing.node.tree.iterator.light;

import java.util.NoSuchElementException;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.TreeNodeExplorer;
import net.vectorcomputing.node.tree.TreeNodes;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;

import org.eclipse.core.runtime.Assert;

/**
 * Abstract class providing the bulk of the implementation necessary when
 * creating a light tree node iterator. Implementors should only implement the
 * {@link AbstractLightTreeNodeIterator#nextNode()} method.
 * 
 * The more formal name for a light tree node iterator would be an iterative node
 * iterator. Use of the term iterative to describe the incremental fashion in
 * which the next tree node is computed is confusing as this is a sub-class of Java's
 * iterator interface specialised for tree nodes.
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public abstract class AbstractLightTreeNodeIterator <T extends TreeNode<T>> implements TreeNodeIterator<T> {
	
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
	 * The tree node explorer used for moving around the tree.
	 */
	protected final TreeNodeExplorer<T> explorer;
	
	/**
	 * The tree node presently being visited.
	 */
	protected T currentNode;
	
	/**
	 * The tree node that will be visited next.
	 */
	protected T nextNode;
	
	protected boolean DEBUG = false;

	/**
	 * Constructor for a light tree node iterator with no depth limit.
	 * 
	 * @param rootNode
	 *            the effective root of the tree during this iteration
	 */
	protected AbstractLightTreeNodeIterator(final T rootNode) {
		this(rootNode, TreeNodeIterator.NO_DEPTH_LIMIT);
	}

	/**
	 * Constructor for a light tree node iterator with a depth limit.
	 * 
	 * @param rootNode
	 *            the effective root of the tree during this iteration
	 * @param depthLimit
	 *            the maximum depth to iterate to
	 */
	protected AbstractLightTreeNodeIterator(final T rootNode, final int depthLimit) {
		Assert.isNotNull(rootNode, "rootNode"); //$NON-NLS-1$
		
		this.rootNode = rootNode;
		this.depthLimit = depthLimit;
		this.explorer = new TreeNodeExplorer<T>(rootNode);
		this.nextNode = rootNode;
		this.currentNode = null;
	}
	
	@Override
	public final boolean hasNext() {
		return (this.nextNode != null);
	}

	@Override
	public final T next() {
		if (nextNode != null) {
			currentNode = explorer.getCurrentNode();
			nextNode();
			return currentNode;
		} else {
			throw new NoSuchElementException();
		}

	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Updates the current node and next node fields to reflect moving to the
	 * next tree node.
	 */
	protected abstract void nextNode();

	/**
	 * Determines if the current depth of the tree node explorer satisfies the
	 * depth limit constraints.
	 * 
	 * @return <code>true</code> if the explorer's current depth satisfies
	 *         constraints
	 */
	protected final boolean isDepthValid() {
		return isDepthValid(explorer.getDepth());
	}

	/**
	 * Determines if the specified depth satisfies the depth limit constraints.
	 * 
	 * @param depth
	 *            the depth in the tree to evaluate
	 * @return <code>true</code> if the depth satisfies constraints
	 */
	protected final boolean isDepthValid(final int depth) {
		return depthLimit == TreeNodeIterator.NO_DEPTH_LIMIT || depth <= depthLimit;		
	}

	@Override
	public final int depth() {
		return TreeNodes.getDistanceToParent(currentNode, rootNode);
	}
	
	protected void debug(final String message) {
		if (DEBUG) {
			System.out.println(message);
		}
	}
		
}
