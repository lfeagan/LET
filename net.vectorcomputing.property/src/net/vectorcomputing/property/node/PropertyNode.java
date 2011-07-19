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
package net.vectorcomputing.property.node;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.vectorcomputing.node.tree.AbstractModifiableTreeNode;
import net.vectorcomputing.node.tree.iterator.fast.FastLevelOrderTreeNodeIterator;
import net.vectorcomputing.property.MutableProperty;
import net.vectorcomputing.property.Property;
import net.vectorcomputing.property.PropertyMessages;
import net.vectorcomputing.property.PropertyUtils;

import org.eclipse.core.runtime.Assert;

public final class PropertyNode extends AbstractModifiableTreeNode<PropertyNode>
		implements Property, NodeWithAttributes<PropertyNode>,
		Collection<PropertyNode> {

	private final MutableProperty property;
	private final Set<Property> attributes = new HashSet<Property>();

	public PropertyNode(final String key) {
		this(key, null, null);
	}

	public PropertyNode(final String key, final String value) {
		this(key, value, null);
	}

	public PropertyNode(final String key, final PropertyNode parent) {
		this(key, null, parent);
	}

	public PropertyNode(final String key, final String value, final PropertyNode parent) {
		super();
		Assert.isNotNull(key, PropertyMessages.PropertyNode_KeyMustBeSpecified);
		this.property = new MutableProperty(key, value);
	}

	public PropertyNode(final Property property, final PropertyNode parent) {
		this(property == null ? null : property.getKey(), property == null ? null : property.getValue(), parent);
	}

	@Override
	public String getFirst() {
		return property.getFirst();
	}

	@Override
	public String getSecond() {
		return property.getSecond();
	}

	@Override
	public Object[] entries() {
		return property.entries();
	}

	@Override
	public Object getEntry(final int index) {
		return property.getEntry(index);
	}

	@Override
	public String getKey() {
		return property.getKey();
	}

	@Override
	public String getValue() {
		return property.getValue();
	}

	@Override
	public boolean addAttribute(final Property property) {
		Assert.isNotNull(property, "property"); //$NON-NLS-1$
		return attributes.add(PropertyUtils.toImmutableProperty(property));
	}

	@Override
	public boolean containsAttribute(final Property property) {
		Assert.isNotNull(property, "property"); //$NON-NLS-1$
		return attributes.contains(property);
	}

	@Override
	public Property getAttribute(final String key) {
		Assert.isNotNull(key, "key"); //$NON-NLS-1$
		for (Property attr : attributes) {
			if (attr.getKey().equals(key)) {
				return attr;
			}
		}
		return null;
	}

	@Override
	public Set<String> getAttributeKeys() {
		final Set<String> keys = new HashSet<String>(attributes.size());
		for (Property attr : attributes) {
			keys.add(attr.getKey());
		}
		return keys;
	}

	@Override
	public Set<Property> getAttributes() {
		return Collections.unmodifiableSet(attributes);
	}

	@Override
	public boolean isAttributesEmpty() {
		return attributes.isEmpty();
	}

	@Override
	public boolean removeAttribute(final Property property) {
		Assert.isNotNull(property, "property"); //$NON-NLS-1$
		return attributes.remove(property);
	}

	@Override
	public int sizeOfAttributes() {
		return attributes.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		final FastLevelOrderTreeNodeIterator<PropertyNode> iterator = new FastLevelOrderTreeNodeIterator<PropertyNode>(this);
		return iterator.getNodeList().toArray(
				(T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size()));
	}

}
