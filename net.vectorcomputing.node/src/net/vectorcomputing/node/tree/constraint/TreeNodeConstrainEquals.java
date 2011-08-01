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
