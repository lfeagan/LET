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
package net.vectorcomputing.property.node;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

import org.eclipse.core.runtime.CoreException;
import org.xml.sax.InputSource;

public interface IPropertyNodeXmlConverter {

	/**
	 * Transforms the content read from the reader into a property node
	 * hierarchy.
	 * 
	 * @param reader
	 *            the reader to read XML content from
	 * @return a property node hierarchy that represents the contents of the
	 *         reader
	 * @throws CoreException
	 */
	public PropertyNode read(Reader reader) throws CoreException;

	/**
	 * Transforms the content read from the input source into a property node
	 * hierarchy.
	 * 
	 * @param source
	 *            the source to read XML content from
	 * @return a property node hierarchy that represents the contents of the
	 *         input source
	 * @throws CoreException
	 */
	public PropertyNode read(InputSource source) throws CoreException;

	/**
	 * Transforms the content read from the string into a property node
	 * hierarchy.
	 * 
	 * @param string
	 *            the stream to read the property node from
	 * @return the property node read from the input source
	 * @throws CoreException
	 */
	public PropertyNode read(String string) throws CoreException;

	/**
	 * Transforms the content read from the file into a property node hierarchy.
	 * 
	 * @param file
	 *            the file to read XML content from
	 * @return a property node hierarchy that represents the contents of the
	 *         file
	 * @throws CoreException
	 */
	public PropertyNode read(File file) throws CoreException;

	/**
	 * Transforms the content read from source into a property node hierarchy.
	 * 
	 * @param stream
	 *            the input stream to read the property node from
	 * @return the property node read from the input stream
	 * @throws CoreException
	 */
	public PropertyNode read(InputStream stream) throws CoreException;

	/**
	 * Transforms the property node tree to its string representation using XML.
	 * 
	 * @param pnode the property node to be transformed
	 * @return a string that uses XML to represent the property node tree
	 * @throws CoreException
	 */
	public String toString(PropertyNode pnode) throws CoreException;

	/**
	 * Transforms the property node into an XML representation and writes that
	 * to a file.
	 * 
	 * @param pnode
	 *            the property node to transform
	 * @param file
	 *            the file to write the transformed representation to
	 * @throws CoreException
	 */
	public void write(PropertyNode pnode, File file) throws CoreException;

	/**
	 * Transforms the property node into an XML representation and writes that
	 * to a writer.
	 * 
	 * @param pnode the property node to transform
	 * @param writer the writer to write the transformed representation to
	 * @throws CoreException
	 */
	public void write(PropertyNode pnode, Writer writer) throws CoreException;
				
}
