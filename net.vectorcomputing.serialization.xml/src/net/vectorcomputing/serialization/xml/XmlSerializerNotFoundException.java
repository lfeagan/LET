package net.vectorcomputing.serialization.xml;

import org.eclipse.core.runtime.IStatus;

public class XmlSerializerNotFoundException extends XmlSerializationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XmlSerializerNotFoundException(IStatus status) {
		super(status);
	}
	
}
