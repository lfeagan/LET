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
package net.vectorcomputing.property;

import static net.vectorcomputing.property.PropertyDocumentConversion.convertToProperty;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.vectorcomputing.node.tree.ModifiableTreeNodeWithData;

import org.eclipse.core.runtime.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Evaluation class trying out ideas with generic conversion. This class is not
 * yet ready for use.
 */
public class ModifiableNodeWithDataXmlConversion {

	public static ModifiableTreeNodeWithData<Property> createNodesFromXmlFile(
			final File file) throws ParserConfigurationException, SAXException,
			IOException {
		Assert.isNotNull(file, "file"); //$NON-NLS-1$
		
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		final ModifiableTreeNodeWithData<Property> rootNode = new ModifiableTreeNodeWithData<Property>();
		final DocumentBuilder db = dbf.newDocumentBuilder();
		final Document doc = db.parse(file);
		doc.getDocumentElement().normalize();

		// Prime the recursive conversion processing by converting the root
		// element
		final Node documentRootNode = doc.getDocumentElement();
		final ModifiableTreeNodeWithData<Property> treeRootNode = new ModifiableTreeNodeWithData<Property>(
				convertToProperty(documentRootNode));

		// Continue by converting the Document's Nodes to Node<Property>s
		// recursively
		attachDocumentNodesAsChildrenRecursively(documentRootNode, treeRootNode);

		return rootNode;
	}

	/**
	 * Converts all children of the parent Document Node into
	 * ModifiableNodeWithData<Property> and then recurses into the grand
	 * children of the parent Document Node
	 * 
	 * @param parentDocumentNode
	 * @param parentModifiableNodeWithData
	 */
	private static void attachDocumentNodesAsChildrenRecursively(
			final Node parentDocumentNode,
			final ModifiableTreeNodeWithData<Property> parentModifiableNodeWithData) {
		final NodeList childDocumentNodes = parentDocumentNode.getChildNodes();

		// Iterate over all child document nodes
		Node childDocumentNode;
		for (int i = 0; i < childDocumentNodes.getLength(); ++i) {
			childDocumentNode = childDocumentNodes.item(i);
			// Convert the childDocumentNode into a Property
			final Property childProperty = convertToProperty(childDocumentNode);
			if (childProperty != null) {
				ModifiableTreeNodeWithData<Property> childModifiableNodeWithData = parentModifiableNodeWithData.addChildWithData(childProperty);
				
				// Retrieve the list of children
				final NodeList grandChildrenDocumentNodes = childDocumentNode.getChildNodes();
				if (grandChildrenDocumentNodes.getLength() > 0) {
					attachDocumentNodesAsChildrenRecursively(childDocumentNode, childModifiableNodeWithData);
				}
			}
		}
	}

	/**
	 * Given a current (un-converted) Document Node and a parent
	 * ModifiableNodeWithData, converts the Document Node into a Property and
	 * attaches it as a child to the parent ModifiableNodeWithData and, if the
	 * current Document Node has children, recursively calls this method on them
	 * to convert them as well
	 * 
	 * @param currentDocumentNode
	 * @param parentModifiableNodeWithData
	 */
	public static void attachDocumentNodesToModifiableNodeWithData(
			final Node currentDocumentNode,
			final ModifiableTreeNodeWithData<Property> parentModifiableNodeWithData) {
		Assert.isNotNull(currentDocumentNode, "currentDocumentNode"); //$NON-NLS-1$
		Assert.isNotNull(parentModifiableNodeWithData, "parentModifiableNodeWithData"); //$NON-NLS-1$
		
		// Attempt to convert the XML Document Node into a Property
		Property currentProperty = convertToProperty(currentDocumentNode);
		// If the conversion was successful, attach it as a child of the parent
		if (currentProperty != null) {
			final ModifiableTreeNodeWithData<Property> currentModifiableNodeWithData = parentModifiableNodeWithData.addChildWithData(currentProperty);

			// Retrieve the children of the current Document Node
			final NodeList childrenDocumentNodes = currentDocumentNode.getChildNodes();

			// Iterate over all documentNodeChildren
			Node childDocumentNode;
			for (int i = 0; i < childrenDocumentNodes.getLength(); ++i)
			{
				childDocumentNode = childrenDocumentNodes.item(i);
				attachDocumentNodesToModifiableNodeWithData(childDocumentNode, currentModifiableNodeWithData);
			}			
		}
	}

	/**
	 * Converts the Document Node into a ModifiableNodeWithData<Property> and,
	 * if the Document Node has children, recurses to process them
	 * 
	 * @param documentNode
	 * @return the modifiable node with data created from the document node
	 */
	public static ModifiableTreeNodeWithData<Property> convertDocumentNodesToModifiableNodeWithDatas(
			final Node documentNode) {
		Assert.isNotNull(documentNode, "documentNode"); //$NON-NLS-1$
		final ModifiableTreeNodeWithData<Property> modifiableNodeWithData = new ModifiableTreeNodeWithData<Property>();
		modifiableNodeWithData.setData(convertToProperty(documentNode));
		return modifiableNodeWithData;
	}
}
