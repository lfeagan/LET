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
