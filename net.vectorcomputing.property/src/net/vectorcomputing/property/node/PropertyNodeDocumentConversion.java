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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.vectorcomputing.property.ImmutableProperty;
import net.vectorcomputing.property.Property;
import net.vectorcomputing.property.PropertyDocumentConversion;
import net.vectorcomputing.property.PropertyMessages;

import org.eclipse.core.runtime.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class PropertyNodeDocumentConversion {

	/**
	 * Converts a property node to its document representation.
	 * 
	 * @param pnode
	 *            the property node to convert
	 * @return the document representation of the property node
	 */
	public static Document convertToDocument(final PropertyNode pnode) {
		Assert.isNotNull(pnode, "pnode"); //$NON-NLS-1$
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		final Document doc;
		
		try {
			final DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			
			// Convert and attach the root node
			final Element rootElement = convertToElement(doc, pnode);
			doc.appendChild(rootElement);
			convertToDocumentRecursive(doc, rootElement, pnode);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(PropertyMessages.PropertyNode_UnableToConvertToDocument, e);
		}

		return doc;
	}

	private static void convertToDocumentRecursive(final Document doc, final Node parentNode, final PropertyNode pnode) {
		Element element;
		for (PropertyNode child : pnode.getChildren()) {
			element = convertToElement(doc, child);
			parentNode.appendChild(element);
			convertToDocumentRecursive(doc, element, child);
		}
	}

	/**
	 * Creates a new element with the property node's key as the tag name.
	 * 
	 * @param doc
	 *            the document to use when creating a new element
	 * @param pnode
	 *            the property node to convert
	 * @return a new element that represents the specified property node
	 */
	private static Element convertToElement(final Document doc, final PropertyNode pnode) {
		// Create a new element with the property node's key as the tag name
		final Element element = doc.createElement(pnode.getKey());
		
		// Attach the property node's value as a text node
		if (pnode.getValue() != null) {
			element.appendChild(doc.createTextNode(pnode.getValue()));	
		}
		
		// Attach the property node's attributes
		for (Property attribute : pnode.getAttributes()) {
			element.setAttribute(attribute.getKey(), attribute.getValue());
		}
		return element;
	}

	/**
	 * Converts the specified document into a property node.
	 * 
	 * @param document
	 *            the document to convert
	 * @return a property node that represents the document
	 */
	public static PropertyNode convertToPropertyNode(final Document document) {
		Assert.isNotNull(document, "document"); //$NON-NLS-1$
		document.getDocumentElement().normalize();
		final Node rootNode = document.getDocumentElement();
		
		// Convert the root element to prime the recursive conversion of children
		final PropertyNode rootPropertyNode = convertToPropertyNode(rootNode, null);
		
		// Recursively convert all children of the document root to property nodes and attach as children
		convertToPropertyNodeRecursive(rootNode, rootPropertyNode);

		return rootPropertyNode;
	}

	private static PropertyNode convertToPropertyNode(final Node node, final PropertyNode parent) {
		Property property = PropertyDocumentConversion.convertToProperty(node);
		if (property != null) {
			final PropertyNode propertyNode = new PropertyNode(property, parent);
			attachNodeAttributes(node, propertyNode);
			return propertyNode;
		} else {
			return null;
		}
	}

	/**
	 * Fetches the attributes from a document node and adds them to a property
	 * node.
	 * 
	 * @param documentNode
	 *            the source to read attributes from
	 * @param propertyNode
	 *            the target to add new attributes to
	 */
	private static void attachNodeAttributes(final Node documentNode, final PropertyNode propertyNode) {
		final NamedNodeMap attributes = documentNode.getAttributes();
		if (attributes != null) {
			Node attribute;
			for (int i = 0; i < attributes.getLength(); ++i) {
				attribute = attributes.item(i);
				propertyNode.addAttribute(new ImmutableProperty(attribute.getNodeName(), attribute.getNodeValue()));
			}
		}
	}
	
	private static void convertToPropertyNodeRecursive(final Node parentNode, final PropertyNode parentPNode) {
		final NodeList children = parentNode.getChildNodes();
		Node childNode;
		PropertyNode childPNode;
		for (int i = 0; i < children.getLength(); ++i) {
			childNode = children.item(i);
			childPNode = convertToPropertyNode(childNode, parentPNode);
			if (childPNode != null) {
				convertToPropertyNodeRecursive(childNode, childPNode);
			}
		}
	}
	
}
