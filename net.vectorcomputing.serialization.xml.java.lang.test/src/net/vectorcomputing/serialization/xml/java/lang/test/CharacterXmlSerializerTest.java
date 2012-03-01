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
import net.vectorcomputing.serialization.xml.java.lang.CharacterXmlSerializer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CharacterXmlSerializerTest {

	private static IXmlSerializerRegistry registry;
	private static Character SPACE;
	private static Character SINGLE;
	
	@BeforeClass
	public static void init() {
		registry = XmlSerializationPlugin.getRegistry();
		SPACE = ' ';
		SINGLE = 's';
	}
	
	@AfterClass
	public static void deinit() {
		registry = null;
		SPACE = null;
		SINGLE = null;
	}
	
	
	@Test
	public void testSpaceCharacterOutput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(SPACE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(SPACE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Character> </java.lang.Character>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testSingleCharacterOutput() throws XmlSerializationException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(SINGLE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(SINGLE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Character>s</java.lang.Character>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testSpaceCharacterInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Character> </java.lang.Character>\n"; //$NON-NLS-1$final Object object = ObjectXmlSerialization.read(reference);
		final Object object = XmlSerialization.read(reference);		
		assertNotNull(object);
		assertEquals(SPACE, object);
	}
	
	@Test
	public void testSingleCharacterInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Character>s</java.lang.Character>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(SINGLE, object);
	}
	
	@Test
	public void testEquals() throws XmlSerializerNotFoundException {
		final XmlSerializer constructed = new CharacterXmlSerializer();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(SINGLE.getClass());
		assertEquals(constructed, descriptor.getXmlSerializer());
	}

}
