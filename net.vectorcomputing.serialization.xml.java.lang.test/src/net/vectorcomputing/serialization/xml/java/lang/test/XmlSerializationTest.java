package net.vectorcomputing.serialization.xml.java.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.vectorcomputing.base.string.StringIO;
import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.serialization.xml.XmlSerialization;

import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

public class XmlSerializationTest {
	
	@Test(expected = AssertionFailedException.class)
	public void readFileTestNull() throws CoreException {
		XmlSerialization.read((File) null);
	}
	
	@Test
	public void readFileTestEmpty() throws CoreException {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("TempFileForXmlSerialization", null);
			Object object = XmlSerialization.read(tempFile);
			assertNull(object);
		} catch (IOException e ) {
			org.junit.Assert.fail();
		} finally {
			if (tempFile != null) {
				tempFile.delete();
			}
		}
	}

	@Test(expected = AssertionFailedException.class)
	public void readInputStreamTestNull() throws CoreException {
		XmlSerialization.read((InputStream) null);
	}

	@Test
	public void readInputStreamTestEmpty() throws CoreException {
		XmlSerialization.read(new InputStream() {
			@Override
			public int read() throws IOException {
				return -1;
			}
		});
	}
	
	@Test(expected = AssertionFailedException.class)
	public void readPropertyNodeTestNull() throws CoreException {
		XmlSerialization.read((PropertyNode) null);
	}
	
	@Test(expected = CoreException.class)
	public void readPropertyNodeTestEmpty() throws CoreException {
		XmlSerialization.read(new PropertyNode("", ""));
	}
	
	@Test(expected = CoreException.class)
	public void readPropertyNodeTestEmpty2() throws CoreException {
		XmlSerialization.read(new PropertyNode("", (String) null));
	}

	@Test(expected = AssertionFailedException.class)
	public void readPropertyNodeTestEmpty3() throws CoreException {
		XmlSerialization.read(new PropertyNode((String) null, (String) null));
	}

	@Test(expected = AssertionFailedException.class)
	public void readStringTestNull() throws CoreException {
		XmlSerialization.read((String) null);
	}
	
	@Test
	public void readStringTestEmpty() throws CoreException {
		XmlSerialization.read("");
	}
	
	@Test(expected = CoreException.class)
	public void readStringTestEmpty2() throws CoreException {
		XmlSerialization.read(" ");
	}

	@Test
	public void readStringTest() throws CoreException {
		final String xml = "<java.lang.Integer>123</java.lang.Integer>\n";
		Object object = XmlSerialization.read(xml);
		assertNotNull(object);
		assertTrue(object instanceof Integer);
		assertEquals(new Integer(123), object);
	}

	@Test
	public void readInputStreamTest() throws CoreException, IOException {
		final Integer value = new Integer(123);
		final String string = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<java.lang.Integer>123</java.lang.Integer>\n";
		
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StringIO.write(string, baos, true);
		byte[] bytes = baos.toByteArray();
		InputStream is = new ByteArrayInputStream(bytes);
		final Object object = XmlSerialization.read(is);
		assertNotNull(object);
		assertTrue(object instanceof Integer);
		assertEquals(value, object);
	}

	@Test
	public void writeToFileTest() throws CoreException {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("TempFileForXmlSerialization", null);
			XmlSerialization.write(new Integer(123), tempFile);
		} catch (IOException e ) {
			org.junit.Assert.fail();
		} finally {
			if (tempFile != null) {
				tempFile.delete();
			}
		}
	}

	@Test
	public void writeToFileTest2() throws CoreException {
		final File tempFile = new File("TempFileForXmlSerialization");
		try {
			XmlSerialization.write(new Integer(123), tempFile);
		} finally {
			if (tempFile != null) {
				tempFile.delete();
			}
		}
	}
	
	@Test(expected = CoreException.class)
	public void writeToFileTestNegative() throws CoreException {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("TempFileForXmlSerialization", null);
			XmlSerialization.write(new Object(), tempFile);
		} catch (IOException e ) {
			org.junit.Assert.fail();
		} finally {
			if (tempFile != null) {
				tempFile.delete();
			}
		}
	}

	@Test(expected = CoreException.class)
	public void writeToFileTestNegative2() throws CoreException {
		final File tempFile = new File("/tmp/");
		try {
			XmlSerialization.write(new Integer(123), tempFile);
		} finally {
			if (tempFile != null) {
				tempFile.delete();
			}
		}
	}

	@Test
	public void writeToOutputStreamTest() throws CoreException {
		XmlSerialization.write(new Integer(123), new OutputStream() {
			@Override
			public void write(int b) throws IOException {
			}
		});
	}
	
	@Test(expected = CoreException.class)
	public void writeToOutputStreamTestNegative() throws CoreException {
		XmlSerialization.write(new Object(), new OutputStream() {
			@Override
			public void write(int b) throws IOException { }
		});
	}
	
	@Test
	public void toPropertyNodeTest() throws CoreException {
		final Integer value = new Integer(123);
		final PropertyNode pnode = XmlSerialization.toPropertyNode(value);
		assertNotNull(pnode);
		final Object object = XmlSerialization.read(pnode);
		assertNotNull(object);
		assertTrue(object instanceof Integer);
		assertEquals(value, object);
	}
	
	@Test(expected = CoreException.class)
	public void foo() throws CoreException {
		XmlSerialization.toPropertyNode(new Object());
		XmlSerialization.toString(new Object());
//		XmlSerialization.read(file)
	}
	
}