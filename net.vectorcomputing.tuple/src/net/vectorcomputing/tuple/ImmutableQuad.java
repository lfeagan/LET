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
 * A {@link Quad} that <b>cannot</b> be modified after constructed.
 * 
 * @param <T1>
 *            the type constraint to place on the first element of the quad
 * @param <T2>
 *            the type constraint to place on the second element of the quad
 * @param <T3>
 *            the type constraint to place on the third element of the quad
 * @param <T4>
 *            the type constraint to place on the fourth element of the quad
 */
public class ImmutableQuad<T1, T2, T3, T4> implements Quad<T1, T2, T3, T4> {

	private static final String SIMPLE_CLASS_NAME = ImmutableQuad.class.getSimpleName();
	private static final int size = 4;

	private final transient int hashcode;

	private final T1 first;
	private final T2 second;
	private final T3 third;
	private final T4 fourth;

	public ImmutableQuad(T1 first, T2 second, T3 third, T4 fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.hashcode = generateHashCode();
	}
	
	@Override
	public final Object[] entries() {
		final Object[] values = new Object[4];
		values[0] = first;
		values[1] = second;
		values[2] = third;
		values[3] = fourth;
		return values;
	}

	@Override
	public int size() {
		return size;
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
			throw new IndexOutOfBoundsException(Messages.Quad_GetEntryFailedIndexConstraint);
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
	public T4 getFourth() {
		return fourth;
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
		result = prime * result + ((fourth == null) ? 0 : fourth.hashCode());
		return result;
	}

	/**
	 * Returns the String representation of this quad.
	 */
	@Override
	public String toString() {
		return Tuples.toString(this, SIMPLE_CLASS_NAME);
	}

}
