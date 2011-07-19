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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class PropertyPlugin implements BundleActivator {
	
	public static final String PLUGIN_ID = "net.vectorcomputing.property"; //$NON-NLS-1$

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		PropertyPlugin.context = bundleContext;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		PropertyPlugin.context = null;
	}

}
