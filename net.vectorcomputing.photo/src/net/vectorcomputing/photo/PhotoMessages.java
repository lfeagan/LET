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
