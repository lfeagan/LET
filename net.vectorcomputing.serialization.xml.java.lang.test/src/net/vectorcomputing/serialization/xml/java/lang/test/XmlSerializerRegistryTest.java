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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;
import net.vectorcomputing.serialization.xml.XmlSerializerNotFoundException;

import org.junit.Test;

public class XmlSerializerRegistryTest {

	@Test
	public void getRegistryTest() {
		assertNotNull(XmlSerializationPlugin.getRegistry());
	}
	
	@Test
	public void getXmlSerializerByIdTestNegative() throws XmlSerializerNotFoundException {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor;
		descriptor = registry.findXmlSerializer("a random id");
		assertNull(descriptor);
		descriptor = registry.findXmlSerializer("aRandomId");
		assertNull(descriptor);
	}
	
	@Test
	public void getXmlSerializerByIdTest() throws XmlSerializerNotFoundException {
		final String ID = "net.vectorcomputing.net.serialization.xml.java.lang.IntegerXmlSerializer";
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializer(ID);
		assertNotNull(descriptor);
		assertEquals(ID, descriptor.getId());
		assertNotNull(descriptor.getName());
		assertEquals(registry, descriptor.getRegistry());
		assertNotNull(descriptor.getTag());
		assertTrue(descriptor.getTag().length() > 0);
		assertNotNull(descriptor.getHandledClass());
		assertEquals(Integer.class, descriptor.getHandledClass());
		assertNotNull(descriptor.getXmlSerializer());
	}
	
	@Test(expected=XmlSerializerNotFoundException.class)
	public void getXmlSerializerByClassTestNegative1() throws XmlSerializerNotFoundException {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		registry.findXmlSerializerForClass(Object.class);
	}

	@Test(expected=XmlSerializerNotFoundException.class)
	public void getXmlSerializerByClassTestNegative2() throws XmlSerializerNotFoundException {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		registry.findXmlSerializerForClass(Iterable.class);
	}

	@Test
	public void getXmlSerializerByClassTest() throws XmlSerializerNotFoundException {
		final String ID = "net.vectorcomputing.net.serialization.xml.java.lang.IntegerXmlSerializer";
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(Integer.class);
		assertNotNull(descriptor);
		assertEquals(ID, descriptor.getId());
		assertNotNull(descriptor.getName());
		assertEquals(registry, descriptor.getRegistry());
		assertNotNull(descriptor.getTag());
		assertTrue(descriptor.getTag().length() > 0);
		assertNotNull(descriptor.getHandledClass());
		assertEquals(Integer.class, descriptor.getHandledClass());
		assertNotNull(descriptor.getXmlSerializer());
	}
	
	@Test(expected=XmlSerializerNotFoundException.class)
	public void getXmlSerializerByClassNameTestNegative1() throws XmlSerializerNotFoundException {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		registry.findXmlSerializerForClass("java.lang.Integerr");
	}

	@Test(expected=XmlSerializerNotFoundException.class)
	public void getXmlSerializerByClassNameTestNegative2() throws XmlSerializerNotFoundException {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		registry.findXmlSerializerForClass("java.lang.Intege");
	}

	@Test(expected=XmlSerializerNotFoundException.class)
	public void getXmlSerializerByClassNameTestNegative3() throws XmlSerializerNotFoundException {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		registry.findXmlSerializerForClass("java.lang.Object");
	}
	
	@Test
	public void getXmlSerializerByClassNameTest() throws XmlSerializerNotFoundException {
		final String ID = "net.vectorcomputing.net.serialization.xml.java.lang.IntegerXmlSerializer";
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass("java.lang.Integer");
		assertNotNull(descriptor);
		assertEquals(ID, descriptor.getId());
		assertNotNull(descriptor.getName());
		assertEquals(registry, descriptor.getRegistry());
		assertNotNull(descriptor.getTag());
		assertTrue(descriptor.getTag().length() > 0);
		assertNotNull(descriptor.getHandledClass());
		assertEquals(Integer.class, descriptor.getHandledClass());
		assertNotNull(descriptor.getXmlSerializer());
	}
	
	
	@Test
	public void getXmlSerializerByTagTest() throws XmlSerializerNotFoundException {
		final String ID = "net.vectorcomputing.net.serialization.xml.java.lang.IntegerXmlSerializer";
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerWithTag("java.lang.Integer");
		assertNotNull(descriptor);
		assertEquals(ID, descriptor.getId());
		assertNotNull(descriptor.getName());
		assertEquals(registry, descriptor.getRegistry());
		assertNotNull(descriptor.getTag());
		assertTrue(descriptor.getTag().length() > 0);
		assertNotNull(descriptor.getHandledClass());
		assertEquals(Integer.class, descriptor.getHandledClass());
		assertNotNull(descriptor.getXmlSerializer());
	}
	
	@Test
	public void getXmlSerializerByTagTestNegative() throws XmlSerializerNotFoundException {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor;
		descriptor = registry.findXmlSerializerWithTag("java.lang.Integerr");
		assertNull(descriptor);
		descriptor = registry.findXmlSerializerWithTag("java.lang.Intege");
		assertNull(descriptor);
		descriptor = registry.findXmlSerializerWithTag("java.lang.Object");
		assertNull(descriptor);
	}

	
	@Test
	public void getXmlSerializersTest() {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		final List<IXmlSerializerDescriptor> xmlSerializers = registry.getXmlSerializers();
		assertTrue(xmlSerializers.size() > 0);
	}
	
}
