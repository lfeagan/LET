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
package net.vectorcomputing.property.constraint;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;

import net.vectorcomputing.property.Property;

/**
 * A properties constraint that determines if one and only one of a list of
 * properties satisfies a property constraint.
 */
public class PropertiesConstrainPropertyConstraintXor implements IPropertiesConstraint {

	private final IPropertyConstraint propertyConstraint;

	/**
	 * Constructor for a properties constraint that test if exactly on property
	 * from a collection of properties satisfies a property constraint.
	 * 
	 * @param propertyConstraint
	 *            the property constraint that exactly one property must satisfy
	 */
	public PropertiesConstrainPropertyConstraintXor(final IPropertyConstraint propertyConstraint) {
		Assert.isNotNull(propertyConstraint, "propertyConstraint"); //$NON-NLS-1$
		this.propertyConstraint = propertyConstraint;
	}

	/**
	 * @return <code>true</code> if one and only one of the properties satisfies
	 *         the property constraint
	 */
	@Override
	public boolean satisfiedBy(final Collection<Property> properties) {
		int satisfiedByCount = 0;
		for (Property property : properties) {
			if (propertyConstraint.satisfiedBy(property)) {
				++satisfiedByCount;
				// If satisfiedByCount exceeds 1, no need to continue
				if (satisfiedByCount > 1) {
					return false;
				}
			}
		}
		return satisfiedByCount == 1;
	}

}
