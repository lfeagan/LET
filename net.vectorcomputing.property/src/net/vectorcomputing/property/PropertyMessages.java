package net.vectorcomputing.property;

import org.eclipse.osgi.util.NLS;

/**
 * Externalized strings.
 */
public class PropertyMessages extends NLS {
	private static final String BUNDLE_NAME = "net.vectorcomputing.property.messages"; //$NON-NLS-1$
	public static String DocumentXmlConverter_IndentAmountMustBeGreaterThanZero;
	public static String PropertyNode_KeyMustBeSpecified;
	public static String PropertyNode_UnableToConvertToDocument;
	public static String PropertyNode_UnableToConvertToString;
	public static String PropertyNode_UnableToCreateDocumentBuilder;
	public static String PropertyNode_UnableToReadFromFile;
	public static String PropertyNode_UnableToReadFromInputSource;
	public static String PropertyNode_UnableToReadFromString;
	public static String PropertyNode_UnableToWriteToFile;
	public static String PropertyNode_UnableToWriteToWriter;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, PropertyMessages.class);
	}

	private PropertyMessages() {
	}
}