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

import net.vectorcomputing.tuple.MutablePair;

/**
 * A property that can have its key and value modified (mutated) after being
 * constructed.
 */
public class MutableProperty extends MutablePair<String,String> implements IMutableProperty {

	/**
	 * Constructor for a mutable property.
	 * 
	 * @param key
	 *            the key to assign to the property
	 * @param value
	 *            the value to assign to the property
	 */
	public MutableProperty(final String key, final String value) {
		super(key, value, true, false);
	}
	
	/**
	 * Constructor for a mutable property that copies the key and value from an
	 * existing property.
	 * 
	 * @param property
	 *            the property to copy the key and value from
	 */
	public MutableProperty(final Property property) {
		this(property == null ? null : property.getKey(), property == null ? null : property.getValue());
	}

	@Override
	public String getKey() {
		return getFirst();
	}

	@Override
	public String getValue() {
		return getSecond();
	}

	@Override
	public String setKey(String newKey) {
		return setFirst(newKey);
	}

	@Override
	public String setValue(String newValue) {
		return setSecond(newValue);
	}

}
