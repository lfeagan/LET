package net.vectorcomputing.net.serialization.xml.java.lang;

import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.XmlSerializer;

public class CharacterXmlSerializer implements XmlSerializer {

	@Override
	public Object read(final PropertyNode pnode, final IXmlSerializerDescriptor descriptor) {
		return new Character(pnode.getValue().charAt(0));
	}
	
	@Override
	public PropertyNode toPropertyNode(final Object obj, final IXmlSerializerDescriptor descriptor) {
		final Character character = (Character) obj;
		final PropertyNode pnode = new PropertyNode(descriptor.getTag(), character.toString());
		return pnode;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (CharacterXmlSerializer.class.equals(obj.getClass()));
	}
	
}
