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
 * A node constraint that returns the inverse (logical 'NOT') of the value
 * returned by the underlying tree node constraint's
 * {@link TreeNodeConstraint#satisfiedBy(TreeNode)} method.
 * 
 * @param <T>
 *            the type of tree node the constraint checks
 */
public class TreeNodeConstraintNot<T extends TreeNode<T>> implements
		TreeNodeConstraint<T> {

	private final TreeNodeConstraint<T> constraint;

	/**
	 * Constructor for a tree node constraint that inverts the result of an
	 * underlying tree node constraint.
	 * 
	 * @param constraint
	 *            the tree node constraint to invert the result of
	 *            {@link #satisfiedBy(TreeNode)} on
	 */
	public TreeNodeConstraintNot(final TreeNodeConstraint<T> constraint) {
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		this.constraint = constraint;
	}

	/**
	 * @return the inverse of the value returned by the underlying tree node
	 *         constraint's satisfiedBy method
	 */
	@Override
	public boolean satisfiedBy(final T candidate) {
		return (!constraint.satisfiedBy(candidate));
	}

}
