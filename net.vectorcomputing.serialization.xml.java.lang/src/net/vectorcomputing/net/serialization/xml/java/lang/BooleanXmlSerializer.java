package net.vectorcomputing.net.serialization.xml.java.lang;

import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.XmlSerializer;

/**
 * Converts a {@link java.lang.Boolean} to and from a {@link PropertyNode} to
 * perform XML serialization and de-serialization.
 */
public class BooleanXmlSerializer implements XmlSerializer {

	@Override
	public Object read(final PropertyNode pnode, final IXmlSerializerDescriptor descriptor) {
		return Boolean.parseBoolean(pnode.getValue());
	}
	
	@Override
	public PropertyNode toPropertyNode(final Object obj, final IXmlSerializerDescriptor descriptor) {
		final Boolean value = (Boolean) obj;
		final PropertyNode pnode = new PropertyNode(descriptor.getTag(), value.toString());
		return pnode;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (BooleanXmlSerializer.class.equals(obj.getClass()));
	}

}
