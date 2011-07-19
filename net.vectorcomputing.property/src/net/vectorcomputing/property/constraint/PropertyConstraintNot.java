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
 * A property constraint that inverts (logical 'NOT') the result of the
 * underlying {@link IPropertyConstraint#satisfiedBy(Property)} method's
 * returned value.
 */
public class PropertyConstraintNot implements IPropertyConstraint {

	private final IPropertyConstraint constraint;

	/**
	 * Constructor for inverting the value returned by an underlying property
	 * constraint's {@link IPropertyConstraint#satisfiedBy(Property)} method.
	 * 
	 * @param constraint
	 *            the property constraint to invert the result of
	 *            {@link IPropertyConstraint#satisfiedBy(Property)} on
	 */
	public PropertyConstraintNot(final IPropertyConstraint constraint) {
		Assert.isNotNull(constraint, "constraint"); //$NON-NLS-1$
		this.constraint = constraint;
	}

	/**
	 * Returns the inverse of the value returned by the underlying property
	 * constraint.
	 * 
	 * @return <code>true</code> if the underlying property constraint returns
	 *         <code>false</code> and vice-a-versa
	 */
	@Override
	public boolean satisfiedBy(final Property property) {
		return (!constraint.satisfiedBy(property));
	}

}
