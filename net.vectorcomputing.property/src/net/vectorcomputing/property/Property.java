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

import net.vectorcomputing.tuple.Pair;

/**
 * Specialization of Pair for use with Pair&lt;String,String&gt;.
 */
public interface Property extends Pair<String,String> {

	/**
	 * Returns the property's key. This is the same as {@link Pair#getFirst()}.
	 * 
	 * @return the property's key
	 */
	public String getKey();

	/**
	 * Returns the property's value. This is the same as {@link Pair#getSecond()}.
	 * 
	 * @return the property's value
	 */
	public String getValue();
	
}
