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
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import net.vectorcomputing.node.NodePlugin;
import net.vectorcomputing.property.PropertyMessages;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PropertyNodeXmlConverter implements IPropertyNodeXmlConverter {
	
	private static IPropertyNodeXmlConverter sharedInstance = null;
	private static final DocumentXmlConverter converter = new DocumentXmlConverter();
	
	public static IPropertyNodeXmlConverter getSharedInstance() {
		synchronized(PropertyNodeXmlConverter.class) {
			if (sharedInstance == null) {
				sharedInstance = new PropertyNodeXmlConverter();
			}
		}
		return sharedInstance;
	}
	
	public static void setSharedInstance(final IPropertyNodeXmlConverter newSharedInstance) {
		Assert.isNotNull(newSharedInstance, "newSharedInstance"); //$NON-NLS-1$
		synchronized(PropertyNodeXmlConverter.class) {
			sharedInstance = newSharedInstance;
		}
	}

	/**
	 * Specified by: {@link DocumentXmlConverter#setIndentOutput(boolean)}.
	 */
	public void setIndentOutput(final boolean indentOutput) {
		converter.setIndentOutput(indentOutput);
	}

	/**
	 * Specified by: {@link DocumentXmlConverter#getIndentOutput()}.
	 */
	public boolean getIndentOutput() {
		return converter.getIndentOutput();
	}

	/**
	 * Specified by: {@link DocumentXmlConverter#setCharsToIndent(int)}.
	 */
	public void setCharsToIndent(final int charsToIndent) {
		converter.setCharsToIndent(charsToIndent);
	}

	/**
	 * Specified by: {@link DocumentXmlConverter#getCharsToIndent()}.
	 */
	public int getCharsToIndent() {
		return converter.getCharsToIndent();
	}
	
	@Override
	public PropertyNode read(final Reader reader) throws CoreException {
		Assert.isNotNull(reader, "reader"); //$NON-NLS-1$
		final InputSource source = new InputSource();
		source.setCharacterStream(reader);
		final Document doc = readDocument(source);
		return convertToPropertyNode(doc);		
	}
	
	@Override
	public PropertyNode read(final InputStream stream) throws CoreException {
		Assert.isNotNull(stream, "stream"); //$NON-NLS-1$
		final InputSource source = new InputSource();
		source.setByteStream(stream);
		final Document doc = readDocument(source);
		return convertToPropertyNode(doc);
	}

	@Override
	public PropertyNode read(final InputSource source) throws CoreException {
		Assert.isNotNull(source, "source"); //$NON-NLS-1$
		final Document doc = readDocument(source);
		return convertToPropertyNode(doc);
	}

	@Override
	public PropertyNode read(final String xmlString) throws CoreException {
		Assert.isNotNull(xmlString, "xmlString"); //$NON-NLS-1$
		try {
			final StringReader sr = new StringReader(xmlString);
			final InputSource is = new InputSource(sr);
			final Document doc = readDocument(is);
			return convertToPropertyNode(doc);
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					NodePlugin.PLUGIN_ID,
					PropertyMessages.PropertyNode_UnableToReadFromString, e));
		}
	}
	
	@Override
	public PropertyNode read(final File file) throws CoreException {
		Assert.isNotNull(file, "file"); //$NON-NLS-1$
		try {
			final DocumentBuilder db = getDocumentBuilder();
			final Document doc = db.parse(file);
			return convertToPropertyNode(doc);
		} catch (Exception e) {
			final String message = MessageFormat.format(
					PropertyMessages.PropertyNode_UnableToReadFromFile,
					file.getPath());
			throw new CoreException(new Status(IStatus.ERROR,
					NodePlugin.PLUGIN_ID, message, e));
		}
	}

	private static final DocumentBuilder getDocumentBuilder()
			throws CoreException {
		try {
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			return dbf.newDocumentBuilder();
		} catch (Exception e) {
			throw new CoreException(
					new Status(
							IStatus.ERROR,
							NodePlugin.PLUGIN_ID,
							PropertyMessages.PropertyNode_UnableToCreateDocumentBuilder,
							e));
		}
	}
	
	private static final Document readDocument(final InputSource source)
			throws CoreException {
		final DocumentBuilder db = getDocumentBuilder();
		try {
			final Document doc = db.parse(source);
			return doc;
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					NodePlugin.PLUGIN_ID,
					PropertyMessages.PropertyNode_UnableToReadFromInputSource,
					e));
		}
	}
	
	private static final PropertyNode convertToPropertyNode(final Document doc) {
		doc.getDocumentElement().normalize();
		final PropertyNode rootParameter = PropertyNodeDocumentConversion.convertToPropertyNode(doc);
		return rootParameter;
	}
	
	@Override
	public final String toString(final PropertyNode pnode) throws CoreException {
		Assert.isNotNull(pnode, "pnode"); //$NON-NLS-1$
		try {
			final Source source = convertToSource(pnode);
			return converter.toString(source);
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					NodePlugin.PLUGIN_ID,
					PropertyMessages.PropertyNode_UnableToConvertToString, e));
		}
	}

	private static final Source convertToSource(final PropertyNode pnode) {
		final Document doc = PropertyNodeDocumentConversion.convertToDocument(pnode);
		final Source source = new DOMSource(doc);
		return source;
	}
	
	@Override
	public void write(final PropertyNode pnode, final File file)
			throws CoreException {
		Assert.isNotNull(pnode, "pnode"); //$NON-NLS-1$
		Assert.isNotNull(file, "file"); //$NON-NLS-1$
		
		try {
			final Source source = convertToSource(pnode);
			final Result result = new StreamResult(file);
			final Transformer transformer = converter.getTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			final String message = MessageFormat.format(
					PropertyMessages.PropertyNode_UnableToWriteToFile,
					file.getPath());
			throw new CoreException(new Status(IStatus.ERROR,
					NodePlugin.PLUGIN_ID, message, e));
		}
	}

	@Override
	public void write(final PropertyNode pnode, final Writer writer)
			throws CoreException {
		Assert.isNotNull(pnode, "pnode"); //$NON-NLS-1$
		Assert.isNotNull(writer, "writer"); //$NON-NLS-1$
		
		try {
			final Source source = convertToSource(pnode);
			final Result result = new StreamResult(writer);
			final Transformer transformer = converter.getTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					NodePlugin.PLUGIN_ID,
					PropertyMessages.PropertyNode_UnableToWriteToWriter, e));
		}
	}

	/**
	 * Validates a file containing XML content complies with a XSD read from
	 * another file.
	 * 
	 * @param xmlFile
	 *            a file containing the XML content to validate
	 * @param xsdFile
	 *            a file containing an XSD to validate with
	 * @return <code>true</code> if the source complies with the schema
	 * @throws SAXException
	 * @throws IOException
	 */
	public static final boolean validateXmlFile(final File xmlFile, final File xsdFile)
			throws SAXException, IOException {
		Assert.isNotNull(xmlFile, "xmlFile"); //$NON-NLS-1$
		Assert.isNotNull(xsdFile, "xsdFile"); //$NON-NLS-1$
		final SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema"); //$NON-NLS-1$
		final Schema xsdSchema = sf.newSchema(xsdFile);
		final Source xmlSource = new StreamSource(xmlFile);
		return validate(xmlSource, xsdSchema);
	}

	/**
	 * Validates that an input stream containing XML content complies with a XSD
	 * read from another input stream.
	 * 
	 * @param xml
	 *            an input stream containing the XML content to validate
	 * @param xsd
	 *            an input stream containing an XSD to validate with
	 * @return <code>true</code> if the source complies with the schema
	 * @throws SAXException
	 * @throws IOException
	 */
	public static final boolean validateXmlStream(final InputStream xml,
			final InputStream xsd) throws SAXException, IOException {
		Assert.isNotNull(xml, "xml"); //$NON-NLS-1$
		Assert.isNotNull(xsd, "xsd"); //$NON-NLS-1$
		final SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema"); //$NON-NLS-1$
		final Schema xsdSchema = sf.newSchema(new StreamSource(xsd));
		final Source xmlSource = new StreamSource(xml);
		return validate(xmlSource, xsdSchema);
	}

	/**
	 * Validates that a XML source complies with a XSD schema.
	 * 
	 * @param source
	 *            a XML source with content to validate
	 * @param schema
	 *            a XSD schema to validate with
	 * @return <code>true</code> if the source complies with the schema
	 * @throws IOException
	 */
	private static final boolean validate(final Source source,
			final Schema schema) throws IOException {
		final Validator validator = schema.newValidator();

		try {
			validator.validate(source);
			return true;
		} catch (SAXException ex) {
			return false;
		}
	}
	
}
