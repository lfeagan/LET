package net.vectorcomputing.node.tree.search;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.constraint.TreeNodeConstraint;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;

import org.eclipse.core.runtime.Assert;

public abstract class AbstractTreeNodeSearcher<T extends TreeNode<T>> implements ITreeNodeSearcher<T>{

	/**
	 * The maximum number of results to return from a search.
	 */
	protected int resultsSizeLimit;

	/**
	 * The depth to limit iteration to.
	 */
	protected int depthLimit;

	/**
	 * 
	 */
	protected final TreeNodeConstraint<T> constraint;
	
	protected AbstractTreeNodeSearcher(final TreeNodeConstraint<T> constraint) {
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		this.constraint = constraint;
	}
	
	/**
	 * Determines if the specified depth satisfies the depth limit constraints.
	 * 
	 * @return <code>true</code> if depth satisfies the depth limit constraints
	 */
	protected final boolean isDepthValid(final int depth) {
		return depth <= depthLimit || depthLimit == TreeNodeIterator.NO_DEPTH_LIMIT;
	}
	
	protected final boolean isResultsSizeAtLimit(final int resultsSize) {
		return !(resultsSize < resultsSizeLimit || resultsSizeLimit == ITreeNodeSearcher.NO_RESULTS_SIZE_LIMIT);
	}
	
	@Override
	public void setDepthLimit(final int depthLimit) {
		this.depthLimit = depthLimit;
	}

	@Override
	public void unsetDepthLimit() {
		this.depthLimit = TreeNodeIterator.NO_DEPTH_LIMIT;
	}

	@Override
	public void setResultsSizeLimit(final int resultsSizeLimit) {
		this.resultsSizeLimit = resultsSizeLimit;
	}

	@Override
	public void unsetResultsSizeLimit() {
		this.resultsSizeLimit = ITreeNodeSearcher.NO_RESULTS_SIZE_LIMIT;
	}
	
}
