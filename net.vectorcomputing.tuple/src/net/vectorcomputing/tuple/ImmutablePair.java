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
package net.vectorcomputing.tuple;

import net.vectorcomputing.base.Assert;


/**
 * A {@link Pair} that <b>cannot</b> be modified after constructed.
 * 
 * @param <T1>
 *            the type constraint to place on the first element of the pair
 * @param <T2>
 *            the type constraint to place on the second element of the pair
 */
public class ImmutablePair<T1, T2> implements Pair<T1, T2> {

	private static final String SIMPLE_CLASS_NAME = ImmutablePair.class.getSimpleName();
	private static final int SIZE = 2;

	private final transient int hashcode;

	private final T1 first;
	private final T2 second;

	/**
	 * Constructor for an immutable pair.
	 * 
	 * @param first
	 *            the first element of the pair
	 * @param second
	 *            the second element of the pair
	 */
	public ImmutablePair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
		this.hashcode = generateHashCode();
	}

	protected ImmutablePair(T1 first, T2 second, boolean assertFirstNotNull, boolean assertSecondNotNull) {
		if (assertFirstNotNull) {
			Assert.isNotNull(first, "first"); //$NON-NLS-1$
		}
		
		if (assertSecondNotNull) {
			Assert.isNotNull(second, "second"); //$NON-NLS-1$
		}
		
		this.first = first;
		this.second = second;
		this.hashcode = generateHashCode();		
	}
	
	/**
	 * Constructor for an immutable pair that uses the first and second values
	 * from an existing pair.
	 * 
	 * @param pair
	 *            the pair to read the first and second values from
	 */
	public ImmutablePair(Pair<T1,T2> pair) {
		this(pair.getFirst(), pair.getSecond());
	}
	
	@Override
	public final Object[] entries() {
		final Object[] values = new Object[2];
		values[0] = first;
		values[1] = second;
		return values;
	}

	@Override
	public int size() {
		return SIZE;
	}

	@Override
	public Object getEntry(int index) {
		switch (index) {
		case 0:
			return first;
		case 1:
			return second;
		default:
			throw new IndexOutOfBoundsException(TupleMessages.Pair_GetEntryFailedIndexConstraint);
		}
	}

	@Override
	public T1 getFirst() {
		return first;
	}

	@Override
	public T2 getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int generateHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	/**
	 * Returns the String representation of this triple.
	 */
	@Override
	public String toString() {
		return Tuples.toString(this, SIMPLE_CLASS_NAME);
	}

}
