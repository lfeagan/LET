package net.vectorcomputing.serialization.xml.java.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.vectorcomputing.net.serialization.xml.java.lang.CharacterXmlSerializer;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerialization;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;
import net.vectorcomputing.serialization.xml.XmlSerializer;

import org.eclipse.core.runtime.CoreException;
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
	public void testSpaceCharacterOutput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(SPACE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(SPACE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Character/>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testSingleCharacterOutput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(SINGLE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(SINGLE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Character>simple</java.lang.Character>\n")); //$NON-NLS-1$
	}
	
	@Test
	public void testSpaceCharacterInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Character> </java.lang.Character>\n"; //$NON-NLS-1$final Object object = ObjectXmlSerialization.read(reference);
		final Object object = XmlSerialization.read(reference);		
		assertNotNull(object);
		assertEquals(SPACE, object);
	}
	
	@Test
	public void testSingleCharacterInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Character>s</java.lang.Character>\n"; //$NON-NLS-1$
		final Object object = XmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(SINGLE, object);
	}
	
	@Test
	public void testEquals() {
		final XmlSerializer constructed = new CharacterXmlSerializer();
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(SINGLE.getClass());
		assertEquals(constructed, descriptor.getXmlSerializer());
	}

}
