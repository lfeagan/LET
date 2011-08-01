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
package net.vectorcomputing.base.string.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.base.string.constraint.StringConstrainBeginsWith;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringConstrainBeginsWithTest {

	private static String empty;
	private static String a;
	private static String ab;
	private static String abc;
	private static String abcd;
	
	@BeforeClass
	public static void init() {
		empty = ""; //$NON-NLS-1$
		a = "a"; //$NON-NLS-1$
		ab = "ab"; //$NON-NLS-1$
		abc = "abc"; //$NON-NLS-1$
		abcd = "abcd"; //$NON-NLS-1$
	}
	
	@AfterClass
	public static void deinit() {
		empty = null;
		a = null;
		ab = null;
		abc = null;
		abcd = null;
	}
	
	@Test (expected=AssertionError.class)
	public void nullPrefix() {
		new StringConstrainBeginsWith(null);
	}
	
	@Test
	public void emptyPrefix() {	
		final IStringConstraint constraint = new StringConstrainBeginsWith(empty);
		assertTrue(constraint.satisfiedBy(a));
		assertTrue(constraint.satisfiedBy(ab));
		assertTrue(constraint.satisfiedBy(abc));
		assertTrue(constraint.satisfiedBy(abcd));
	}
	
	@Test
	public void oneCharacterPrefix() {
		final IStringConstraint constraint = new StringConstrainBeginsWith(a);
		assertTrue(constraint.satisfiedBy(a));
		assertTrue(constraint.satisfiedBy(ab));
		assertTrue(constraint.satisfiedBy(abc));
		assertTrue(constraint.satisfiedBy(abcd));		
	}
	
	@Test
	public void twoCharacterPrefix() {
		final IStringConstraint constraint = new StringConstrainBeginsWith(ab);
		assertFalse(constraint.satisfiedBy(a));
		assertTrue(constraint.satisfiedBy(ab));
		assertTrue(constraint.satisfiedBy(abc));
		assertTrue(constraint.satisfiedBy(abcd));		
	}

	@Test
	public void threeCharacterPrefix() {
		final IStringConstraint constraint = new StringConstrainBeginsWith(abc);
		assertFalse(constraint.satisfiedBy(a));
		assertFalse(constraint.satisfiedBy(ab));
		assertTrue(constraint.satisfiedBy(abc));
		assertTrue(constraint.satisfiedBy(abcd));		
	}
	
	@Test
	public void fourCharacterPrefix() {
		final IStringConstraint constraint = new StringConstrainBeginsWith(abcd);
		assertFalse(constraint.satisfiedBy(a));
		assertFalse(constraint.satisfiedBy(ab));
		assertFalse(constraint.satisfiedBy(abc));
		assertTrue(constraint.satisfiedBy(abcd));		
	}
	
	@Test
	public void fiveCharacterPrefix() {
		final IStringConstraint constraint = new StringConstrainBeginsWith("abcde"); //$NON-NLS-1$
		assertFalse(constraint.satisfiedBy(a));
		assertFalse(constraint.satisfiedBy(ab));
		assertFalse(constraint.satisfiedBy(abc));
		assertFalse(constraint.satisfiedBy(abcd));		
	}
		
}
