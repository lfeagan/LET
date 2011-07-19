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

import org.eclipse.core.runtime.Assert;

import net.vectorcomputing.property.Property;

/**
 * A property constraint that determines if a property is equal to another.
 */
public class PropertyConstrainEquals implements IPropertyConstraint {
	
	private final Property referenceProperty;

	/**
	 * 
	 * @param referenceProperty
	 *            the property to test for equality against
	 */
	public PropertyConstrainEquals(final Property referenceProperty) {
		Assert.isNotNull(referenceProperty, "referenceProperty"); //$NON-NLS-1$
		this.referenceProperty = referenceProperty;
	}
	
	/**
	 * @return <code>true</code> if the property equals the reference property
	 */
	@Override
	public boolean satisfiedBy(final Property property) {
		if (property == null) {
			return false;
		} else {
			return referenceProperty.equals(property); 
		}
	}

}
