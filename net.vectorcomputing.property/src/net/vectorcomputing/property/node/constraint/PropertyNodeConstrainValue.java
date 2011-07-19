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
 * A property node constraint that tests if a property node's value satisfies a
 * string constraint.
 */
public class PropertyNodeConstrainValue implements PropertyNodeConstraint {

	private final IStringConstraint valueConstraint;

	/**
	 * Constructor for a property node constraint that tests if a property
	 * node's value satisfies the specified string constraint.
	 * 
	 * @param valueConstraint
	 *            the string constraint that evaluates a property node's value
	 */
	public PropertyNodeConstrainValue(final IStringConstraint valueConstraint) {
		Assert.isNotNull(valueConstraint, "valueConstraint"); //$NON-NLS-1$
		this.valueConstraint = valueConstraint;
	}

	/**
	 * @return <code>true</code> if the property node's value satisfies the
	 *         string constraint
	 */
	@Override
	public boolean satisfiedBy(final PropertyNode propertyNode) {
		Assert.isNotNull(propertyNode, "propertyNode"); //$NON-NLS-1$
		return valueConstraint.satisfiedBy(propertyNode.getValue());
	}

}
