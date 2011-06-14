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
 * A string constraint that determines if an input string contains a reference
 * string.
 */
public class StringConstrainContains implements IStringConstraint {
	
	private final String reference;

	/**
	 * @param reference
	 *            the {@link String} that the input string must contain
	 */
	public StringConstrainContains(String reference) {
		this.reference = reference;
	}
	
	public String getReference() {
		return reference;
	}
	
	/**
	 * Determines if the input string contains the reference string
	 * 
	 * @return <code>true</code> if the string contains the reference string
	 */
	@Override
	public boolean satisfiedBy(String input) {
		return (input.indexOf(reference) >= 0);
	}
	
}
