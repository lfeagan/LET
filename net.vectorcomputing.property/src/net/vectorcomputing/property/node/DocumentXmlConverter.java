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

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.vectorcomputing.property.PropertyMessages;

import org.eclipse.core.runtime.Assert;
import org.w3c.dom.Document;

/**
 * Utility class for converting a {@link Document} to a string.
 * 
 * Supports:
 * <ul>
 * <li>enabling and disabling indentation of the output</li>
 * <li>the number of characters wide each indent level is</li>
 * </ul>
 */
public class DocumentXmlConverter {
	
	private boolean indentOutput = true;
	
	private static final int DEFAULT_INDENT_CHARS = 2;
	private int charsToIndent = DEFAULT_INDENT_CHARS;
	
	/**
	 * Enables or disables indented output.
	 * 
	 * @param indentOutput <code>true</code> if the output is to be indented
	 */
	public void setIndentOutput(final boolean indentOutput) {
		this.indentOutput = indentOutput;
	}
	
	/**
	 * Returns <code>true</code> if output will be indented.
	 * 
	 * @return <code>true</code> if output will be indented
	 */
	public boolean getIndentOutput() {
		return indentOutput;
	}

	/**
	 * Sets the number of characters to indent output.
	 * 
	 * As an example of how this works, if set to 2 characters and the current
	 * node is a depth 5 the line will start
	 * 
	 * <pre>
	 * 2 * (5 - 1) = 8
	 * </pre>
	 * 
	 * characters in from the left margin.
	 * 
	 * @param charsToIndent
	 *            the number of characters to indent each depth of the output
	 */
	public void setCharsToIndent(final int charsToIndent) {
		if (charsToIndent > 0) {
			this.charsToIndent = charsToIndent;
		} else {
			throw new IllegalArgumentException(PropertyMessages.DocumentXmlConverter_IndentAmountMustBeGreaterThanZero);
		}
	}
	
	/**
	 * Returns the number of characters output will be indented per depth in the
	 * tree.
	 * 
	 * @return the number of characters output will be indented per depth in the
	 *         tree
	 */
	public int getCharsToIndent() {
		return charsToIndent;
	}

	/**
	 * Converts the specified document to its string representation.
	 * 
	 * @param doc
	 *            the document to convert
	 * @return the string representation of the document
	 * @throws TransformerException
	 */
	public String toString(final Document doc) throws TransformerException {
		Assert.isNotNull(doc, "doc"); //$NON-NLS-1$
		final Source source = new DOMSource(doc);
		return toString(source);
	}

	/**
	 * Converts the specified source to its string representation.
	 * 
	 * @param source
	 *            the source to convert
	 * @return the string representation
	 * @throws TransformerException
	 */
	public String toString(final Source source) throws TransformerException {
		Assert.isNotNull(source, "source"); //$NON-NLS-1$
		final StringWriter stringWriter = new StringWriter();
		final Result result = new StreamResult(stringWriter);
		final Transformer transformer = getTransformer();
		transformer.transform(source, result);
		return stringWriter.toString();
	}

	/**
	 * Returns the transformer specified by this document XML converter.
	 * 
	 * @return the transformer specified by this document XML converter.
	 * @throws TransformerConfigurationException
	 */
	public Transformer getTransformer() throws TransformerConfigurationException {
		final TransformerFactory factory = TransformerFactory.newInstance();
		final Transformer transformer = factory.newTransformer();
		if (indentOutput) {
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(charsToIndent)); //$NON-NLS-1$
		}
		return transformer;
	}
	
}
