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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;

/**
 * A string constraint that determines if a string matches a regular expression
 * or pattern.
 * 
 */
public class StringConstrainMatchesPattern implements IStringConstraint {
	
	private final Pattern pattern;

	/**
	 * Constructs a string constraint that tests if a string matches a
	 * {@link Pattern}.
	 * 
	 * @param pattern
	 *            the {@link Pattern} a string must match
	 */
	public StringConstrainMatchesPattern(final Pattern pattern) {
		Assert.isNotNull(pattern, "pattern"); //$NON-NLS-1$
		this.pattern = pattern;
	}

	/**
	 * Constructs a string constraint that tests if a string matches a regular
	 * expression (pattern).
	 * 
	 * @param regex
	 *            the regular expression a string must match
	 */
	public StringConstrainMatchesPattern(final String regex) {
		Assert.isNotNull(regex, "regex"); //$NON-NLS-1$
		this.pattern = Pattern.compile(regex);
	}

	/**
	 * Returns the pattern a string must match
	 * 
	 * @return the pattern a string must match
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * @return <code>true</code> if the string matches the pattern
	 */
	@Override
	public boolean satisfiedBy(final String input) {
		final Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<MatchesPattern pattern=\""); //$NON-NLS-1$
		sb.append(pattern);
		sb.append("\"/>"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
