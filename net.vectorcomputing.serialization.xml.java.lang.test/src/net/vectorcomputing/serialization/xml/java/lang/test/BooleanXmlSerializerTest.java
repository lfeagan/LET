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
import net.vectorcomputing.net.serialization.xml.java.lang.BooleanXmlSerializer;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.XmlSerialization;
import net.vectorcomputing.serialization.xml.XmlSerializationException;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;
import net.vectorcomputing.serialization.xml.XmlSerializerNotFoundException;

import org.junit.Test;

public class BooleanXmlSerializerTest {
	
	@Test
	public void testTrueOuput() throws XmlSerializationException {		
		String output = XmlSerialization.toString(Boolean.TRUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Boolean>true</java.lang.Boolean>\n")); //$NON-NLS-1$
	}

	@Test
	public void testTrueInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Boolean>true</java.lang.Boolean>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(Boolean.TRUE, object);
	}

	@Test
	public void testFalseOutput() throws XmlSerializationException {
		String output = XmlSerialization.toString(Boolean.FALSE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Boolean>false</java.lang.Boolean>\n")); //$NON-NLS-1$
	}

	@Test
	public void testFalseInput() throws XmlSerializationException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Boolean>false</java.lang.Boolean>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(Boolean.FALSE, object);
	}
	
	@Test
	public void testEquals() throws XmlSerializerNotFoundException {
		final BooleanXmlSerializer constructed = new BooleanXmlSerializer();
		IXmlSerializerDescriptor descriptor = XmlSerializationPlugin.getRegistry().findXmlSerializerForClass(Boolean.TRUE.getClass());
		assertEquals(constructed, descriptor.getXmlSerializer());
	}
	
}
