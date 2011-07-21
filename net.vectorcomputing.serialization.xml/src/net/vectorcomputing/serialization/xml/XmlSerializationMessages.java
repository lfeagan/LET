// $codepro.audit.disable largeNumberOfFields
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

import org.eclipse.osgi.util.NLS;

public class XmlSerializationMessages extends NLS {
	private static final String BUNDLE_NAME = "net.vectorcomputing.serialization.xml.messages"; //$NON-NLS-1$
	public static String XmlSerializationPlugin_RegistryIsClosed;
	public static String XmlSerializer_UnableToFindXmlSerializerForClass;
	public static String UnableToConvertInputStreamToPropertyNode;
	public static String UnableToConvertObjectToString;
	public static String UnableToConvertStringToPropertyNode;
	public static String UnableToWriteObjectToOutputStream;
	public static String XmlSerializerDescriptor_UnableToExtractTheHandlesClass;
	public static String XmlSerializerDescriptor_UnableToExtractXmlSerializer;
	public static String XmlSerializerRegistry_UnableToConstructDescriptor;
	public static String XmlSerializerRegistry_UnableToGetDefaultExtensionRegistry;
	public static String XmlSerializer_UnableToFindSerializerForTag;
	public static String XmlSerializer_UnableToReadObjectFromFile;
	public static String XmlSerializer_UnableToReadObjectFromInputStream;
	public static String XmlSerializer_UnableToWriteObjectToFile;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, XmlSerializationMessages.class);
	}

	private XmlSerializationMessages() {
	}
}
