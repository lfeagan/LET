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

import net.vectorcomputing.tuple.IMutablePair;

/**
 * Interface extending property to add setters for the key and value.
 */
public interface IMutableProperty extends Property, IMutablePair<String, String> {

	/**
	 * Assigns a new value to this property's key.
	 * 
	 * @param newKey
	 *            this property's new key
	 * @return the previous key
	 */
	public String setKey(String newKey);

	/**
	 * Assigns a new value to this property's value.
	 * 
	 * @param newValue
	 *            this property's new value
	 * @return the previous value or <code>null</code> if there was none
	 */
	public String setValue(String newValue);

}
