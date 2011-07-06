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
 * A {@link Pair} that <b>can</b> be modified after constructed.
 * 
 * @see ImmutablePair
 * 
 * @param <T1>
 *            the type constraint to place on the first element of the pair
 * @param <T2>
 *            the type constraint to place on the second element of the pair
 */
public interface IMutablePair<T1, T2> extends Pair<T1, T2> {

	/**
	 * Sets the first element of this pair.
	 * 
	 * @param newFirst
	 *            the object to set as the first element of this pair
	 * @return the previous object stored as the first element of this pair
	 */
	public T1 setFirst(T1 newFirst);

	/**
	 * Sets the second element of this pair.
	 * 
	 * @param newSecond
	 *            the object to set as the second element of this pair
	 * @return the previous object stored as the second element of this pair
	 */
	public T2 setSecond(T2 newSecond);

}
