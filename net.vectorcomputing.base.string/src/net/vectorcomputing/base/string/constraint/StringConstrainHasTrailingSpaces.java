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
 * A string constraint that tests if an input string has trailing spaces
 * (trailing = on the right-hand side).
 */
public class StringConstrainHasTrailingSpaces implements IStringConstraint {

	/**
	 * @return <code>true</code> if the input string has trailing spaces
	 *         (trailing = on the right-hand side)
	 */
	@Override
	public boolean satisfiedBy(final String input) {
		return input.matches(".*\\s+$"); //$NON-NLS-1$
	}
	
	@Override
	public String toString() {
		return "<HasTrailingSpaces/>"; //$NON-NLS-1$
	}

}
