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
 * A property constraint that XORs multiple property constraints together.
 */
public class PropertyConstraintXor implements IPropertyConstraint {

	private final IPropertyConstraint[] constraints;

	/**
	 * Constructor for XORing multiple property constraints together.
	 * 
	 * @param constraints
	 *            the property constraints to XOR together
	 */
	public PropertyConstraintXor(final IPropertyConstraint... constraints) {
		Assert.isNotNull(constraints, "constraints"); //$NON-NLS-1$
		this.constraints = constraints;
	}

	/**
	 * @return <code>true</code> if the property satisfies exactly one of the
	 *         property constraints
	 */
	@Override
	public boolean satisfiedBy(final Property property) {
		int satisfiedByCount = 0;
		for (IPropertyConstraint constraint : constraints) {
			if (constraint.satisfiedBy(property)) {
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
