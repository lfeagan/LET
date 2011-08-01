package net.vectorcomputing.serialization.xml;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public class XmlSerializationException extends CoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public XmlSerializationException(IStatus status) {
		super(status);
	}
	
}
