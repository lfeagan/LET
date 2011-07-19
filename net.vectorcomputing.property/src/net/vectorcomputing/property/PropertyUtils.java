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
package net.vectorcomputing.property;

import org.eclipse.core.runtime.Assert;

/**
 * Common utility functions for working with properties.
 */
public final class PropertyUtils {

	/**
	 * Converts a property to an immutable property. If the property argument is
	 * already an immutable property, simply casts and return it. If it is not,
	 * creates a new immutable property that contains references to the key and
	 * value from the property argument.
	 * 
	 * @param property
	 *            the property to convert to an immutable property
	 * @return an immutable property with the same key and value as the property
	 *         argument
	 */
	public static final ImmutableProperty toImmutableProperty(final Property property) {
		Assert.isNotNull(property, "property"); //$NON-NLS-1$
		if (property instanceof ImmutableProperty) {
			return (ImmutableProperty) property;
		} else {
			return new ImmutableProperty(property.getFirst(), property.getSecond());
		}
	}

	/**
	 * Converts a property to a mutable property. If the property argument is
	 * already an instance a mutable property, simply casts and return it. If it
	 * is not, creates a new mutable property that contains references to the
	 * key and value from the property argument.
	 * 
	 * @param property
	 *            the property to convert to a mutable property
	 * @return a mutable property with the same key and value as the property
	 *         argument
	 */
	public static final MutableProperty toMutableProperty(final Property property) {
		Assert.isNotNull(property, "property"); //$NON-NLS-1$
		if (property instanceof MutableProperty) {
			return (MutableProperty) property;
		} else {
			return new MutableProperty(property.getFirst(), property.getSecond());
		}		
	}
	
}
