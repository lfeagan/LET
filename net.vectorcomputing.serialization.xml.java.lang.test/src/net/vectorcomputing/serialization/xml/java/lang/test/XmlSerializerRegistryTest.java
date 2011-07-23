package net.vectorcomputing.serialization.xml.java.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;

import org.junit.Test;

public class XmlSerializerRegistryTest {

	@Test
	public void getRegistryTest() {
		assertNotNull(XmlSerializationPlugin.getRegistry());
	}
	
	@Test
	public void getXmlSerializerByIdTestNegative() {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor;
		descriptor = registry.findXmlSerializer("a random id");
		assertNull(descriptor);
		descriptor = registry.findXmlSerializer("aRandomId");
		assertNull(descriptor);
	}
	
	@Test
	public void getXmlSerializerByIdTest() {
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
	
	@Test
	public void getXmlSerializerByClassTestNegative() {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor;
		descriptor = registry.findXmlSerializerForClass(Object.class);
		assertNull(descriptor);
		descriptor = registry.findXmlSerializerForClass(Iterable.class);
		assertNull(descriptor);
	}
	
	@Test
	public void getXmlSerializerByClassTest() {
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
	
	@Test
	public void getXmlSerializerByClassNameTestNegative() {
		final IXmlSerializerRegistry registry = XmlSerializationPlugin.getRegistry();
		IXmlSerializerDescriptor descriptor;
		descriptor = registry.findXmlSerializerForClass("java.lang.Integerr");
		assertNull(descriptor);
		descriptor = registry.findXmlSerializerForClass("java.lang.Intege");
		assertNull(descriptor);
		descriptor = registry.findXmlSerializerForClass("java.lang.Object");
		assertNull(descriptor);
	}
	
	@Test
	public void getXmlSerializerByClassNameTest() {
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
	public void getXmlSerializerByTagTest() {
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
	public void getXmlSerializerByTagTestNegative() {
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
