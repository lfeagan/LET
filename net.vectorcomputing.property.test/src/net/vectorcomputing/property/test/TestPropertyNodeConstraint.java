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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.vectorcomputing.base.string.constraint.StringConstrainEquals;
import net.vectorcomputing.property.ImmutableProperty;
import net.vectorcomputing.property.constraint.PropertiesConstrainPropertyConstraintAnd;
import net.vectorcomputing.property.constraint.PropertiesConstrainPropertyConstraintOr;
import net.vectorcomputing.property.constraint.PropertiesConstrainPropertyConstraintXor;
import net.vectorcomputing.property.constraint.IPropertiesConstraint;
import net.vectorcomputing.property.constraint.PropertiesConstraintAnd;
import net.vectorcomputing.property.constraint.PropertyConstrainKeyStringConstraint;
import net.vectorcomputing.property.constraint.IPropertyConstraint;
import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.property.node.constraint.PropertyNodeConstrainAttributes;
import net.vectorcomputing.property.node.constraint.PropertyNodeConstraint;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPropertyNodeConstraint {
	
	static PropertyNode rootWithNoAttrs;
	static PropertyNode rootWithAttrs;
	
	@BeforeClass
	public static void createPropertyNodes() {
		rootWithNoAttrs = new PropertyNode("root");
		rootWithAttrs = new PropertyNode("root");
		rootWithAttrs.addAttribute(new ImmutableProperty("one", "two"));
		rootWithAttrs.addAttribute(new ImmutableProperty("three", "four"));
	}
	
	@AfterClass
	public static void deletePropertyNodes() {
		rootWithNoAttrs = null;
		rootWithAttrs = null;
	}

	@Test
	public void atLeastOneTest() {
		final IPropertyConstraint keyIsOne = new PropertyConstrainKeyStringConstraint(new StringConstrainEquals("one"));
		IPropertiesConstraint atLeastOneAttr = new PropertiesConstrainPropertyConstraintOr(keyIsOne);
		PropertyNodeConstraint atLeastOneNode = new PropertyNodeConstrainAttributes(atLeastOneAttr);
		assertTrue(atLeastOneNode.satisfiedBy(rootWithAttrs));
	}
	
	@Test
	public void exactlyOneTest() {
		final IPropertyConstraint keyIsOne = new PropertyConstrainKeyStringConstraint(new StringConstrainEquals("one"));
		IPropertiesConstraint exactlyOneAttr = new PropertiesConstrainPropertyConstraintXor(keyIsOne);
		PropertyNodeConstraint exactlyOneNode = new PropertyNodeConstrainAttributes(exactlyOneAttr);
		assertTrue(exactlyOneNode.satisfiedBy(rootWithAttrs));
	}

	@Test
	public void allOneTest() {
		final IPropertyConstraint keyIsOne = new PropertyConstrainKeyStringConstraint(new StringConstrainEquals("one"));
		IPropertiesConstraint allOneAttr = new PropertiesConstrainPropertyConstraintAnd(keyIsOne);
		PropertyNodeConstraint allOneNode = new PropertyNodeConstrainAttributes(allOneAttr);
		assertFalse(allOneNode.satisfiedBy(rootWithAttrs));
	}
	
	@Test
	public void exactlyOneAndThree() {
		final IPropertyConstraint keyIsOne = new PropertyConstrainKeyStringConstraint(new StringConstrainEquals("one"));
		final IPropertyConstraint keyIsThree = new PropertyConstrainKeyStringConstraint(new StringConstrainEquals("three"));
		IPropertiesConstraint exactlyOneOne = new PropertiesConstrainPropertyConstraintXor(keyIsOne);
		IPropertiesConstraint exactlyOneThree = new PropertiesConstrainPropertyConstraintXor(keyIsThree);
		IPropertiesConstraint oneAndThree = new PropertiesConstraintAnd(exactlyOneOne, exactlyOneThree);
		PropertyNodeConstraint nodeConstraint = new PropertyNodeConstrainAttributes(oneAndThree);
		assertTrue(nodeConstraint.satisfiedBy(rootWithAttrs));
	}

	
}
