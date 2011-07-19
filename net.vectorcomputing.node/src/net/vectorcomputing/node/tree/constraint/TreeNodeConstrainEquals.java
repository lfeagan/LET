package net.vectorcomputing.node.tree.constraint;

import org.eclipse.core.runtime.Assert;

import net.vectorcomputing.node.tree.TreeNode;

/**
 * A node constraint that determines if a tree node is equal to a reference
 * node.
 * 
 * @param <T>
 *            the type of tree node the constraint checks
 */
public class TreeNodeConstrainEquals<T extends TreeNode<T>> implements
		TreeNodeConstraint<T> {

	private final T referenceNode;

	public TreeNodeConstrainEquals(final T referenceNode) {
		Assert.isNotNull(referenceNode, "referenceNode"); //$NON-NLS-1$
		this.referenceNode = referenceNode;
	}

	@Override
	public boolean satisfiedBy(final T node) {
		return referenceNode.equals(node);
	}

}
