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
 * A synchronized version of {@link MutablePair}.
 * 
 * @param <T1>
 *            the type constraint to place on the first element of the pair
 * @param <T2>
 *            the type constraint to place on the second element of the pair
 */
public class SynchronizedPair<T1, T2> extends MutablePair<T1, T2> {

	public SynchronizedPair(T1 first, T2 second) {
		super(first, second);
	}

	@Override
	public synchronized Object getEntry(int index) {
		return super.getEntry(index);
	}

	@Override
	public synchronized T1 getFirst() {
		return super.getFirst();
	}

	@Override
	public synchronized T2 getSecond() {
		return super.getSecond();
	}

	@Override
	public synchronized T1 setFirst(T1 newFirst) {
		return super.setFirst(newFirst);
	}

	@Override
	public synchronized T2 setSecond(T2 newSecond) {
		return super.setSecond(newSecond);
	}

	@Override
	public synchronized int hashCode() {
		return super.hashCode();
	}

	@Override
	public synchronized boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public synchronized String toString() {
		return super.toString();
	}

}
