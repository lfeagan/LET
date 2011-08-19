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
package net.vectorcomputing.build;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class BuildPlugin extends Plugin {

	public static final String PLUGIN_ID = "net.vectorcomputing.build";
	
	// the shared instances
	private static BundleContext bundleContext = null;
	private static BuildPlugin plugin;
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		BuildPlugin.bundleContext = context;
		BuildPlugin.plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		BuildPlugin.bundleContext = null;
		BuildPlugin.plugin = null;
		super.stop(context);
	}

	public static BundleContext getBundleContext() {
		return bundleContext;
	}
	
	public static BuildPlugin getPlugin() {
		return plugin;
	}
	
}
