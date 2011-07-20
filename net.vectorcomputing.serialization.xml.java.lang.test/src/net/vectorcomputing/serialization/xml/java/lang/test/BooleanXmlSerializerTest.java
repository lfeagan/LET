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

public class BooleanXmlSerializerTest {

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
	public void testTrueOuput() throws CoreException {
		Boolean TRUE = Boolean.TRUE;
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(TRUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(TRUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Boolean>true</java.lang.Boolean>\n")); //$NON-NLS-1$
	}

	@Test
	public void testTrueInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Boolean>true</java.lang.Boolean>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(Boolean.TRUE, object);
	}

	@Test
	public void testFalseOutput() throws CoreException {
		Boolean FALSE = Boolean.FALSE;
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(FALSE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(FALSE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Boolean>false</java.lang.Boolean>\n")); //$NON-NLS-1$
	}

	@Test
	public void testFalseInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Boolean>false</java.lang.Boolean>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(Boolean.FALSE, object);
	}
}
