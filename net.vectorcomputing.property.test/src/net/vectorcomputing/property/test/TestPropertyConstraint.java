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
package net.vectorcomputing.property.test;

import static org.junit.Assert.*;
import net.vectorcomputing.base.string.constraint.StringConstrainEquals;
import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.property.ImmutableProperty;
import net.vectorcomputing.property.constraint.PropertyConstrainKeyStringConstraint;
import net.vectorcomputing.property.constraint.IPropertyConstraint;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPropertyConstraint {

	static ImmutableProperty keyOnly;
	
	@BeforeClass
	public static void initProperties() {
		keyOnly = new ImmutableProperty("key", null);
	}
	
	@AfterClass
	public static void deinitProperties() {
		keyOnly = null;
	}
	
	@Test
	public void foo() {
		IStringConstraint keyConstraint = new StringConstrainEquals("key");
		IPropertyConstraint pc = new PropertyConstrainKeyStringConstraint(keyConstraint);
		assertTrue(pc.satisfiedBy(keyOnly));
	}
	
}
