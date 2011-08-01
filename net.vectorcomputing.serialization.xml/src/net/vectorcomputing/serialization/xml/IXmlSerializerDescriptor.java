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
package net.vectorcomputing.serialization.xml;

import java.io.InputStream;
import java.io.OutputStream;

import net.vectorcomputing.property.node.PropertyNode;

import org.eclipse.core.runtime.CoreException;

/**
 * Interface for accessing the attributes of a serializer configuration element.
 */
public interface IXmlSerializerDescriptor {

	public static final String ATTR_ID = "id";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_TAG = "tag";
	public static final String ATTR_CLASS = "class";
	public static final String ATTR_HANDLES = "handles";

	/**
	 * Returns the XML serializers's ID in the extension registry
	 * 
	 * @return the XML serializer's ID in the extension registry
	 */
	public String getId();

	/**
	 * Returns the name to use when presenting information about the XML
	 * serializer to users
	 * 
	 * @return the human-readable name of the XmlSerializer
	 */
	public String getName();

	/**
	 * Returns the key of the root node of an object that has been converted to
	 * a property node using the XML serializer.
	 * 
	 * @return the key of the root node of an object that has been converted to
	 *         a property node using the XML serializer.
	 */
	public String getTag();

	/**
	 * Returns the XML serializer this descriptor describes.
	 * 
	 * @return the XML serializer this descriptor describes.
	 * @throws XmlSerializationException
	 *             if unable to create an instance of the XML serializer
	 */
	public XmlSerializer getXmlSerializer();

	/**
	 * Returns the class this XML serializer accepts (serializes and
	 * de-serializes).
	 * 
	 * @return the class this XML serializer accepts (serializes and
	 *         de-serializes).
	 * @throws XmlSerializationException
	 *             if unable to create an instance of the accepted class
	 */
	public Class<?> getHandledClass();

	/**
	 * Returns the registry that created this XML serializer descriptor.
	 * 
	 * @return the registry that created this XML serializer descriptor.
	 */
	public IXmlSerializerRegistry getRegistry();

	/**
	 * Reads an object from a string that contains XML notation.
	 * 
	 * @param string
	 *            the string to read from
	 * @return the object read from the string
	 * @throws XmlSerializationException
	 *             if unable to read an object from the string
	 */
	public Object read(String string) throws XmlSerializationException;

	/**
	 * Reads an object from an input stream.
	 * 
	 * @param inputStream
	 *            the input stream to read from
	 * @return the object read from the input stream
	 * @throws XmlSerializationException
	 *             if unable to read an object from the input stream
	 */
	public Object read(InputStream inputStream) throws XmlSerializationException;

	/**
	 * Reads an object from a property node.
	 * 
	 * @param pnode
	 *            the property node to read from
	 * @return the object read from the property node
	 * @throws XmlSerializationException
	 *             if unable to read an object from the property node
	 */
	public Object read(PropertyNode pnode) throws XmlSerializationException;

	/**
	 * Converts the specified object to a property node.
	 * 
	 * @param object
	 *            the object to be converted
	 * @return a property node representation of the specified object
	 * @throws XmlSerializationException
	 *             if an error occurs during conversion
	 */
	public PropertyNode toPropertyNode(Object object) throws XmlSerializationException;

	/**
	 * Converts the specified object to a string representation.
	 * 
	 * @param object
	 *            the object to be converted
	 * @return the string representation of the specified object
	 * @throws XmlSerializationException
	 *             if the specified object could not be converted
	 */
	public String toString(Object object) throws XmlSerializationException;
	
	/**
	 * Converts and writes the specified object to an output stream.
	 * 
	 * @param object
	 *            the object to be converted
	 * @param outputStream
	 *            the output stream to write the converted object to
	 * @throws XmlSerializationException
	 */
	public void write(Object object, OutputStream outputStream) throws XmlSerializationException;

}
