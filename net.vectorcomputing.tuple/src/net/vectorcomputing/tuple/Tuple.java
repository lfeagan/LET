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
package net.vectorcomputing.tuple;

/**
 * Interface for accessing the fields of an N-tuple.
 */
public interface Tuple {

	/**
	 * Returns an array containing the entries stored in this tuple.
	 * 
	 * @return an array containing the entries stored in this tuple.
	 */
	public Object[] entries();

	/**
	 * Returns the object at the specified index in the tuple.
	 * 
	 * @param index
	 *            the offset into the tuple of the entry to return
	 * @return the object at the specified index in the tuple
	 * @throws IndexOutOfBoundsException
	 */
	public Object getEntry(int index);

	/**
	 * Returns the size of the tuple. For example, a pair is a 2-tuple and would
	 * therefore return 2. A triple is a 3-tuple and would therefore return 3.
	 * 
	 * @return the number of entries in the tuple
	 */
	public int size();

}
