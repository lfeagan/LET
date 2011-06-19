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
package net.vectorcomputing.base.string.constraint;

/**
 * A string constraint that does a case-sensitive comparison of the beginning
 * (prefix) of a string with a reference string.
 */
public class StringConstrainStartsWith implements IStringConstraint {

	private final String prefix;
	
	/**
	 * @param prefix
	 *            the string that the input string must begin with
	 *            (case-sensitive)
	 */
	public StringConstrainStartsWith(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return this.prefix;
	}

	/**
	 * @return <code>true</code> if the beginning of the string is equal to the
	 *         prefix string (case-sensitive)
	 */
	@Override
	public boolean satisfiedBy(String input) {
		return input.startsWith(prefix);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BeginsWith prefix=\"");
		sb.append(prefix);
		sb.append("\"/>");
		return sb.toString();
	}
	
}
