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

import java.util.BitSet;


import org.eclipse.core.runtime.Assert;

/**
 * <p>
 * A node explorer provides a simple, reliable mechanism for visiting a tree of
 * nodes. The basic methods for exploring the tree are:
 * <ul>
 * <li>{@link #goToChild()},</li>
 * <li>{@link #goToNextSibling()},</li>
 * <li>{@link #goToPreviousSibling()},</li>
 * <li>{@link #goToParent()},</li>
 * <li>{@link #goToDepth(int)}, and</li>
 * <li>{@link #goToRoot()}.</li>
 * </ul>
 * 
 * <p>
 * One of the unique features of a tree node explorer is the ability to limit
 * the upper-most tree node that can be visited by specifying a tree node other
 * than the true root of the tree to behave as though it were the root of the
 * tree. (The true root of the tree is the only tree node without a parent.)
 * Even though this artificial root tree node has a parent, a call to
 * {@link TreeNodeExplorer#hasParent()} will return false. All tree node
 * exploration methods should use {@link TreeNodeExplorer#hasParent()} instead
 * of {@link TreeNode#hasParent()}.
 * </p>
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public class TreeNodeExplorer<T extends TreeNode<T>> {
	
	/**
	 * The effective root of the tree for this explorer.
	 */
	private final T root;

	private final TreeNodeLocation<T> location = new TreeNodeLocation<T>();

	/**
	 * A bit set is used to keep track of the number of depths in the tree as it
	 * is traversed.
	 */
	private final BitSet depths = new BitSet();

	private T currentNode;

	public TreeNodeExplorer(final T rootNode) {
		Assert.isNotNull(rootNode, "rootNode"); //$NON-NLS-1$
		
		this.root = rootNode;
		this.currentNode = rootNode;
		this.depths.set(0);
		checkAndSetNextDepthExists();
	}

	private boolean DEBUG = false;

	private final void debug(String message) {
		if (DEBUG) {
			System.out.println(message);
		}
	}

	/**
	 * Returns the tree node that is being visited at present.
	 * 
	 * @return the tree node that is being visited at present
	 */
	public T getCurrentNode() {
		return this.currentNode;
	}

	public int getDepth() {
		return location.getDepth();
	}

	/**
	 * Determines if the current node is the root of this explorer.
	 * 
	 * @return <code>true</code> if the current node is the root node (as
	 *         specified when this tree node explorer was constructed)
	 */
	public boolean isRoot() {
		return currentNode == root;
	}

	/**
	 * Determines if the current node is not the root of this explorer.
	 * 
	 * @return <code>true</code> if the current node is not the root of the tree
	 *         (as specified when this tree node explorer was constructed)
	 */
	public boolean isNonRoot() {
		return currentNode != root;
	}

	/**
	 * 
	 */
	public void goToRoot() {
		debug("manually setting currentNode = root"); //$NON-NLS-1$
		currentNode = root;
		debug("resetting location"); //$NON-NLS-1$
		location.reset();
	}

	/**
	 * Goes to the parent of the current tree node.
	 * 
	 * @return <code>true</code> if the current tree node had a parent and
	 *         therefore the current tree node and location were updated
	 */
	public boolean goToParent() {
		if (isNonRoot()) {
			debug("going to parent"); //$NON-NLS-1$
			location.goToParent();
			currentNode = currentNode.getParent();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Goes to the first child of the current tree node.
	 * 
	 * @return <code>true</code> if the current tree node had children and the
	 *         location and current tree node were updated to refer to first
	 *         child.
	 */
	public boolean goToChild() {
		debug("goToChild"); //$NON-NLS-1$
		if (currentNode.hasChildren()) {
			debug("goToChild:hasChildren:true"); //$NON-NLS-1$
			location.goToChild();
			currentNode = currentNode.getChild(0);
			checkAndSetNextDepthExists();
			return true;
		} else {
			debug("goToChild:hasChildren:false"); //$NON-NLS-1$
			return false;
		}
	}

	/**
	 * Goes to the next sibling in the current tree node's parent's list of
	 * children.
	 * 
	 * @return <code>true</code> if the current tree node had a next sibling (to
	 *         the right) and the location and current tree node were updated to
	 *         refer to it.
	 */
	public boolean goToNextSibling() {
		if (hasSiblingsToRight()) {
			debug("going to next sibling"); //$NON-NLS-1$
			location.goToNextSibling();
			currentNode = location.get(root);
			checkAndSetNextDepthExists();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Goes to the previous sibling in the current tree node's parent's list of
	 * children.
	 * 
	 * @return <code>true</code> if the current tree node had a previous sibling
	 *         (to the left) and the location and current tree node were updated
	 *         to refer to it.
	 */
	public boolean goToPreviousSibling() {
		if (hasSiblingsToLeft()) {
			debug("going to previous sibling"); //$NON-NLS-1$
			location.goToPreviousSibling();
			currentNode = location.get(root);
			checkAndSetNextDepthExists();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Goes to the first child of a parent, grand parent, great grand parent,
	 * etc...of the current tree node that has not been visited. If the current
	 * tree node is along the right-most branch of the tree of nodes, the
	 * call will terminate when the root tree node has been reached.
	 */
	public void goToParentNextSibling() {
		debug("goToParentNextSibling"); //$NON-NLS-1$
		while (goToParent()) {
			debug("goToParentNextSibling:hadParent:true"); //$NON-NLS-1$
			if (goToNextSibling()) {
				break;
			}
		}
	}

	/**
	 * Method for descending from an upper-level to the requested depth. This
	 * method should not be invoked if the requested depth does not exist.
	 * 
	 * @param requestedDepth
	 *            the depth to go go
	 */
	public void goToDepth(final int requestedDepth) {
		debug("goToDepth " + requestedDepth); //$NON-NLS-1$
		while (location.getDepth() < requestedDepth) {
			if (goToChild()) {
				// there was a child to go to
			} else if (goToNextSibling()) {
				// there was a sibling to go to
			} else if (hasParent()) {
				goToParentNextSibling();
				if (isRoot()) {
					throw new IllegalArgumentException(
							"Unable to go to requested target depth. Requested depth does not exist."); //$NON-NLS-1$
				}
			} else {
				// No children, siblings, or parent --> single node, shouldn't
				// have called this method ever
				throw new IllegalArgumentException(
						"Unable to go to requested target depth. No parent, children, or siblings exist."); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Determines if the current tree node has a parent. If the current tree
	 * node is the artificial root specified when constructing this tree node
	 * explorer, this method will return <code>false</code> regardless of the
	 * current tree node actually having a parent. If the current tree node is
	 * not the artificial root, returns the value returned by the current tree
	 * node's {@link TreeNode#hasParent()} method.
	 * 
	 * @return <code>true</code> if the current tree node has a parent
	 */
	public boolean hasParent() {
		if (currentNode == root) {
			return false;
		} else {
			return currentNode.hasParent();
		}
	}

	/**
	 * Returns <code>true</code> if the current tree node has children.
	 * 
	 * @return <code>true</code> if the current tree node has children
	 */
	public boolean hasChildren() {
		return currentNode.hasChildren();
	}

	/**
	 * Returns <code>true</code> if the current tree node has siblings to the
	 * right by comparing the current location with the length of the parent's
	 * list of children.
	 * 
	 * @return <code>true</code> if the current tree node has siblings to the
	 *         right
	 */
	public boolean hasSiblingsToRight() {
		if (hasParent()) {
			int locationIndex = location.getChildOffset(location.getDepth());
			return (locationIndex < getNumberOfSiblings());
		} else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if the current tree node has siblings to the
	 * left by comparing the current location with the start index of the
	 * parent's list of children (which is zero by definition).
	 * 
	 * @return <code>true</code> if the current tree node has siblings to the
	 *         left
	 */
	public boolean hasSiblingsToLeft() {
		if (hasParent()) {
			int locationIndex = location.getChildOffset(location.getDepth());
			return (locationIndex == 0);
		} else {
			return false;
		}
	}

	/**
	 * Computes the number of siblings of the current tree node. This is the
	 * size of the current tree node's parent's list of children minus one. If
	 * this tree node does not have a parent, by construction there is no
	 * possibility of siblings and zero is returned.
	 * 
	 * @return the number of siblings this tree node has
	 */
	public int getNumberOfSiblings() {
		if (hasParent()) {
			return (currentNode.getParent().sizeOfChildren() - 1);
		} else {
			return 0;
		}
	}

	/**
	 * Returns <code>true</code> if the specified depth is known definitively to
	 * exist.
	 * <p>
	 * The implementation uses information collected during previous invocations
	 * of tree exploration methods that navigate away from the root to ensure a
	 * low constant time complexity. Specifically, while visiting a tree node a
	 * simple check using the getChildren method is performed to determine if
	 * the next depth in the tree exists. If it does, a bit set is used to
	 * signify its existence. A consequence of this implementation is that a
	 * return value of <code>false</code> does not guarantee the non-existence
	 * of a particular depth as this may simply indicate that a tree node above
	 * the requested depth has not yet been visited.
	 * 
	 * @param depth
	 *            the depth to check for existence of
	 * @return <code>true</code> if the depth is known definitively to exist
	 */
	public boolean hasDepth(final int depth) {
		return depths.get(depth);
	}

	/**
	 * Returns <code>true</code> if the next depth is known definitively to
	 * exist.
	 * 
	 * @return <code>true</code> if the next depth is known definitively to
	 *         exist
	 * @see TreeNodeExplorer#hasDepth(int)
	 */
	public boolean hasNextDepth() {
		return hasDepth(location.getDepth() + 1);
	}
	
	/**
	 * Determines if a depth below the current tree node exists and stores this
	 * information for later use. The current tree node's
	 * {@link TreeNode#hasChildren()} method is used to determine if the next
	 * depth exists.
	 */
	private void checkAndSetNextDepthExists() {
		if (!hasNextDepth() && currentNode.hasChildren()) {
			final int nextDepthLevel = location.getDepth() + 1;
			debug("a next level exists, setting level " + nextDepthLevel + " to true"); //$NON-NLS-1$ //$NON-NLS-2$
			depths.set(nextDepthLevel);
		}
	}
	
}
