package net.vectorcomputing.net.serialization.xml.java.lang;

import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.XmlSerializer;

import org.eclipse.core.runtime.CoreException;

public class FloatXmlSerializer implements XmlSerializer {

	@Override
	public Object read(PropertyNode pnode, IXmlSerializerDescriptor descriptor)
			throws CoreException {
		return Float.parseFloat(pnode.getValue());
	}

	@Override
	public PropertyNode toPropertyNode(Object obj,
			IXmlSerializerDescriptor descriptor) throws CoreException {
		final Float value = (Float) obj;
		final PropertyNode pnode = new PropertyNode(descriptor.getTag(), value.toString());
		return pnode;
	}

	@Override
	public boolean equals(Object obj) {
		return (FloatXmlSerializer.class.equals(obj.getClass()));
	}

}
