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
package net.vectorcomputing.property;

import org.eclipse.osgi.util.NLS;

/**
 * Externalized strings.
 */
public class PropertyMessages extends NLS {
	private static final String BUNDLE_NAME = "net.vectorcomputing.property.messages"; //$NON-NLS-1$
	public static String DocumentXmlConverter_IndentAmountMustBeGreaterThanZero;
	public static String PropertyNode_KeyMustBeSpecified;
	public static String PropertyNode_UnableToConvertToDocument;
	public static String PropertyNode_UnableToConvertToString;
	public static String PropertyNode_UnableToCreateDocumentBuilder;
	public static String PropertyNode_UnableToReadFromFile;
	public static String PropertyNode_UnableToReadFromInputSource;
	public static String PropertyNode_UnableToReadFromString;
	public static String PropertyNode_UnableToWriteToFile;
	public static String PropertyNode_UnableToWriteToWriter;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, PropertyMessages.class);
	}

	private PropertyMessages() {
	}
}
