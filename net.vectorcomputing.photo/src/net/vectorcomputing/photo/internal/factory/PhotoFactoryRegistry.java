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
package net.vectorcomputing.photo.internal.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.vectorcomputing.photo.PhotoMessages;
import net.vectorcomputing.photo.PhotoPlugin;
import net.vectorcomputing.photo.factory.IPhotoFactory;
import net.vectorcomputing.photo.factory.IPhotoFactoryDescriptor;
import net.vectorcomputing.photo.factory.IPhotoFactoryRegistry;

import org.eclipse.core.filesystem.IFileStore;
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

public class PhotoFactoryRegistry implements IPhotoFactoryRegistry, IExtensionChangeHandler {

	private static final String ELEMENT_NAME = "factory"; //$NON-NLS-1$
	
//	private static PhotoFactoryRegistry singleton;

	private List<IPhotoFactoryDescriptor> descriptors;
	
	private ExtensionTracker tracker;	

//	/**
//	 * Return the singleton instance of this class.
//	 * 
//	 * @return the singleton instance of this class
//	 */
//	public static final synchronized PhotoFactoryRegistry getInstance() {
//		if (singleton == null) {
//			singleton = new PhotoFactoryRegistry();
//		}
//		return singleton;
//	}
		
	public PhotoFactoryRegistry() {
		tracker = new ExtensionTracker();
		tracker.registerHandler(this, ExtensionTracker.createExtensionPointFilter(getExtensionPointFilter()));
		initializeFactoryDescriptors();
	}
	
	private IExtensionPoint getExtensionPointFilter() {
		return Platform.getExtensionRegistry().getExtensionPoint(PhotoPlugin.PLUGIN_ID, IPhotoFactory.POINT_ID);
	}
	
	private void initializeFactoryDescriptors() {
		if (descriptors == null) {
			List<IConfigurationElement> elements = getFactoryElements();
			descriptors = convertFactoryElementsToDescriptors(elements);
		}
	}
	
	private List<IConfigurationElement> getFactoryElements() {
		List<IConfigurationElement> allElements = getElements();
		List<IConfigurationElement> factoryElements = filterFactoryElements(allElements);
		return factoryElements;
	}
	
	/**
	 * Returns a list of all configuration elements extending the "net.vectorcomputing.photo.photoFactory" extension point
	 * @return
	 */
	private final List<IConfigurationElement> getElements() {
		IExtensionRegistry reg = RegistryFactory.getRegistry();
		if (reg == null) {
			throw new RuntimeException(PhotoMessages.PhotoFactoryRegistry_UnableToLocateEclipseExtensionRegistry);
		}
		
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(IPhotoFactory.POINT_ID);
		return Arrays.asList(elements);
	}

	public static final List<IConfigurationElement> filterFactoryElements(List<IConfigurationElement> elements) {
		ArrayList<IConfigurationElement> factoryElements = new ArrayList<IConfigurationElement>();
		for (IConfigurationElement element : elements) {
			if (isFactoryElement(element)) {
				factoryElements.add(element);
			}
		}
		return factoryElements;
	}
	
	/**
	 * Determines if the specified configuration element specifies a factory.
	 * @param configurationElement
	 * @return <code>true</code> if the configuration element represents a factory
	 */
	public static final boolean isFactoryElement(IConfigurationElement configurationElement) {
		String name = configurationElement.getName();
		return (name.equals(ELEMENT_NAME));
	}

	public static List<IPhotoFactoryDescriptor> convertFactoryElementsToDescriptors(List<IConfigurationElement> factoryElements) {
		List<IPhotoFactoryDescriptor> descriptors = new ArrayList<IPhotoFactoryDescriptor>();
		
		for (IConfigurationElement element : factoryElements) {
			try {
				descriptors.add(new PhotoFactoryDescriptor(element));
			} catch (Exception e) {
				getLog().log(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, PhotoMessages.PhotoFactoryRegistry_UnableToConstructPhotoFactoryDescriptor, e));
			}
		}
		
		return descriptors;
	}

	private static ILog getLog() {
		return PhotoPlugin.getPlugin().getLog();
	}

	@Override
	public IPhotoFactoryDescriptor findFactory(String id) {
		for (IPhotoFactoryDescriptor descriptor : descriptors) {
			if (descriptor.getId().equals(id)) {
				return descriptor;
			}
		}
		return null;
	}

	@Override
	public List<IPhotoFactoryDescriptor> getFactories() {
		return Collections.unmodifiableList(descriptors);
	}

	@Override
	public IPhotoFactoryDescriptor findFactoryFor(IFileStore photo) {
		for (IPhotoFactoryDescriptor descriptor : descriptors) {
			IPhotoFactory factory = descriptor.getFactory();
			if (factory.fileNameConstraint().satisfiedBy(photo.getName())
					&& factory.accepts(photo)) {
				return descriptor;
			}
		}
		return null;
	}
	
	@Override
	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		List<IConfigurationElement> newElements = Arrays.asList(extension.getConfigurationElements());
		List<IConfigurationElement> newFactoryElements = filterFactoryElements(newElements);
		List<IPhotoFactoryDescriptor> newFactoryDescriptors = convertFactoryElementsToDescriptors(newFactoryElements);
		descriptors.addAll(newFactoryDescriptors);
	}

	@Override
	public void removeExtension(IExtension extension, Object[] objects) {
		List<IConfigurationElement> oldElements = Arrays.asList(extension.getConfigurationElements());
		List<IConfigurationElement> oldFactoryElements = filterFactoryElements(oldElements);
		List<IPhotoFactoryDescriptor> oldFactoryDescriptors = convertFactoryElementsToDescriptors(oldFactoryElements);
		descriptors.removeAll(oldFactoryDescriptors);

		for (Object oldObject : objects) {
			tracker.unregisterObject(extension, oldObject);
		}
	}

}
