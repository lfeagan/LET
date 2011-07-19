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
 * A property constraint that ANDs multiple property constraints together.
 */
public class PropertyConstraintAnd implements IPropertyConstraint {

	private final IPropertyConstraint[] constraints;

	/**
	 * Constructor for ANDing multiple property constraints together.
	 * 
	 * @param constraints
	 *            the property constraints to AND together
	 */
	public PropertyConstraintAnd(final IPropertyConstraint... constraints) {
		Assert.isNotNull(constraints, "constraints"); //$NON-NLS-1$
		this.constraints = constraints;
	}

	/**
	 * @return <code>true</code> if the property satisfies all of the property
	 *         constraints
	 */
	@Override
	public boolean satisfiedBy(final Property property) {
		for (IPropertyConstraint constraint : constraints) {
			if (!constraint.satisfiedBy(property)) {
				return false;
			}
		}
		return true;
	}

}
