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
package net.vectorcomputing.photo;

import org.eclipse.osgi.util.NLS;

public class PhotoMessages extends NLS {
	private static final String BUNDLE_NAME = "net.vectorcomputing.photo.messages"; //$NON-NLS-1$
	public static String ExceptionWhileClosingInputStream;
	public static String ExceptionWhileClosingOutputStream;
	public static String PhotoCatalog_UnableToCreateFromPath;
	public static String PhotoFactoryDescriptor_UnableToCreatePhotoFactory;
	public static String PhotoFactoryRegistry_UnableToConstructPhotoFactoryDescriptor;
	public static String PhotoFactoryRegistry_UnableToLocateEclipseExtensionRegistry;
	public static String PhotoFactoryRegistryIsClosed;
	public static String PhotoLibraryIsClosed;
	public static String UnableToReadImageData;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, PhotoMessages.class);
	}

	private PhotoMessages() {
	}
}
