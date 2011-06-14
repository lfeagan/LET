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
 * A string constraint that does a case-sensitive equality check of
 * a string against a reference string.
 */
public class StringConstrainEquals implements IStringConstraint {
	
	private final String reference;

	/**
	 * @param reference the string that the input string be equal to (case-sensitive)
	 */
	public StringConstrainEquals(String reference) {
		this.reference = reference;
	}
	
	public String getReference() {
		return reference;
	}
	
	/**
	 * @return <code>true</code> if the string is equal to the reference string
	 *         (case-sensitive)
	 */
	@Override
	public boolean satisfiedBy(String input) {
		return reference.equals(input);
	}
	
}
