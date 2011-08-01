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
import net.vectorcomputing.net.serialization.xml.java.lang.FloatXmlSerializer;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerialization;
import net.vectorcomputing.serialization.xml.XmlSerializationException;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;
import net.vectorcomputing.serialization.xml.XmlSerializerNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FloatXmlSerializerTest {

	private static IXmlSerializerRegistry registry;
	private static Float MIN_VALUE;
	private static Float MAX_VALUE;
	private static Float ZERO;
	private static Float NaN;
	
	@BeforeClass
	public static void init() {
		registry = XmlSerializationPlugin.getRegistry();		
		MIN_VALUE = Float.valueOf(Float.MIN_VALUE);
		MAX_VALUE = Float.valueOf(Float.MAX_VALUE);
		NaN = Float.valueOf(Float.NaN);
		ZERO = Float.valueOf(0.0f);
	}
	
	@AfterClass
	public static void deinit() {
		registry = null;
		MIN_VALUE = null;
		MAX_VALUE = null;
		ZERO = null;
		NaN = null;
	}
	
	@Test
	public void testMinOuput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MIN_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MIN_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Float>-1.4E-45</java.lang.Float>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMaxOuput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MAX_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MAX_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Float>3.4028235E38</java.lang.Float>\n")); //$NON-NLS-1$
	}

	@Test
	public void testZeroOuput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(ZERO.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(ZERO);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Float>0.0</java.lang.Float>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testNaNOuput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(NaN.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(NaN);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Float>NaN</java.lang.Float>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMinInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Float>1.4E-45</java.lang.Float>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MIN_VALUE, object);
	}

	@Test
	public void testMaxInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Float>3.4028235E38</java.lang.Float>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MAX_VALUE, object);
	}

	@Test
	public void testZeroInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Float>0.0</java.lang.Float>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(ZERO, object);
	}
	
	@Test
	public void testNaNInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Float>NaN</java.lang.Float>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(NaN, object);
	}
	
	@Test
	public void testEquals() throws XmlSerializerNotFoundException {
		final FloatXmlSerializer constructed = new FloatXmlSerializer();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(NaN.getClass());
		assertEquals(constructed, descriptor.getXmlSerializer());
	}
	
}
