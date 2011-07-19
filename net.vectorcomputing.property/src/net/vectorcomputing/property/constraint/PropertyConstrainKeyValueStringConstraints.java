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

import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.property.Property;

/**
 * A property constraint that determines if a property's key satisfies a string
 * constraint and value satisfies an independent string constraint.
 */
public class PropertyConstrainKeyValueStringConstraints implements IPropertyConstraint {

	private final IStringConstraint keyConstraint;
	private final IStringConstraint valueConstraint;

	public PropertyConstrainKeyValueStringConstraints(
			final IStringConstraint keyConstraint, final IStringConstraint valueConstraint) {
		Assert.isNotNull(keyConstraint, "keyConstraint"); //$NON-NLS-1$
		Assert.isNotNull(valueConstraint, "valueConstraint"); //$NON-NLS-1$
		this.keyConstraint = keyConstraint;
		this.valueConstraint = valueConstraint;
	}

	/**
	 * @return <code>true</code> if the property satisfies the key and value
	 *         string constraints
	 */
	@Override
	public boolean satisfiedBy(final Property property) {
		if (property == null) {
			return false;
		} else {
			return keyConstraint.satisfiedBy(property.getKey())
					&& valueConstraint.satisfiedBy(property.getValue());
		}
	}

}
