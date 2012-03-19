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
 * A properties constraint that determines if all of the properties satisfy a
 * property constraint.
 * 
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PropertiesConstrainPropertyConstraintAnd implements IPropertiesConstraint {

	private final IPropertyConstraint propertyConstraint;

	/**
	 * Constructor for a property constraint that determines if all elements in
	 * a list of properties satisfy a property constraint.
	 * 
	 * @param propertyConstraint
	 *            the property constraint that must be satisfied by all
	 *            properties in the collection
	 */
	public PropertiesConstrainPropertyConstraintAnd(final IPropertyConstraint propertyConstraint) {
		Assert.isNotNull(propertyConstraint, "propertyConstraint"); //$NON-NLS-1$
		this.propertyConstraint = propertyConstraint;
	}

	/**
	 * Determines if all of the properties satisfy the property constraint
	 * 
	 * @return <code>true</code> if all of the properties satisfy the property
	 *         constraint
	 */
	@Override
	public boolean satisfiedBy(final Collection<Property> properties) {
		for (Property property : properties) {
			if (!propertyConstraint.satisfiedBy(property)) {
				return false;
			}
		}
		return true;
	}

}
