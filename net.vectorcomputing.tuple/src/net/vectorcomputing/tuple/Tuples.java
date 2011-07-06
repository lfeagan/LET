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
 * Utility function for converting any tuple to a standardized string
 * representation.
 */
public final class Tuples {

	/**
	 * Builds and returns the String representation of this Tuple. Format:
	 * Pair<T1,T2>(first,second)
	 * 
	 * @param tuple
	 *            the pair to convert to a string representation
	 * @param label
	 *            the label to give to the pair, typically the class name
	 * @return the string representation of the pair
	 */
	public static final <T1, T2> String toString(Tuple tuple, String label) {
		final StringBuilder classNames = new StringBuilder();
		final StringBuilder strings = new StringBuilder();
		classNames.append(label);
		classNames.append('<');
		strings.append('(');
		final Object[] entries = tuple.entries();
		Object obj = null;
		for (int i = 0; i < entries.length; ++i) {
			obj = entries[i];
			if (obj == null) {
				classNames.append('?');
				strings.append('?');
			} else {
				classNames.append(obj.getClass().getName());
				strings.append(obj);
			}

			if (i < entries.length - 1) {
				classNames.append(',');
				strings.append(',');
			}
		}
		classNames.append('>');
		strings.append(')');

		classNames.append(strings.toString());

		return classNames.toString();
	}

}
