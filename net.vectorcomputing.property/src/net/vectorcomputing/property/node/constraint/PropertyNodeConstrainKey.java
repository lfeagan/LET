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

import org.eclipse.core.runtime.Assert;

import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.property.node.PropertyNode;

/**
 * A property node constraints that evaluates if a property node's key satisfies
 * a string constraint.
 */
public class PropertyNodeConstrainKey implements PropertyNodeConstraint {

	private final IStringConstraint keyConstraint;

	/**
	 * Constructor for a property node constraint that evaluates if a property
	 * node's key satisfies the specified string constraint.
	 * 
	 * @param keyConstraint
	 *            the string constraint that evaluates a property node's key
	 */
	public PropertyNodeConstrainKey(final IStringConstraint keyConstraint) {
		Assert.isNotNull(keyConstraint, "keyConstraint"); //$NON-NLS-1$
		this.keyConstraint = keyConstraint;
	}

	/**
	 * @return <code>true</code> if the property node's key satisfies the string
	 *         constraint
	 */
	@Override
	public boolean satisfiedBy(final PropertyNode propertyNode) {
		return keyConstraint.satisfiedBy(propertyNode.getKey());
	}

}
