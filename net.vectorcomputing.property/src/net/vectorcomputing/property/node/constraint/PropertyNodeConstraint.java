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
package net.vectorcomputing.property.node.constraint;

import net.vectorcomputing.node.tree.constraint.TreeNodeConstraint;
import net.vectorcomputing.property.node.PropertyNode;

/**
 * Interface for testing that a property node satisfies a constraint.
 */
public interface PropertyNodeConstraint extends TreeNodeConstraint<PropertyNode> {

	/**
	 * Determines if the specified property node satisfies the constraint.
	 * 
	 * @param propertyNode
	 *            the property node to test
	 * @return <code>true</code> if the property node satisfies the constraint
	 */
	@Override
	public boolean satisfiedBy(PropertyNode propertyNode);

}
