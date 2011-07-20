/*******************************************************************************
 * Copyright 2011 lfeagan
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
package net.vectorcomputing.serialization.xml.java.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.ObjectXmlSerialization;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;

import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringXmlSerializerTest {

	private static IXmlSerializerRegistry registry;
	private static String EMPTY_STRING;
	private static String SIMPLE_STRING;
	
	@BeforeClass
	public static void init() {
		registry = XmlSerializationPlugin.getRegistry();
		EMPTY_STRING = "";
		SIMPLE_STRING = "simple";
	}
	
	@AfterClass
	public static void deinit() {
		registry = null;
		EMPTY_STRING = null;
		SIMPLE_STRING = null;
	}
	
	
	@Test
	public void testEmptyStringOutput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(EMPTY_STRING.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(EMPTY_STRING);
		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.String/>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testSimpleStringOutput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(SIMPLE_STRING.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(SIMPLE_STRING);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.String>simple</java.lang.String>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testEmptyStringInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.String/>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(EMPTY_STRING, object);
	}
	
	@Test
	public void testSimpleStringInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.String>simple</java.lang.String>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(SIMPLE_STRING, object);
	}
	
}