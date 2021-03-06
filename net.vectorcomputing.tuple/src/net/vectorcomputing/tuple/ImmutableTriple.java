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

/**
 * A {@link Triple} that <b>cannot</b> be modified after constructed.
 * 
 * @param <T1>
 *            the type constraint to place on the first element of the triple
 * @param <T2>
 *            the type constraint to place on the second element of the triple
 * @param <T3>
 *            the type constraint to place on the third element of the triple
 */
public class ImmutableTriple<T1, T2, T3> implements Triple<T1, T2, T3> {

	private static final String SIMPLE_CLASS_NAME = ImmutableTriple.class.getSimpleName();
	private static final int SIZE = 3;

	private final transient int hashcode;

	private final T1 first;
	private final T2 second;
	private final T3 third;

	public ImmutableTriple(T1 first, T2 second, T3 third) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.hashcode = generateHashCode();
	}
	
	@Override
	public final Object[] entries() {
		final Object[] values = new Object[3];
		values[0] = first;
		values[1] = second;
		values[2] = third;
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
		case 2:
			return third;
		default:
			throw new IndexOutOfBoundsException(TupleMessages.Triple_GetEntryFailedIndexConstraint);
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
	public T3 getThird() {
		return third;
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
		result = prime * result + ((third == null) ? 0 : third.hashCode());
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
