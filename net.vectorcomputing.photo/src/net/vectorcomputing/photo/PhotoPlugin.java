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
package net.vectorcomputing.photo;

import net.vectorcomputing.photo.catalog.IPhotoCatalogs;
import net.vectorcomputing.photo.factory.IPhotoFactoryRegistry;
import net.vectorcomputing.photo.internal.catalog.PhotoCatalogs;
import net.vectorcomputing.photo.internal.factory.PhotoFactoryRegistry;
import net.vectorcomputing.photo.internal.library.PhotoLibraryRoot;
import net.vectorcomputing.photo.library.IPhotoLibrary;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class PhotoPlugin extends Plugin {
	
	public static final String PLUGIN_ID = "net.vectorcomputing.photo"; //$NON-NLS-1$

	private static BundleContext context;
	private static PhotoPlugin plugin;

	private static IPhotoCatalogs catalogs = null;
	private static IPhotoLibrary library = null;
	private static IPhotoFactoryRegistry factoryRegistry = null;
	
	public static PhotoPlugin getPlugin() {
		return plugin;
	}
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		PhotoPlugin.context = bundleContext;
		PhotoPlugin.plugin = this;
		PhotoPlugin.catalogs = new PhotoCatalogs();
		PhotoPlugin.library = new PhotoLibraryRoot();
		PhotoPlugin.factoryRegistry = new PhotoFactoryRegistry();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		PhotoPlugin.context = null;
		PhotoPlugin.plugin = null;
		PhotoPlugin.catalogs = null;
		PhotoPlugin.library = null;
		PhotoPlugin.factoryRegistry = null;
		super.stop(bundleContext);
	}

	public static IPhotoCatalogs getCatalogs() {
		return catalogs;
	}
	
	/**
	 * Returns the photo library. The photo library is not accessible after the
	 * photo plug-in has shutdown.
	 * 
	 * @return the photo library that was created by the single instance of this
	 *         plug-in class.
	 */
	public static IPhotoLibrary getLibrary() {
		if (library == null) {
			throw new IllegalStateException("Photo library is closed");
		}
		return library;
	}
	
	public static IPhotoFactoryRegistry getFactoryRegistry() {
		if (factoryRegistry == null) {
			throw new IllegalStateException("Photo factory registry is closed");
		}
		return factoryRegistry;
	}
	
}
