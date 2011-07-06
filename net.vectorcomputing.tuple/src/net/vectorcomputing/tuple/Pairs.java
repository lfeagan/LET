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

import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions for casting a list of mutable pairs to a list of immutable
 * pairs and vice-a-versa.
 */
public final class Pairs {
	
	/**
	 * Converts a list of mutable pairs into a list of immutable pairs
	 * 
	 * @param original
	 *            the list of mutable pairs to be converted
	 * @return a new list with the same content and ordering as the original
	 *         list but built using immutable pairs
	 */
	public static <T1,T2> List<ImmutablePair<T1,T2>> copyToImmutableList(List<MutablePair<T1,T2>> original) {
		List<ImmutablePair<T1,T2>> immutableList = new ArrayList<ImmutablePair<T1,T2>>();
		for (MutablePair<T1,T2> element : original) {
			immutableList.add(new ImmutablePair<T1, T2>(element));
		}
		return immutableList;
	}

	/**
	 * Converts an array of mutable pairs into an array of immutable pairs
	 * 
	 * @param original
	 *            the array of mutable pairs to be converted
	 * @return a new array with the same content and ordering as the original
	 *         array but built using immutable pairs
	 */
	@SuppressWarnings("unchecked")
	public static <T1,T2> ImmutablePair<T1,T2>[] copyToImmutableArray(List<MutablePair<T1,T2>> original) {
		return (ImmutablePair<T1,T2>[]) copyToImmutableList(original).toArray();
	}
	
}
