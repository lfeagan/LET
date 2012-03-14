package net.vectorcomputing.print;

import java.io.IOException;
import java.net.URL;

import net.vectorcomputing.print.internal.ORM;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Plugin;
import org.hibernate.SessionFactory;
import org.osgi.framework.BundleContext;

public class PrintPlugin extends Plugin {

	public static final String PLUGIN_ID = "net.vectorcomputing.photo"; //$NON-NLS-1$

	private static BundleContext context;
	private static PrintPlugin plugin;

	public static PrintPlugin getPlugin() {
		return plugin;
	}
	
	static BundleContext getContext() {
		return context;
	}
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		PrintPlugin.context = bundleContext;
		PrintPlugin.plugin = this;
		ORM.start(getHibernateCfg());
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		PrintPlugin.context = null;
		PrintPlugin.plugin = null;
		ORM.stop();
		super.stop(bundleContext);
	}
	
	public static URL getBundleURL() throws IOException {
		// Retrieve the URL to the root of the plug-in
		return FileLocator.toFileURL(plugin.getBundle().getEntry("/")); //$NON-NLS-1$
	}
	
	private static final String getHibernateCfg() throws IOException {
		return getBundleURL().getPath() + "hibernate.cfg.xml";
	}
	
	public static SessionFactory getSessionFactory() {
		return ORM.getSessionFactory();
	}
	
}
