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
package net.vectorcomputing.base.string.transform;

import org.eclipse.core.runtime.Assert;

/**
 * A strings transformer that takes an array of input strings and concatenates
 * them together with a delimiter separating each element of the array 
 */
public class StringsTransformDelimitElements implements IStringsTransformer {

	/**
	 * The delimiter to place between elements of an input array of strings
	 */
	private final String delimiter;
	
	/**
	 * @param delimiter
	 *            the string to place between each string in an input array of
	 *            strings
	 */
	public StringsTransformDelimitElements(final String delimiter) {
		Assert.isNotNull(delimiter, "delimiter"); //$NON-NLS-1$
		this.delimiter = delimiter;
	}
	
	/**
	 * @return a new string that was created by iterating over each of the
	 *         elements in the input array of strings and placing the delimiter
	 *         string between them
	 */
	@Override
	public String transform(String[] input) {
		Assert.isNotNull(input, "input"); //$NON-NLS-1$
		final StringBuilder sb = new StringBuilder();
		for (int i=0; i < input.length; ) {
			sb.append(input[i]);
			++i;
			if (i < input.length) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<"); //$NON-NLS-1$
		sb.append(StringsTransformDelimitElements.class.getSimpleName());
		sb.append(" delimiter=\""); //$NON-NLS-1$
		sb.append(delimiter);
		sb.append("\">"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
