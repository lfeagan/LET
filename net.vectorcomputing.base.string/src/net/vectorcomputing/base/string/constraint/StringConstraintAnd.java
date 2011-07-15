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
 *  A {@link IStringConstraint} that ANDs multiple {@link IStringConstraint}s together.
 */
public class StringConstraintAnd implements IStringConstraint {

	private final IStringConstraint[] stringConstraints;
	
	/**
	 * Constructor for a string constraint that ANDs multiple
	 * {@link IStringConstraint}s together
	 * 
	 * @param stringConstraints the string constraints a input string must satisfy
	 */
	public StringConstraintAnd(final IStringConstraint...stringConstraints) {
		Assert.isNotNull(stringConstraints, "stringConstraints"); //$NON-NLS-1$
		this.stringConstraints = stringConstraints;
	}

	/**
	 * @return <code>true</code> if the input string satisfies all of the
	 *         {@link IStringConstraint}s or <code>false</code> if the
	 *         input string does not satisfy all of the
	 *         {@link IStringConstraint}s.
	 */
	@Override
	public boolean satisfiedBy(final String input) {
		for (IStringConstraint stringMatcher : stringConstraints) {
			if (!stringMatcher.satisfiedBy(input)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<StringConstraintAnd>"); //$NON-NLS-1$
		for (IStringConstraint stringConstraint : stringConstraints) {
			sb.append(stringConstraint.toString());
		}
		sb.append("</StringConstraintAnd>"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
