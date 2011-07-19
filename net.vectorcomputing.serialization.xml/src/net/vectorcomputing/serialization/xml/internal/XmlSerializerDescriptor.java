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
package net.vectorcomputing.serialization.xml.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;

import net.vectorcomputing.base.string.StringIO;
import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.property.node.PropertyNodeXmlConverter;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerializationMessages;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;
import net.vectorcomputing.serialization.xml.XmlSerializer;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class XmlSerializerDescriptor implements IXmlSerializerDescriptor {

	private static final String ATTR_ID = "id"; //$NON-NLS-1$
	private static final String ATTR_NAME = "name"; //$NON-NLS-1$
	private static final String ATTR_TAG = "tag"; //$NON-NLS-1$
	private static final String ATTR_CLASS = "class"; //$NON-NLS-1$
	private static final String ATTR_HANDLES = "handles"; //$NON-NLS-1$
	
	private final IConfigurationElement element;
	private final IXmlSerializerRegistry registry;
	
	private final String id;
	private final String name;
	private final String tag;
	private final XmlSerializer xmlSerializer;
	private final Class<?> handledClass;
	
	public XmlSerializerDescriptor(final IConfigurationElement element, final IXmlSerializerRegistry registry) throws CoreException {
		Assert.isNotNull(element, "element"); //$NON-NLS-1$
		Assert.isNotNull(registry, "registry"); //$NON-NLS-1$
		
		this.element = element;
		this.registry = registry;
		id = extractId(element);
		name = extractName(element);
		tag = element.getAttribute(ATTR_TAG);
		xmlSerializer = extractXmlSerializer(element);
		handledClass = extractHandledClass(element);
	}
	
	private static final String extractId(final IConfigurationElement element) {
		assert element != null;
		return element.getAttribute(ATTR_ID);
	}

	private static final String extractName(final IConfigurationElement element) {
		assert element != null;
		return element.getAttribute(ATTR_NAME);
	}
	
	private static final XmlSerializer extractXmlSerializer(final IConfigurationElement element) throws CoreException {
		assert element != null;
		try {
			Object obj = element.createExecutableExtension(ATTR_CLASS);
			XmlSerializer xmlSerializer = (XmlSerializer) obj;
			return xmlSerializer;
		} catch (Exception e) {
			final String message = MessageFormat.format(XmlSerializationMessages.XmlSerializerDescriptor_UnableToExtractXmlSerializer, element.getAttribute(ATTR_CLASS), extractId(element));
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, message, e));
		}	
	}
	
	private static final Class<?> extractHandledClass(final IConfigurationElement element) throws CoreException {
		assert element != null;
		String className = element.getAttribute(ATTR_HANDLES);
		try {
			Class<?> handledClass = Class.forName(className);
			return handledClass;
		} catch (ClassNotFoundException e) {
			final String message = MessageFormat.format(XmlSerializationMessages.XmlSerializerDescriptor_UnableToExtractTheHandlesClass, className, extractId(element));
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, message, e));
		}
	}

	public IConfigurationElement getConfigurationElement() {
		return element;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public XmlSerializer getXmlSerializer() {
		return this.xmlSerializer;
	}

	@Override
	public Class<?> getHandledClass() {
		return handledClass;
	}
	
	@Override
	public IXmlSerializerRegistry getRegistry() {
		return registry;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XmlSerializerDescriptor ["); //$NON-NLS-1$
		if (id != null) {
			builder.append("id="); //$NON-NLS-1$
			builder.append(id);
			builder.append(", "); //$NON-NLS-1$
		}
		if (name != null) {
			builder.append("name="); //$NON-NLS-1$
			builder.append(name);
			builder.append(", "); //$NON-NLS-1$
		}
		if (tag != null) {
			builder.append("tag="); //$NON-NLS-1$
			builder.append(tag);
			builder.append(", "); //$NON-NLS-1$
		}
		if (xmlSerializer != null) {
			builder.append("xmlSerializer="); //$NON-NLS-1$
			builder.append(xmlSerializer);
			builder.append(", "); //$NON-NLS-1$
		}
		if (handledClass != null) {
			builder.append("handledClass="); //$NON-NLS-1$
			builder.append(handledClass);
		}
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}

	@Override
	public Object read(final PropertyNode pnode) throws CoreException {
		Assert.isNotNull(pnode, "pnode"); //$NON-NLS-1$
		return getXmlSerializer().read(pnode, this);
	}

	@Override
	public Object read(final String string) throws CoreException {
		Assert.isNotNull(string , "string"); //$NON-NLS-1$
		
		final PropertyNode pnode;
		try {
			pnode = PropertyNodeXmlConverter.getSharedInstance().read(string);
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.UnableToConvertStringToPropertyNode, e));
		}
		return read(pnode);
	}

	@Override
	public Object read(final InputStream inputStream) throws CoreException {		
		Assert.isNotNull(inputStream, "inputStream"); //$NON-NLS-1$
		
		final PropertyNode pnode;
		try {
			pnode = PropertyNodeXmlConverter.getSharedInstance().read(inputStream);
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.UnableToConvertInputStreamToPropertyNode, e));
		}		
		return read(pnode);
	}

	@Override
	public PropertyNode toPropertyNode(final Object object) throws CoreException {
		Assert.isNotNull(object, "object"); //$NON-NLS-1$
		return getXmlSerializer().toPropertyNode(object, this);
	}
	
	@Override
	public String toString(final Object object) throws CoreException {
		Assert.isNotNull(object , "object"); //$NON-NLS-1$

		final PropertyNode pnode = toPropertyNode(object);
		try {
			return PropertyNodeXmlConverter.getSharedInstance().toString(pnode);
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.UnableToConvertObjectToString, e));
		}
	}
	
	@Override
	public void write(final Object object, final OutputStream outputStream) throws CoreException {
		try {
			StringIO.write(toString(object), outputStream, false);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.UnableToWriteObjectToOutputStream, e));
		}
	}
	
}
