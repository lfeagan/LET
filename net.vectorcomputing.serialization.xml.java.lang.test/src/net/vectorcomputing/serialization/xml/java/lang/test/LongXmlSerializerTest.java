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
package net.vectorcomputing.serialization.xml.java.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerialization;
import net.vectorcomputing.serialization.xml.XmlSerializationException;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;
import net.vectorcomputing.serialization.xml.XmlSerializer;
import net.vectorcomputing.serialization.xml.XmlSerializerNotFoundException;
import net.vectorcomputing.serialization.xml.java.lang.LongXmlSerializer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LongXmlSerializerTest {

	private static IXmlSerializerRegistry registry;
	private static Long MIN_VALUE;
	private static Long MAX_VALUE;
	private static Long ZERO;
	
	@BeforeClass
	public static void init() {
		registry = XmlSerializationPlugin.getRegistry();
		MIN_VALUE = Long.valueOf(Long.MIN_VALUE);
		MAX_VALUE = Long.valueOf(Long.MAX_VALUE);
		ZERO = Long.valueOf(0);
	}
	
	@AfterClass
	public static void deinit() {
		registry = null;
		MIN_VALUE = null;
		MAX_VALUE = null;
		ZERO = null;
	}
	
	@Test
	public void testMinOuput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MIN_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MIN_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Long>-9223372036854775808</java.lang.Long>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMaxOuput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MAX_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MAX_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Long>9223372036854775807</java.lang.Long>\n")); //$NON-NLS-1$
	}

	@Test
	public void testZeroOuput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(ZERO.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(ZERO);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Long>0</java.lang.Long>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMinInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Long>-9223372036854775808</java.lang.Long>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MIN_VALUE, object);
	}

	@Test
	public void testMaxInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Long>9223372036854775807</java.lang.Long>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MAX_VALUE, object);
	}

	@Test
	public void testZeroInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Long>0</java.lang.Long>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(ZERO, object);
	}
	
	@Test
	public void testEquals() throws XmlSerializerNotFoundException {
		final XmlSerializer constructed = new LongXmlSerializer();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(ZERO.getClass());
		assertEquals(constructed, descriptor.getXmlSerializer());
	}
	
}
