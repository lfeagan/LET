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

import java.util.Set;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.property.Property;

/**
 * Interface that extends node by adding the ability to attach attributes to a
 * node as {@link Property}.
 * 
 * @param <T>
 *            the node type
 */
public interface NodeWithAttributes<T extends TreeNode<T>> extends TreeNode<T> {

	/**
	 * Adds to the list of attributes associated with this node.
	 * 
	 * @param property
	 *            the property to add to this node's list of attributes
	 * @return <code>true</code> if the node's attributes changed
	 */
	public boolean addAttribute(Property property);

	/**
	 * Returns <code>true</code> if this node's attributes contains the
	 * specified property.
	 * 
	 * @param property
	 *            property whose presence is this node's attributes is to be
	 *            tested
	 * @return <code>true</code> if this node's attributes contains the
	 *         specified property
	 */
	public boolean containsAttribute(Property property);

	/**
	 * Returns the attribute with the specified key.
	 * 
	 * @param key
	 *            the key of the attribute to be found
	 * @return the attribute the specified key or <code>null</code> if none is
	 *         found
	 */
	public Property getAttribute(String key);

	/**
	 * Returns a set containing the keys of all attributes associated with this
	 * node.
	 * 
	 * @return a set containing the keys of all attributes associated with this
	 *         node
	 */
	public Set<String> getAttributeKeys();

	/**
	 * Returns an unmodifiable set containing the attributes associated with
	 * this node.
	 * 
	 * @return an unmodifiable set containing the attributes associated with
	 *         this node
	 */
	public Set<Property> getAttributes();

	/**
	 * Returns <code>true</code> if this node has no attributes.
	 * 
	 * @return <code>true</code> if this node has no attributes
	 */
	public boolean isAttributesEmpty();

	/**
	 * Removes a property from this node's list of attributes.
	 * 
	 * @param property
	 *            the property to remove from this node's list of attributes
	 * @return <code>true</code> if the node's attributes changed
	 */
	public boolean removeAttribute(Property property);

	/**
	 * Returns the number of attributes associated with this node.
	 * 
	 * @return the number of attributes associated with this node
	 */
	public int sizeOfAttributes();

}
