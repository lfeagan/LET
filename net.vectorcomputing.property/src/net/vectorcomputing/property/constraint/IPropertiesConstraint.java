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

import net.vectorcomputing.property.Property;

/**
 * Interface for testing if a list of properties satisfy a constraint.
 * 
 * @author lfeagan
 * @since 1.0
 */
public interface IPropertiesConstraint {

	/**
	 * Determines if the properties satisfy the constraint.
	 * 
	 * @param properties
	 *            the properties the constraint is evaluated against
	 * @return <code>true</code> if the properties satisfy the constraint
	 */
	public boolean satisfiedBy(Collection<Property> properties);
	
}
