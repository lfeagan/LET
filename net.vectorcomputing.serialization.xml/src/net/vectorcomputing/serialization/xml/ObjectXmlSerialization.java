/*******************************************************************************
 * Copyright 2011 lfeagan
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
package net.vectorcomputing.serialization.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;

import net.vectorcomputing.base.string.StringIO;
import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.property.node.PropertyNodeXmlConverter;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public final class ObjectXmlSerialization {

	// suppress default constructor
	private ObjectXmlSerialization() {
		throw new AssertionError();
	}
	
	/**
	 * Converts an object to a property node.
	 * 
	 * @param object
	 *            the object to be converted
	 * @return a property node representation of the specified object
	 * @throws CoreException
	 *             if an error occurs during conversion
	 */
	public static final PropertyNode toPropertyNode(final Object object) throws CoreException {
		Assert.isNotNull(object, "object"); //$NON-NLS-1$
		
		final IXmlSerializerDescriptor descriptor = XmlSerializationPlugin.getRegistry().findXmlSerializerForClass(object.getClass());
		if (descriptor == null) {
			final String message = MessageFormat.format(XmlSerializationMessages.XmlSerializer_UnableToFindXmlSerializerForClass, object.getClass().getName());
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, message));
		}
		return descriptor.toPropertyNode(object);
	}

	/**
	 * Converts an object to a property node and then to a string.
	 * 
	 * @param object
	 *            the object to be converted
	 * @return a string representation of the specified object using XML
	 *         notation
	 * @throws CoreException
	 *             if an error occurs during conversion
	 */
	public static final String toString(final Object object) throws CoreException {
		Assert.isNotNull(object, "object"); //$NON-NLS-1$

		final IXmlSerializerDescriptor descriptor = XmlSerializationPlugin.getRegistry().findXmlSerializerForClass(object.getClass());
		if (descriptor == null) {
			final String message = MessageFormat.format(XmlSerializationMessages.XmlSerializer_UnableToFindXmlSerializerForClass, object.getClass().getName());
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID,  message));
		}
		return descriptor.toString(object);
	}

	/**
	 * Converts an object to a property node and then writes that to an output
	 * stream using XML notation.
	 * 
	 * @param object
	 *            the object to be converted
	 * @param outputStream
	 *            the output stream to write the converted object to
	 * @throws CoreException
	 *             if an error occurs during the conversion
	 */
	public static final void write(final Object object, final OutputStream outputStream) throws CoreException {
		Assert.isNotNull(object, "object"); //$NON-NLS-1$
		Assert.isNotNull(outputStream, "outputStream"); //$NON-NLS-1$

		final IXmlSerializerDescriptor descriptor = XmlSerializationPlugin.getRegistry().findXmlSerializerForClass(object.getClass());
		if (descriptor == null) {
			final String message = MessageFormat.format(XmlSerializationMessages.XmlSerializer_UnableToFindXmlSerializerForClass, object.getClass().getName());
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, message));
		}
		descriptor.write(object, outputStream);
	}

	/**
	 * Converts an object to a property node and then writes that to a file.
	 * 
	 * @param object
	 *            the object to be converted
	 * @param file
	 *            the file to write the converted object to
	 * @throws CoreException
	 *             if an error occurs during the conversion
	 */
	public static final void write(final Object object, final File file) throws CoreException {
		Assert.isNotNull(object, "object"); //$NON-NLS-1$
		Assert.isNotNull(file, "file"); //$NON-NLS-1$

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			final FileWriter fw = new FileWriter(file);
			final String xmlString = toString(object);
			fw.write(xmlString);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.XmlSerializer_UnableToWriteObjectToFile, e));
		}
	}

	/**
	 * Reads an object from a file.
	 * 
	 * @param file
	 *            the file to read from
	 * @return the object read from the file
	 * @throws CoreException
	 *             if unable to read an object from the file
	 */
	public static final Object read(final File file) throws CoreException {
		Assert.isNotNull(file, "file"); //$NON-NLS-1$

		try {
			final String contents = StringIO.read(file);
			return read(contents);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.XmlSerializer_UnableToReadObjectFromFile, e));
		}
	}

	/**
	 * Reads an object from a string that contains XML notation.
	 * 
	 * @param xmlString
	 *            the string to read from
	 * @return the object read from the string
	 * @throws CoreException
	 *             if unable to read an object from the string
	 */
	public static final Object read(final String xmlString) throws CoreException {
		Assert.isNotNull(xmlString, "xmlString"); //$NON-NLS-1$

		if (xmlString.length() != 0) {
			final PropertyNode pnode = PropertyNodeXmlConverter.getSharedInstance().read(xmlString);
			return read(pnode);
		} else {
			return null;
		}
	}

	/**
	 * Reads an object from a property node.
	 * 
	 * @param pnode
	 *            the property node to read from
	 * @return the object read from the property node
	 * @throws CoreException
	 *             if unable to read an object from the property node
	 */
	public static final Object read(final PropertyNode pnode) throws CoreException {
		Assert.isNotNull(pnode, "pnode"); //$NON-NLS-1$

		final String tag = extractTag(pnode);
		final IXmlSerializerDescriptor descriptor = XmlSerializationPlugin.getRegistry().findXmlSerializerWithTag(tag);
		if (descriptor == null) {
			final String message = MessageFormat.format(XmlSerializationMessages.XmlSerializer_UnableToFindSerializerForTag, tag);
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID,  message)); 
		} else {
			return descriptor.read(pnode);
		}
	}

	/**
	 * Reads an object from an input stream.
	 * 
	 * @param inputStream
	 *            the input stream to read from
	 * @return the object read from the input stream
	 * @throws CoreException
	 *             if unable to read an object from the input stream
	 */
	public static final Object read(final InputStream inputStream) throws CoreException {
		Assert.isNotNull(inputStream, "inputStream"); //$NON-NLS-1$

		try {
			final String contents = StringIO.read(inputStream);
			return read(contents);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.XmlSerializer_UnableToReadObjectFromInputStream, e));
		}		
	}
	
	private static final String extractTag(final PropertyNode pnode) {
		assert pnode != null;
		return pnode.getKey();
	}

}
