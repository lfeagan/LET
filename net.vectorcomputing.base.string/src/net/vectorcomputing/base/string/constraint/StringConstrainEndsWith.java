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

import net.vectorcomputing.base.Assert;

/**
 * A string constraint that does a case-sensitive comparison of the end
 * (suffix) of a string with a reference string.
 */
public class StringConstrainEndsWith implements IStringConstraint {

	private final String suffix;
	
	/**
	 * @param suffix
	 *            the string that the input string must end with
	 *            (case-sensitive)
	 */
	public StringConstrainEndsWith(final String suffix) {
		Assert.isNotNull(suffix, "suffix"); //$NON-NLS-1$
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return this.suffix;
	}
	
	/**
	 * @return <code>true</code> if the end of the string is equal to the suffix
	 *         string (case-sensitive)
	 */
	@Override
	public boolean satisfiedBy(final String input) {
		return input.endsWith(suffix);
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<EndsWith suffix=\""); //$NON-NLS-1$
		sb.append(suffix);
		sb.append("\"/>"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
