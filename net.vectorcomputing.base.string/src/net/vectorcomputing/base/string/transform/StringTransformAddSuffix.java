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
 * Transforms a string by appending (placing at the end) a suffix string.
 */
public class StringTransformAddSuffix implements IStringTransformer {
	
	/**
	 * The string to be placed at the end of the input string.
	 */
	private final String suffix;
	
	/**
	 * Constructor for a string transformer that appends the suffix string at the end of
	 * a input string.
	 * 
	 * @param suffix
	 *            the string to append at the end of a input string
	 */
	public StringTransformAddSuffix(String suffix)
	{
		this.suffix = suffix;
	}

	/**
	 * Returns the string that is appended to a input string
	 * 
	 * @return the string that is appended to a input string
	 */
	public String getSuffix() {
		return this.suffix;
	}
	
	/**
	 * Transforms a string by appending the suffix string
	 * 
	 * @return a new string that has the suffix string appended at the end of
	 *         the input string
	 */
	@Override
	public String transform(String input) {
		return input + suffix;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(StringTransformAddSuffix.class.getSimpleName());
		sb.append(" suffix=\"");
		sb.append(suffix);
		sb.append("\">");
		return sb.toString();
	}
	
}
