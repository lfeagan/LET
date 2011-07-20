/*******************************************************************************
 * Copyright 2011 lfeagan
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
package net.vectorcomputing.net.serialization.xml.java.lang;

import net.vectorcomputing.property.node.PropertyNode;
import net.vectorcomputing.serialization.xml.IXmlSerializerDescriptor;
import net.vectorcomputing.serialization.xml.XmlSerializer;

import org.eclipse.core.runtime.CoreException;

/**
 * Converts a {@link java.lang.Float} to and from a {@link PropertyNode} to
 * perform XML serialization and de-serialization.
 */
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
