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

import static org.junit.Assert.assertNotNull;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;

import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoubleXmlSerializerTest {

	private static IXmlSerializerRegistry registry;
	
	@BeforeClass
	public static void init() {
		registry = XmlSerializationPlugin.getRegistry();
	}
	
	@AfterClass
	public static void deinit() {
		registry = null;
	}
	
	@Test
	public void foo() throws CoreException {
		Double myDouble = new Double(1.23);
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(myDouble.getClass());
		assertNotNull(descriptor);
		System.out.println(descriptor.toString());
		System.out.println(descriptor.toString(myDouble));
	}
}
