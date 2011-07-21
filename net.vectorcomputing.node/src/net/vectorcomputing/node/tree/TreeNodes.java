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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vectorcomputing.node.NodeMessages;
import net.vectorcomputing.node.NodePlugin;
import net.vectorcomputing.node.tree.constraint.TreeNodeConstraint;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;
import net.vectorcomputing.node.tree.iterator.fast.FastLevelOrderTreeNodeIterator;
import net.vectorcomputing.node.tree.iterator.fast.FastPreOrderTreeNodeIterator;
import net.vectorcomputing.node.tree.iterator.light.LightLevelOrderTreeNodeIteratorFactory;
import net.vectorcomputing.node.tree.search.ITreeNodeSearcher;
import net.vectorcomputing.node.tree.search.TreeNodeSearcher;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * General tree node utility functions.
 */
public final class TreeNodes {

	/**
	 * Make the constructor inaccessible
	 */
	private TreeNodes() {
	}

	/**
	 * Creates a list of tree nodes that represent the path from begin to the
	 * root of a tree of nodes.
	 * 
	 * @param <T>
	 *            a tree node-implementing class
	 * @param begin
	 * @return a list containing the tree nodes visited while walking from the
	 *         begin tree node to the root (includes the begin tree node and the
	 *         root tree node)
	 */
	public static <T extends TreeNode<T>> List<T> getPathToRoot(final T begin) {
		Assert.isNotNull(begin, "begin"); //$NON-NLS-1$
		final ArrayList<T> pathToRoot = new ArrayList<T>();
		pathToRoot.add(begin);
		T node = begin;
		while (node.hasParent()) {
			node = node.getParent();
			pathToRoot.add(node);
		}
		return Collections.unmodifiableList(pathToRoot);
	}

	/**
	 * Finds the root of the tree containing the specified tree node by
	 * iterating over the tree node's parent, grandparent, and so on until a
	 * tree node without a parent is found.
	 * 
	 * @param <T>
	 *            a tree node-implementing class
	 * @param begin
	 *            a tree node of of the tree to find the root of
	 * @return the root tree node
	 */
	public static <T extends TreeNode<T>> T getRoot(final T begin) {
		Assert.isNotNull(begin, "begin"); //$NON-NLS-1$
		T node = begin;
		while (node.hasParent()) {
			node = node.getParent();
		}
		return node;
	}

	/**
	 * Finds all leaves of the specified tree node. Achieves this by first
	 * finding the root of the tree containing the specified tree node and then
	 * finding all leaves of the root.
	 * 
	 * @param root
	 *            a tree node that is contained in the tree to find leaves in
	 * @return all leaves
	 */
	public static <T extends TreeNode<T>> List<T> getAllLeaves(final T root) {
		return getLeaves(getRoot(root));
	}

	/**
	 * Finds the leaves of the specified tree node. The tree node specified does
	 * not need to be the root of the tree. If the tree node is the root of the
	 * tree, then all leaves will be returned. If it is not the root, then
	 * only the tree nodes that are leaves descended from the specified tree
	 * node will be returned.
	 * 
	 * @param <T>
	 *            a tree node-implementing class
	 * @param root
	 *            the tree node to find the leaves of
	 * @return the leaves of this tree node
	 */
	public static <T extends TreeNode<T>> List<T> getLeaves(final T root) {
		Assert.isNotNull(root, "root"); //$NON-NLS-1$
		ArrayList<T> leaves = new ArrayList<T>();
		TreeNodeIterator<T> iterator = new FastLevelOrderTreeNodeIterator<T>(root);
		T node;
		while ((node = iterator.next()) != null) {
			if (!node.hasChildren()) {
				leaves.add(node);
			}
		}
		return leaves;
	}

	/**
	 * Finds the depth of a tree node in a tree. The root tree node is, by
	 * definition, at a depth of 0.
	 * 
	 * @param <T>
	 *            a tree node-implementing class
	 * @param begin
	 * @return the depth of the specified tree node in its containing tree
	 */
	public static <T extends TreeNode<T>> int getDepth(final T begin) {
		Assert.isNotNull(begin, "begin"); //$NON-NLS-1$
		int i = 0;
		T node = begin;
		while (node.hasParent()) {
			node = node.getParent();
			++i;
		}
		return i;
	}

	/**
	 * Determines the depth of a member of a {@link TreeNode}s to a pseudo root
	 * tree node (not the actual root with no parent). If the pseudo root is not
	 * encountered while moving up from the start tree node to each parent, the
	 * depth to the actual root tree node will instead be returned.
	 * 
	 * distance aka span
	 * 
	 * @param <T>
	 *            a tree node-implementing class
	 * @param begin
	 * @param parent
	 * @return the distance from the begin tree node to the parent (if it is
	 *         encountered) or the distance to the root if the parent tree node
	 *         is not encountered while moving toward the root
	 */
	public static <T extends TreeNode<T>> int getDistanceToParent(final T begin, final T parent) {
		Assert.isNotNull(begin, "begin"); //$NON-NLS-1$
		Assert.isNotNull(parent, "parent"); //$NON-NLS-1$
		
		if (begin.equals(parent)) {
			return 0;
		}

		int distance = 0;
		T node = begin;
		while (node.hasParent() && !node.equals(parent)) {
			node = node.getParent();
			++distance;
		}
		return distance;
	}

	/**
	 * <p>
	 * Determines the overall height of a tree of nodes that contains the
	 * specified tree node.
	 * </p>
	 * <p>
	 * The height of a tree is the number of nodes in the longest path from the
	 * root to a leaf tree node. This value is the same for any tree node in the
	 * same tree.
	 * </p>
	 * 
	 * @param <T>
	 *            a tree node-implementing class
	 * @param node
	 *            A tree node that is part of a tree for which the height will
	 *            be determined
	 * @return the height of the tree.
	 */
	public static <T extends TreeNode<T>> int getTreeHeight(final T node) {
		Assert.isNotNull(node, "node"); //$NON-NLS-1$
		final FastPreOrderTreeNodeIterator<T> iter = new FastPreOrderTreeNodeIterator<T>(getRoot(node));
		return iter.getMaximumDepthVisited() + 1;
	}

	/**
	 * Builds a list of directly related tree nodes (parent or child
	 * relationship) that if traversed walk from the start tree node to the end
	 * tree node.
	 * 
	 * @param <T>
	 *            a tree node-implementing class
	 * @param start
	 *            the tree node the path is to begin at
	 * @param finish
	 *            the tree node the path is to end at
	 * @return an unmodifiable list that contains the tree nodes traversed when
	 *         walking from begin to end
	 */
	public static <T extends TreeNode<T>> List<T> getPath(T start, T finish) {
		/* Example
		 *     A
		 *    /
		 *   B
		 *  / \
		 * C    D
		 *     /
		 *    E
		 * 
		 * paths to root:
		 * startToRoot  = [ C , B , A ]
		 * finishToRoot = [ E , D , B , A ]
		 * 
		 * after while loop:
		 * sToRIndex = 0
		 * fToRIndex = 1
		 * 
		 * at end:
		 * startToFinish = [ C , B , D , E ]
		 */
		
		Assert.isNotNull(start, "start"); //$NON-NLS-1$
		Assert.isNotNull(finish, "finish"); //$NON-NLS-1$
		
		final List<T> startToRoot = getPathToRoot(start);
		final List<T> finishToRoot = getPathToRoot(finish);

		// The start and finish nodes must share a common root for there to be a
		// path between them
		if (!lastNodesEqual(startToRoot, finishToRoot)) {
			throw new IllegalArgumentException(
					NodeMessages.TreeNodes_StartAndFinishNodesDoNotShareACommonRoot);
		}

		// subtract two rather than one to avoid comparing the common root node
		// as it is already known to be equal
		int sToRIndex = startToRoot.size() - 2;
		int fToRIndex = finishToRoot.size() - 2;
		// walk backwards checking for the first non-equal nodes
		while (startToRoot.get(sToRIndex).equals(finishToRoot.get(fToRIndex))) {
			--sToRIndex;
			--fToRIndex;
		}
		// at this point, both sToRIndex and fToRIndex refer to the first
		// non-equal nodes of both paths to root

		// create the path from start to finish by copying selectively from
		// startToRoot and then finishToRoot
		final int pathSize = 3 + sToRIndex + fToRIndex;
		final ArrayList<T> startToFinish = new ArrayList<T>(pathSize);
		++sToRIndex; // add one back to include the common node when copying
		int i = 0; // the index into the path from start to finish
		for (; i <= sToRIndex; ++i) {
			startToFinish.add(startToRoot.get(i));
		}
		for (; i < pathSize; ++i) {
			startToFinish.add(finishToRoot.get(fToRIndex));
			--fToRIndex;
		}
		return Collections.unmodifiableList(startToFinish);
	}

	private static <T extends TreeNode<T>> boolean lastNodesEqual(
			final List<T> firstList, final List<T> secondList) {
		final T firstListLastNode = getLastNode(firstList);
		final T secondListLastNode = getLastNode(secondList);
		return (firstListLastNode == secondListLastNode);
	}

	private static <T extends TreeNode<T>> T getLastNode(final List<T> list) {
		if (list.size() == 0) {
			throw new IllegalArgumentException(
					NodeMessages.Nodes_ListSizeMustBeGreaterThanZero);
		} else {
			return list.get(list.size() - 1);
		}
	}

	/**
	 * Creates a node search that finds all tree nodes that satisfy the
	 * constraint.
	 * 
	 * @param constraint
	 *            the constraint that a tree node must satisfy to be included in
	 *            the results
	 * @return a node searcher that will find all tree nodes satisfying the
	 *         constraint
	 */
	public static <T extends TreeNode<T>> ITreeNodeSearcher<T> createFindNodesSatisfyingConstraintSearch(
			final TreeNodeConstraint<T> constraint) {
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		final ITreeNodeSearcher<T> search = new TreeNodeSearcher<T>(
				new LightLevelOrderTreeNodeIteratorFactory<T>(), constraint);
		search.unsetDepthLimit();
		search.unsetResultsSizeLimit();
		return search;
	}

	/**
	 * Finds and returns a list containing all tree nodes that satisfy the
	 * specified tree node constraint.
	 * 
	 * @param constraint
	 *            the constraint that a tree node must satisfy to be included in
	 *            the results
	 * @return the tree nodes that satisfy the constraint
	 */
	public static <T extends TreeNode<T>> List<T> findNodesSatisfyingConstraint(
			final T root, final TreeNodeConstraint<T> constraint) {
		Assert.isNotNull(root, "root"); //$NON-NLS-1$
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		return createFindNodesSatisfyingConstraintSearch(constraint).search(root);
	}

	/**
	 * Creates a tree node search that finds all tree nodes that satisfy the
	 * tree node constraint within the depth limit.
	 * 
	 * @param constraint
	 *            the constraint that a tree node must satisfy to be included in
	 *            the results
	 * @param depthLimit
	 *            the depth to limit searches to
	 * @return a tree node searcher that will find all nodes that satisfy the
	 *         constraint
	 */
	public static <T extends TreeNode<T>> ITreeNodeSearcher<T> createFindNodesSatisfyingConstraintSearch(
			final TreeNodeConstraint<T> constraint, final int depthLimit) {
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		ITreeNodeSearcher<T> search = new TreeNodeSearcher<T>(
				new LightLevelOrderTreeNodeIteratorFactory<T>(), constraint);
		search.setDepthLimit(depthLimit);
		search.unsetResultsSizeLimit();
		return search;
	}

	/**
	 * Finds all tree nodes that satisfy the tree node constraint within a depth
	 * limit.
	 * 
	 * @param root
	 *            the tree node to begin the search at
	 * @param constraint
	 *            the constraint that tree nodes must satisfy
	 * @param depthLimit
	 *            the depth to limit searches to
	 * @return the tree nodes that satisfy the constraint
	 */
	public static <T extends TreeNode<T>> List<T> findNodesSatisfyingConstraint(
			final T root, final TreeNodeConstraint<T> constraint,
			final int depthLimit) {
		Assert.isNotNull(root, "root"); //$NON-NLS-1$
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		return createFindNodesSatisfyingConstraintSearch(constraint, depthLimit).search(root);
	}

	/**
	 * Finds and returns the first tree node that satisfies the constraint using
	 * a level-order (breadth-first) search.
	 * 
	 * @param root
	 *            the tree node to begin the search at
	 * @param constraint
	 *            the constraint that the tree node must satisfy
	 * @return the first tree node that satisfies the constraint
	 * @throws IllegalArgumentException
	 *             if a tree node satisfying the constraint cannot be found
	 */
	public static <T extends TreeNode<T>> T findFirstNodeSatisfyingConstraint(
			final T root, final TreeNodeConstraint<T> constraint)
			throws CoreException {
		Assert.isNotNull(root, "root"); //$NON-NLS-1$
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$

		final ITreeNodeSearcher<T> searcher = new TreeNodeSearcher<T>(
				new LightLevelOrderTreeNodeIteratorFactory<T>(), constraint);
		searcher.setResultsSizeLimit(1);
		final List<T> results = searcher.search(root);

		if (results.size() > 0) {
			return results.get(0);
		} else {
			throw new CoreException(new Status(IStatus.ERROR,
					NodePlugin.PLUGIN_ID,
					NodeMessages.TreeNodes_ZeroNodesSatisfyTheConstraint));
		}
	}

}
