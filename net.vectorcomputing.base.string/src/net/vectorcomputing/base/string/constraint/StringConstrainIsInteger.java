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
 * A string constraint that determines if a string represents an integer. The
 * default radix is 10.
 */
public class StringConstrainIsInteger implements IStringConstraint {

	private final int radix;

	/**
	 * Constructs a string constraint that test if a string represents an
	 * integer with a radix of 10.
	 */
	public StringConstrainIsInteger() {
		this.radix = 10;
	}

	/**
	 * Constructs a string constraint that tests if a string represents an
	 * integer with a custom radix. The radix must meet the constraints
	 * specified by {@link Integer#parseInt(String, int)}.
	 * 
	 * @param radix
	 *            the radix to use when determining if the string represents an
	 *            integer
	 */
	public StringConstrainIsInteger(int radix) {
		this.radix = radix;
	}

	/**
	 * Returns the radix used when determining if the string represents an
	 * integer
	 * 
	 * @return the radix used when determining if the string represents an
	 *         integer
	 */
	public int getRadix() {
		return radix;
	}
	
	/**
	 * Determines if the string represents an integer value
	 * 
	 * @return <code>true</code> if the string represents an integer value
	 */
	@Override
	public boolean satisfiedBy(String input) {
		try {
			Integer.parseInt(input, radix);
			return true;
		} catch (NumberFormatException e) { // $codepro.audit.disable logExceptions
			return false;
		}
	}
	
}
