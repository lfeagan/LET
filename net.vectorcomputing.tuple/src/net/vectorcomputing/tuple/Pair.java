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
 * Java version of a 2-tuple with generic type arguments.
 * 
 * @param <T1>
 *            the type constraint to place on the first element of the pair
 * @param <T2>
 *            the type constraint to place on the second element of the pair
 */
public interface Pair<T1, T2> extends Tuple {

	/**
	 * Returns the first value of this pair.
	 * 
	 * @return the first value of this pair
	 */
	public T1 getFirst();

	/**
	 * Returns the second value of this pair.
	 * 
	 * @return the second value of this pair
	 */
	public T2 getSecond();

}