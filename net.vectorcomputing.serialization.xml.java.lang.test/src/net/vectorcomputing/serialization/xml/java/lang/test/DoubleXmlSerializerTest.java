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
import net.vectorcomputing.net.serialization.xml.java.lang.DoubleXmlSerializer;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.ObjectXmlSerialization;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;

import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoubleXmlSerializerTest {
	
	private static IXmlSerializerRegistry registry;
	private static Double MIN_VALUE;
	private static Double MAX_VALUE;
	private static Double ZERO;
	private static Double NaN;
	
	@BeforeClass
	public static void init() {
		registry = XmlSerializationPlugin.getRegistry();		
		MIN_VALUE = Double.valueOf(Double.MIN_VALUE);
		MAX_VALUE = Double.valueOf(Double.MAX_VALUE);
		NaN = Double.valueOf(Double.NaN);
		ZERO = Double.valueOf(0.0f);
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
	public void testRandomOutput() throws CoreException {
		final Double myDouble = new Double(1.23);
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(myDouble.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(myDouble);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Double>1.23</java.lang.Double>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testMinOuput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MIN_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MIN_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Double>4.9E-324</java.lang.Double>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMaxOuput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MAX_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MAX_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Double>1.7976931348623157E308</java.lang.Double>\n")); //$NON-NLS-1$
	}

	@Test
	public void testZeroOuput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(ZERO.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(ZERO);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Double>0.0</java.lang.Double>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testNaNOuput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(NaN.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(NaN);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Double>NaN</java.lang.Double>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMinInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Double>4.9E-324</java.lang.Double>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MIN_VALUE, object);
	}

	@Test
	public void testMaxInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Double>1.7976931348623157E308</java.lang.Double>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MAX_VALUE, object);
	}

	@Test
	public void testZeroInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Double>0.0</java.lang.Double>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(ZERO, object);
	}
	
	@Test
	public void testNaNInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Double>NaN</java.lang.Double>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(NaN, object);
	}
	
	@Test
	public void testEquals() {
		final DoubleXmlSerializer constructed = new DoubleXmlSerializer();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(NaN.getClass());
		assertEquals(constructed, descriptor.getXmlSerializer());
	}
	
}
