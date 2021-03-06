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
 * A properties constraint that XORs multiple property constraints together
 * against multiple properties.
 */
public class PropertiesConstraintXor implements IPropertiesConstraint {

	private final IPropertiesConstraint[] propertiesConstraints;

	/**
	 * @param propertiesConstraints
	 *            the properties constraints that must be satisfied
	 */
	public PropertiesConstraintXor(final IPropertiesConstraint...propertiesConstraints) {
		Assert.isNotNull(propertiesConstraints, "propertiesConstraints"); //$NON-NLS-1$
		this.propertiesConstraints = propertiesConstraints;
	}
	
	@Override
	public boolean satisfiedBy(final Collection<Property> properties) {
		int satisfiedByCount = 0;
		for (IPropertiesConstraint propertiesConstraint : propertiesConstraints) {
			if (propertiesConstraint.satisfiedBy(properties)) {
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
