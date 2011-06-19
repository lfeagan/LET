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

/**
 * Transforms a string by prepending (placing in front) a prefix string.
 */
public class StringTransformAddPrefix implements IStringTransformer {

	/**
	 * The string to be placed in front of the input string.
	 */
	private final String prefix;
	
	/**
	 * Constructor for a string transformer that prepends the prefix string in front of a
	 * input string.
	 * 
	 * @param prefix
	 *            the string to prepend in front of a input string
	 */
	public StringTransformAddPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Returns the prefix string that is prepended a input string
	 * @return
	 */
	public String getPrefix() {
		return this.prefix;
	}
	
	/**
	 * Transforms a string by prepending the prefix string
	 * 
	 * @return a new string that has the prefix string prepended in front of the
	 *         input string
	 */
	@Override
	public String transform(String input) {
		return prefix + input;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<"); //$NON-NLS-1$
		sb.append(StringTransformAddPrefix.class.getSimpleName());
		sb.append(" prefix=\""); //$NON-NLS-1$
		sb.append(prefix);
		sb.append("\">"); //$NON-NLS-1$
		return sb.toString();
	}

}
