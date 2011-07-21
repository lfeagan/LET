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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.IXmlSerializerRegistry;
import net.vectorcomputing.serialization.xml.XmlSerializationMessages;
import net.vectorcomputing.serialization.xml.XmlSerializationPlugin;
import net.vectorcomputing.serialization.xml.XmlSerializer;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;

public final class XmlSerializerRegistry implements IXmlSerializerRegistry, IExtensionChangeHandler {
	
	private static final String ELEMENT_NAME = "serializer"; //$NON-NLS-1$

	private List<IXmlSerializerDescriptor> descriptors;
	
	private ExtensionTracker tracker;	
//	private IExtension extension;
		
	public XmlSerializerRegistry() {
		tracker = new ExtensionTracker();
		tracker.registerHandler(this, ExtensionTracker.createExtensionPointFilter(getExtensionPointFilter()));
		initializeXmlSerializerDescriptors();
	}
	
	private IExtensionPoint getExtensionPointFilter() {
		return Platform.getExtensionRegistry().getExtensionPoint(XmlSerializationPlugin.PLUGIN_ID, XmlSerializer.POINT_ID);
	}
	
	private void initializeXmlSerializerDescriptors() {
		if (descriptors == null) {
			List<IConfigurationElement> elements = getSerializerElements();
			descriptors = convertSerializerElementsToDescriptors(elements);
		}
	}
	
	@Override
	public IXmlSerializerDescriptor findXmlSerializer(String id) {
		Assert.isNotNull(id , "id"); //$NON-NLS-1$
		for (IXmlSerializerDescriptor descriptor : descriptors) {
			if (descriptor.getId().equals(id)) {
				return descriptor;
			}
		}
		return null;
	}
	
	@Override
	public List<IXmlSerializerDescriptor> getXmlSerializers() {
		return Collections.unmodifiableList(descriptors);
	}
	
	@Override
	public IXmlSerializerDescriptor findXmlSerializerForClass(String className) {
		Assert.isNotNull(className , "className"); //$NON-NLS-1$
		for (IXmlSerializerDescriptor descriptor : descriptors) {
			if (descriptor.getHandledClass().getName().equals(className)) {
				return descriptor;
			}
		}
		return null;
	}

	@Override
	public IXmlSerializerDescriptor findXmlSerializerForClass(Class<?> clazz) {
		Assert.isNotNull(clazz , "clazz"); //$NON-NLS-1$
		for (IXmlSerializerDescriptor descriptor : descriptors) {
			if (descriptor.getHandledClass().equals(clazz)) {
				return descriptor;
			}
		}
		return null;
	}

	@Override
	public IXmlSerializerDescriptor findXmlSerializerWithTag(String tag) {
		Assert.isNotNull(tag , "tag"); //$NON-NLS-1$
		for (IXmlSerializerDescriptor descriptor : descriptors) {
			if (descriptor.getTag().equals(tag)) {
				return descriptor;
			}
		}
		return null;
	}
	
	private List<IConfigurationElement> getSerializerElements() {
		List<IConfigurationElement> allElements = getElements();
		List<IConfigurationElement> serializerElements = filterSerializerElements(allElements);
		return serializerElements;
	}
	
	/**
	 * Returns a list of all configuration elements extending the
	 * "net.vectorcomputing.serialization.xml.xmlSerializers" extension point
	 * 
	 * @return a list containing all xmlSerializers configuration elements
	 */
	private final List<IConfigurationElement> getElements() {
		IExtensionRegistry reg = RegistryFactory.getRegistry();
		if (reg == null) {
			throw new RuntimeException(XmlSerializationMessages.XmlSerializerRegistry_UnableToGetDefaultExtensionRegistry);
		}
		
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(XmlSerializer.POINT_ID);
		return Arrays.asList(elements);
	}
	
	public static final List<IConfigurationElement> filterSerializerElements(List<IConfigurationElement> elements) {
		Assert.isNotNull(elements , "elements"); //$NON-NLS-1$
		ArrayList<IConfigurationElement> serializerElements = new ArrayList<IConfigurationElement>();
		for (IConfigurationElement element : elements) {
			if (isSerializerElement(element)) {
				serializerElements.add(element);
			}
		}
		return serializerElements;
	}
	
	/**
	 * Determines if the specified configuration element specifies a serializer.
	 * 
	 * @param configurationElement
	 * @return <code>true</code> if the configuration element represents a
	 *         serializer
	 */
	public static final boolean isSerializerElement(IConfigurationElement configurationElement) {
		Assert.isNotNull(configurationElement , "configurationElement"); //$NON-NLS-1$
		String name = configurationElement.getName();
		return (name.equals(ELEMENT_NAME));
	}
	
	public List<IXmlSerializerDescriptor> convertSerializerElementsToDescriptors(List<IConfigurationElement> serializerElements) {
		Assert.isNotNull(serializerElements , "serializerElements"); //$NON-NLS-1$
		List<IXmlSerializerDescriptor> descriptors = new ArrayList<IXmlSerializerDescriptor>();
		
		for (IConfigurationElement element : serializerElements) {
			try {
				descriptors.add(new XmlSerializerDescriptor(element, this));
			} catch (Exception e) {
				getLog().log(new Status(IStatus.ERROR, XmlSerializationPlugin.PLUGIN_ID, XmlSerializationMessages.XmlSerializerRegistry_UnableToConstructDescriptor, e));
			}
		}
		
		return descriptors;
	}
	
	private static ILog getLog() {
		return XmlSerializationPlugin.getPlugin().getLog();
	}

	@Override
	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		List<IConfigurationElement> newElements = Arrays.asList(extension.getConfigurationElements());
		List<IConfigurationElement> newSerializerElements = filterSerializerElements(newElements);
		List<IXmlSerializerDescriptor> newSerializerDescriptors = convertSerializerElementsToDescriptors(newSerializerElements);
		descriptors.addAll(newSerializerDescriptors);
	}

	@Override
	public void removeExtension(IExtension extension, Object[] objects) {
		List<IConfigurationElement> oldElements = Arrays.asList(extension.getConfigurationElements());
		List<IConfigurationElement> oldSerializerElements = filterSerializerElements(oldElements);
		List<IXmlSerializerDescriptor> oldSerializerDescriptors = convertSerializerElementsToDescriptors(oldSerializerElements);
		descriptors.removeAll(oldSerializerDescriptors);

		for (Object oldObject : objects) {
			tracker.unregisterObject(extension, oldObject);
		}
	}
	
}
