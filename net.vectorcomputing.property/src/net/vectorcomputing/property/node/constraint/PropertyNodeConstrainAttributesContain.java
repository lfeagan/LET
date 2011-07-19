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
package net.vectorcomputing.property.node.constraint;

import java.util.ArrayList;
import java.util.List;

import net.vectorcomputing.property.Property;
import net.vectorcomputing.property.constraint.IPropertiesConstraint;
import net.vectorcomputing.property.constraint.IPropertyConstraint;
import net.vectorcomputing.property.constraint.PropertiesConstrainPropertyConstraintOr;
import net.vectorcomputing.property.constraint.PropertyConstrainEquals;
import net.vectorcomputing.property.node.PropertyNode;

import org.eclipse.core.runtime.Assert;

/**
 * A property node constraint that determines if the attributes of a property
 * node contain a particular property.
 */
public class PropertyNodeConstrainAttributesContain implements PropertyNodeConstraint {

	private final IPropertyConstraint propertyConstraint;
	private final IPropertiesConstraint propertiesConstraint;

	public PropertyNodeConstrainAttributesContain(final Property property) {
		Assert.isNotNull(property, "property"); //$NON-NLS-1$
		propertyConstraint = new PropertyConstrainEquals(property);
		propertiesConstraint = new PropertiesConstrainPropertyConstraintOr(propertyConstraint);
	}

	@Override
	public boolean satisfiedBy(final PropertyNode propertyNode) {
		Assert.isNotNull(propertyNode, "propertyNode"); //$NON-NLS-1$
		return propertiesConstraint.satisfiedBy(propertyNode.getAttributes());
	}

	public List<String> getMatchingValues(final PropertyNode propertyNode) {
		Assert.isNotNull(propertyNode, "propertyNode"); //$NON-NLS-1$
		final List<String> matchingValues = new ArrayList<String>();
		for (Property attribute : propertyNode.getAttributes()) {
			if (propertyConstraint.satisfiedBy(attribute)) {
				matchingValues.add(attribute.getValue());
			}
		}
		return matchingValues;
	}

}
