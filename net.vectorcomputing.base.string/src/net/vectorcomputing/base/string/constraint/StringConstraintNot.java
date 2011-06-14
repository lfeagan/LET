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
 * A string constraint that returns the opposite (logical 'NOT') of an
 * underlying string constraint.
 * <p>
 * Example: A file extraction utility may provide a filter capability to exclude
 * matches. However, you may only want to extract files ending with ".java". To
 * address this you could either define a regular expression that matches all
 * files not ending in ".java" or create a string equality constraint for
 * ".java" and negate the result to turn it into the desired filter.
 * </p>
 * <code>new StringConstraintNot(new StringConstrainEndsWith(".java"))</code>
 */
public class StringConstraintNot implements IStringConstraint {

	private final IStringConstraint underlyingConstraint;

	/**
	 * Constructor for a string constraint that negates the returned value of an
	 * underlying string constraint.
	 * 
	 * @param underlyingConstraint
	 *            the string constraint the negate the result of satisfiedBy on
	 */
	public StringConstraintNot(IStringConstraint underlyingConstraint) {
		this.underlyingConstraint = underlyingConstraint;		
	}

	/**
	 * Returns the underlying string constraint that is negated
	 * 
	 * @return the underlying string constraint that is negated
	 */
	public IStringConstraint getUnderlyingConstraint() {
		return underlyingConstraint;
	}

	/**
	 * @return <code>true</code> if the underlying string constraint returns
	 *         <code>false</code> and vice-a-versa
	 */
	@Override
	public boolean satisfiedBy(String input) {
		return !this.underlyingConstraint.satisfiedBy(input);
	}
	
}
