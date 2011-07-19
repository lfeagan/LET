package net.vectorcomputing.node.tree.constraint;

import java.util.Collection;

import net.vectorcomputing.node.tree.TreeNode;

/**
 * A tree node constraint that determines if a tree node is contained in a
 * collection of reference tree nodes.
 * 
 * @param <T>
 *            the type of tree node the constraint checks
 */
public class TreeNodeConstrainContainedIn<T extends TreeNode<T>> implements
		TreeNodeConstraint<T> {

	private final Collection<T> referenceNodes;

	public TreeNodeConstrainContainedIn(final Collection<T> referenceNodes) {
		this.referenceNodes = referenceNodes;
	}

	@Override
	public boolean satisfiedBy(final T node) {
		return referenceNodes.contains(node);
	}

}
