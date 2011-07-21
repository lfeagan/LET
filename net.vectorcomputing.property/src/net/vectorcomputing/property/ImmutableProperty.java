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

import net.vectorcomputing.tuple.ImmutablePair;

/**
 * A property that cannot be modified after constructed.
 */
public class ImmutableProperty extends ImmutablePair<String,String> implements Property {

	/**
	 * Constructor for an immutable property.
	 * 
	 * @param key
	 *            the key to assign to the property
	 * @param value
	 *            the value to assign to the property
	 */
	public ImmutableProperty(final String key, final String value) {
		super(key, value, true, false);
	}

	/**
	 * Constructor for an immutable property that copies the key and value from
	 * an existing property.
	 * 
	 * @param property
	 *            the property to copy the key and value from
	 */
	public ImmutableProperty(final Property property) {
		this((property == null) ? null : property.getKey(), (property == null) ? null : property.getValue());
	}

	@Override
	public String getKey() {
		return getFirst();
	}

	@Override
	public String getValue() {
		return getSecond();
	}

}
