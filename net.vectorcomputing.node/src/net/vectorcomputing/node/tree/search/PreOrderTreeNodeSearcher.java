/*******************************************************************************
 * Copyright 2011 lfeagan
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

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.constraint.TreeNodeConstraint;

import org.eclipse.core.runtime.Assert;

/**
 * A tree node searcher that visits tree nodes in pre-order to find satisfactory
 * nodes.
 * 
 * @param <T>
 *            the type of tree node to search
 */
public class PreOrderTreeNodeSearcher<T extends TreeNode<T>> extends AbstractTreeNodeSearcher<T> {

	/**
	 * Constructor for a level-order tree node searcher that uses the specified
	 * constraint to identify satisfactory tree nodes to add to the results.
	 * 
	 * @param constraint
	 *            the tree node constraint a node must satisfy to be added to
	 *            the results
	 */
	public PreOrderTreeNodeSearcher(final TreeNodeConstraint<T> constraint) {
		super(constraint);
	}
	
	@Override
	public List<T> search(final T top) {
		Assert.isNotNull(top, "top"); //$NON-NLS-1$
		
		final ArrayList<T> results = new ArrayList<T>();
		search(top, 0, results);
		return results;
	}

	private void search(final T parent, final int currentDepth, final List<T> results) {
		if (constraint.satisfiedBy(parent)) {
			results.add(parent);
			// check that the size of results is still less than the limit
			if (isResultsSizeAtLimit(results.size())) {
				return;
			}
		}
			
		if (parent.hasChildren() && isDepthValid(currentDepth)) {
			for (T child : parent.getChildren()) {
				search(child, currentDepth + 1, results);
			}
		}
	}

}
