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
 * A properties constraint that determines if all elements in a list of
 * properties satisfy all elements of a list of properties constraints.
 * 
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PropertiesConstraintAnd implements IPropertiesConstraint {

	private final IPropertiesConstraint[] propertiesConstraints;

	/**
	 * @param propertiesConstraints
	 *            the properties constraints that must be satisfied
	 */
	public PropertiesConstraintAnd(final IPropertiesConstraint... propertiesConstraints) {
		Assert.isNotNull(propertiesConstraints, "propertiesConstraints"); //$NON-NLS-1$
		this.propertiesConstraints = propertiesConstraints;
	}

	/**
	 * @return <code>true</code> if all properties in the list of properties
	 *         satisfies all of the properties constraints
	 */
	@Override
	public boolean satisfiedBy(final Collection<Property> properties) {
		for (IPropertiesConstraint propertiesConstraint : propertiesConstraints) {
			if (!propertiesConstraint.satisfiedBy(properties)) {
				return false;
			}
		}
		return true;
	}

}
