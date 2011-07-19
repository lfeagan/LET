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
package net.vectorcomputing.node.tree.search;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.constraint.TreeNodeConstraint;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;
import net.vectorcomputing.node.tree.iterator.TreeNodeIteratorFactory;

/**
 * A tree node searcher that uses a tree node iterator and constraint provided
 * at construction time.
 * 
 * @param <T>
 *            the type of tree node to search
 */
public class TreeNodeSearcher<T extends TreeNode<T>> implements ITreeNodeSearcher<T> {
	
	private int resultsSizeLimit;
	private int depthLimit;
	private final TreeNodeIteratorFactory<T> iteratorFactory;
	private final TreeNodeConstraint<T> constraint;

	/**
	 * Constructor for a tree node searcher.
	 * 
	 * @param iteratorFactory
	 *            the factory used to create a {@link TreeNodeIterator} when
	 *            searching
	 * @param constraint
	 *            the constraint a tree node must satisfy to be included in the
	 *            search results
	 */
	public TreeNodeSearcher(
			final TreeNodeIteratorFactory<T> iteratorFactory,
			final TreeNodeConstraint<T> constraint) {
		
		Assert.isNotNull(iteratorFactory, "iteratorFactory"); //$NON-NLS-1$
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		
		this.resultsSizeLimit = ITreeNodeSearcher.NO_RESULTS_SIZE_LIMIT;
		this.depthLimit = TreeNodeIterator.NO_DEPTH_LIMIT;
		this.iteratorFactory = iteratorFactory;
		configureIteratorFactoryDepthLimit();
		this.constraint = constraint;
	}

	@Override
	public void setDepthLimit(final int depthLimit) {
		this.depthLimit = depthLimit;
		configureIteratorFactoryDepthLimit();
	}

	@Override
	public void unsetDepthLimit() {
		depthLimit = TreeNodeIterator.NO_DEPTH_LIMIT;
		configureIteratorFactoryDepthLimit();
	}

	@Override
	public void setResultsSizeLimit(final int resultsSizeLimit) {
		this.resultsSizeLimit = resultsSizeLimit;
	}

	@Override
	public void unsetResultsSizeLimit() {
		resultsSizeLimit = NO_RESULTS_SIZE_LIMIT;
	}

	@Override
	public List<T> search(final T root) {
		Assert.isNotNull(root, "root"); //$NON-NLS-1$
		
		final TreeNodeIterator<T> iterator = iteratorFactory.create(root);
		final ArrayList<T> results = new ArrayList<T>();
		T node;
		int numOfResults = 0;

		while (iterator.hasNext()
				&& (resultsSizeLimit == NO_RESULTS_SIZE_LIMIT 
					|| numOfResults < resultsSizeLimit)) {
			node = iterator.next();
			if (constraint.satisfiedBy(node)) {
				results.add(node);
				++numOfResults;
			}
		}
		return results;
	}
	
	private final void configureIteratorFactoryDepthLimit() {
		if (depthLimit == TreeNodeIterator.NO_DEPTH_LIMIT) {
			iteratorFactory.unsetDepthLimit();
		} else {
			iteratorFactory.setDepthLimit(depthLimit);
		}
	}
	
}
