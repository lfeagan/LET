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

import net.vectorcomputing.property.node.PropertyNode;

import org.eclipse.core.runtime.CoreException;

/**
 * Interface for a class capable of converting an object to/from an XML
 * representation.
 */
public interface XmlSerializer {

	public static final String POINT_ID = XmlSerializationPlugin.PLUGIN_ID + ".xmlSerializers"; //$NON-NLS-1$

	public Object read(PropertyNode pnode, IXmlSerializerDescriptor descriptor) throws CoreException;

	/**
	 * 
	 * @param object
	 * @param descriptor
	 * @return
	 * @throws CoreException
	 */
	public PropertyNode toPropertyNode(Object object, IXmlSerializerDescriptor descriptor) throws CoreException;
	
	// Implementors must provide an implementation of the equals method
	@Override
	public boolean equals(Object object);
	
}
