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
 * A properties constraint that returns the inverse (logical 'NOT') of the value
 * returned by the underlying properties constraint's
 * {@link IPropertiesConstraint#satisfiedBy(Collection)} method.
 */
public class PropertiesConstraintNot implements IPropertiesConstraint {

	private final IPropertiesConstraint propertiesConstraint;

	/**
	 * Constructor for a properties constraint that inverts the result of an
	 * underlying properties constraint.
	 * 
	 * @param propertiesConstraint
	 *            the properties constraint to invert the result of
	 *            {@link IPropertiesConstraint#satisfiedBy(Collection)} on
	 */
	public PropertiesConstraintNot(final IPropertiesConstraint propertiesConstraint) {
		Assert.isNotNull(propertiesConstraint, "propertiesConstraint"); //$NON-NLS-1$
		this.propertiesConstraint = propertiesConstraint;
	}

	/**
	 * @return <code>true</code> if the underlying property constraint's
	 *         {@link IPropertiesConstraint#satisfiedBy(Collection)} method returns
	 *         <code>false</code> and vice-a-versa
	 */
	@Override
	public boolean satisfiedBy(final Collection<Property> properties) {
		return (!propertiesConstraint.satisfiedBy(properties));
	}

}
