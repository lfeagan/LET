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
package net.vectorcomputing.serialization.xml.java.lang;

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
