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

import net.vectorcomputing.base.string.Strings;
import net.vectorcomputing.base.string.transform.IStringTransformer;
import net.vectorcomputing.base.string.transform.StringTransformRemoveLeadingSpaces;
import net.vectorcomputing.base.string.transform.StringTransformRemoveTrailingSpaces;
import net.vectorcomputing.base.string.transform.StringTransformerPipeline;

import org.eclipse.core.runtime.Assert;
import org.w3c.dom.Node;

public class PropertyDocumentConversion {

	/**
	 * A string transformer that removes leading and trailing spaces.
	 */
	private static final IStringTransformer TRANSFORMER = new StringTransformerPipeline(
			new StringTransformRemoveLeadingSpaces(),
			new StringTransformRemoveTrailingSpaces());

	/**
	 * Converts an XML document node to a property.
	 * 
	 * @param documentNode
	 *            the XML document node to be converted
	 * @return the XML document node converted into a property or
	 *         <code>null</code> if unable to convert to a property
	 * @throws NullPointerException
	 *             if called against a non-pure node
	 */
	public static Property convertToProperty(final Node documentNode) {
		Assert.isNotNull(documentNode, "documentNode"); //$NON-NLS-1$
		
		final String name = documentNode.getNodeName();
		final Short type = documentNode.getNodeType();
		String value = null;

		if (type == Node.ELEMENT_NODE) {
			// Fetch and normalize the document node's value
			final Node valueNode = documentNode.getFirstChild();
			if (valueNode == null) {
				return new ImmutableProperty(name, Strings.emptyString());
			} else {
				return new ImmutableProperty(name, valueNode.getNodeValue());
			}
		}
		return null;
	}

	private static final String trimValue(final String untrimmedValue) {
//		final String untrimmedValue = curNode.getNodeValue();
//		if (untrimmedValue != null) {
//			value = TRANSFORMER.transform(untrimmedValue);
//			// if the value's length after trimming leading and trailing
//			// spaces is zero, treat as being effectively null
//			if (value.length() == 0) {
//				value = null;
//			}
//		}
		return Strings.emptyString();
	}
}
