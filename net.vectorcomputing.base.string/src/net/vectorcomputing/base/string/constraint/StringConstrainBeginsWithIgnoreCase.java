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

import net.vectorcomputing.base.string.Assert;

/**
 * A string constraint that does a case-insensitive comparison of the beginning
 * (prefix) of a string with a reference string.
 */
public class StringConstrainBeginsWithIgnoreCase implements IStringConstraint {

	private final String prefix;
	
	/**
	 * @param prefix
	 *            the string that the input string must start with
	 *            (case-insensitive)
	 */
	public StringConstrainBeginsWithIgnoreCase(final String prefix) {
		Assert.isNotNull(prefix, "prefix"); //$NON-NLS-1$
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	/**
	 * @return <code>true</code> if the beginning of the string is equal to the
	 *         prefix string (case-insensitive)
	 */
	@Override
	public boolean satisfiedBy(final String input) {
		// the input must be at least at long as the prefix for them to match
		if (input.length() < prefix.length()) {
			return false;
		} else {
			final String inputBeginning = input.substring(0, prefix.length());
			return inputBeginning.equalsIgnoreCase(prefix);
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<BeginsWithIgnoreCase prefix=\""); //$NON-NLS-1$
		sb.append(prefix);
		sb.append("\"/>"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
