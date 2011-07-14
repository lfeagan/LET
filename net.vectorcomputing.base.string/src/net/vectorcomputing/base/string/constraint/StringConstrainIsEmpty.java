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
 * A string constraint that tests if the string is empty.
 * <p>
 * This condition is met if:
 * <ol>
 * <li>the input string is null</li>
 * <li>the length of the input string after trimming it is zero</li>
 * </ol>
 * </p>
 */
public class StringConstrainIsEmpty implements IStringConstraint {

	/**
	 * @return <code>true</code> if the input string is null or has a trimmed
	 *         length of zero
	 */
	@Override
	public boolean satisfiedBy(final String input) {
		return (input == null || input.trim().length() == 0);
	}

	
	@Override
	public String toString() {
		return "<IsEmpty/>"; //$NON-NLS-1$
	}
	
}
