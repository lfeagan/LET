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
	public void testMinOuput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MIN_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MIN_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Long>-9223372036854775808</java.lang.Long>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMaxOuput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(MAX_VALUE.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(MAX_VALUE);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Long>9223372036854775807</java.lang.Long>\n")); //$NON-NLS-1$
	}

	@Test
	public void testZeroOuput() throws CoreException {
		IXmlSerializerDescriptor descriptor = registry.findXmlSerializerForClass(ZERO.getClass());
		assertNotNull(descriptor);
		String output = descriptor.toString(ZERO);
//		System.out.println(output);
		assertTrue(output.endsWith("<java.lang.Long>0</java.lang.Long>\n")); //$NON-NLS-1$
	}

	@Test
	public void testMinInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Long>-9223372036854775808</java.lang.Long>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MIN_VALUE, object);
	}

	@Test
	public void testMaxInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Long>9223372036854775807</java.lang.Long>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(MAX_VALUE, object);
	}

	@Test
	public void testZeroInput() throws CoreException {
		final String reference = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Long>0</java.lang.Long>\n"; //$NON-NLS-1$
		final Object object = ObjectXmlSerialization.read(reference);
		assertNotNull(object);
		assertEquals(ZERO, object);
	}
	
}
