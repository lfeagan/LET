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
package net.vectorcomputing.node.tree.constraint;

import org.eclipse.core.runtime.Assert;

import net.vectorcomputing.node.tree.TreeNode;

/**
 * A node constraint that ORs multiple tree node constraints together.
 * 
 * @param <T>
 *            the type of tree node the constraint checks
 */
public class TreeNodeConstraintOr<T extends TreeNode<T>> implements
		TreeNodeConstraint<T> {

	private final TreeNodeConstraint<T>[] constraints;

	/**
	 * Constructor for ORing multiple tree node constraints together
	 * 
	 * @param constraints
	 *            the tree node constraints to OR together
	 */
	public TreeNodeConstraintOr(final TreeNodeConstraint<T>... constraints) {
		Assert.isNotNull(constraints, "constraints"); //$NON-NLS-1$
		this.constraints = constraints;
	}

	/**
	 * Evaluates if the tree node satisfies any of the node constraints.
	 * 
	 * @return <code>true</code> if the tree node satisfies any of the node
	 *         constraints
	 */
	@Override
	public boolean satisfiedBy(final T node) {
		for (TreeNodeConstraint<T> constraint : constraints) {
			if (constraint.satisfiedBy(node)) {
				return true;
			}
		}
		return false;
	}

}