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

import net.vectorcomputing.property.constraint.IPropertiesConstraint;
import net.vectorcomputing.property.node.PropertyNode;

/**
 * A property node constraint that tests the attributes of a property node.
 */
public class PropertyNodeConstrainAttributes implements PropertyNodeConstraint {

	private final IPropertiesConstraint propertiesConstraint;

	public PropertyNodeConstrainAttributes(final IPropertiesConstraint propertiesConstraint) {
		Assert.isNotNull(propertiesConstraint, "propertiesConstraint"); //$NON-NLS-1$
		this.propertiesConstraint = propertiesConstraint;
	}

	/**
	 * @return <code>true</code> if the property node's attributes satisfy the
	 *         property constraint
	 */
	@Override
	public boolean satisfiedBy(final PropertyNode propertyNode) {
		Assert.isNotNull(propertyNode, "propertyNode"); //$NON-NLS-1$
		return propertiesConstraint.satisfiedBy(propertyNode.getAttributes());
	}

}
