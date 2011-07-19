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

import java.util.List;

import org.eclipse.core.runtime.CoreException;

/**
 * Interface for retrieving registered XML serializers.
 */
public interface IXmlSerializerRegistry {

	/**
	 * Retrieves the {@link IXmlSerializerDescriptor} with the specified id.
	 * 
	 * @param id
	 *            the id of the XML serializer to find
	 * @return the first XML serializer with the specified id or
	 *         <code>null</code> if one cannot be found
	 */
	public IXmlSerializerDescriptor findXmlSerializer(String id);
	
	/**
	 * Returns a list containing all {@link IXmlSerializerDescriptor}s in the
	 * extension registry.
	 * 
	 * @return a list containing all {@link IXmlSerializerDescriptor}s in the
	 *         extension registry.
	 * @throws CoreException
	 */
	public List<IXmlSerializerDescriptor> getXmlSerializers();

	/**
	 * Returns the descriptor of the XML serializer that accepts the specified
	 * class name.
	 * 
	 * @param className
	 *            the class name the XML serializer must accept
	 * @return the {@link IXmlSerializerDescriptor} that accepts the specified
	 *         class name or <code>null</code> if one cannot be found
	 */
	public IXmlSerializerDescriptor findXmlSerializerForClass(String className);

	/**
	 * Returns the descriptor of the XML serializer that accepts the specified
	 * class.
	 * 
	 * @param clazz
	 *            the class the XML serializer must accept
	 * @return the {@link IXmlSerializerDescriptor} that accepts the specified
	 *         class or <code>null</code> if one cannot be found
	 */	
	public IXmlSerializerDescriptor findXmlSerializerForClass(Class<?> clazz);

	/**
	 * Returns the descriptor of the XML serializer that uses the specified tag
	 * when converting an object to XML.
	 * 
	 * @param tag
	 *            the XML tag the serializer uses
	 * @return the first {@link IXmlSerializerDescriptor} that uses the
	 *         specified tag or <code>null</code> if one cannot be found
	 */
	public IXmlSerializerDescriptor findXmlSerializerWithTag(String tag);
	
}
