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
package net.vectorcomputing.node.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.vectorcomputing.node.NodeMessages;
import net.vectorcomputing.node.exception.CycleException;
import net.vectorcomputing.node.tree.constraint.TreeNodeConstrainEquals;
import net.vectorcomputing.node.tree.constraint.TreeNodeConstraint;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;
import net.vectorcomputing.node.tree.iterator.fast.FastLevelOrderTreeNodeIterator;
import net.vectorcomputing.node.tree.iterator.light.LightLevelOrderTreeNodeIteratorFactory;
import net.vectorcomputing.node.tree.search.PreOrderTreeNodeSearcher;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.AssertionFailedException;

/**
 * Abstract class to provide most of the implementation necessary for a
 * modifiable tree node.
 * 
 * @param <T>
 *            the tree node type
 */
public abstract class AbstractModifiableTreeNode<T extends AbstractModifiableTreeNode<T>>
		implements ModifiableTreeNode<T>, Collection<T> {

	private T parent = null;
	private final List<T> children = new ArrayList<T>();
	
	@SuppressWarnings("unchecked")
	@Override
	public final boolean addChild(final T child) {
		Assert.isNotNull(child, "child"); //$NON-NLS-1$
		if (!children.contains(child)) {
			checkForCycle(child, (T) this);
			child.detachFromParent();
			child.attachToParent((T) this);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Detaches this tree node from its current parent by:
	 * <ol>
	 * <li>removing this tree node from its parent's list of children, and</li>
	 * <li>unsetting this tree node's parent (by setting it to null).</li>
	 * </ol>
	 */
	private void detachFromParent() {
		if (parent != null) {
			parent.children.remove(this);
			parent = null;			
		}
	}
	
	/**
	 * Attaches this tree node to a new parent by:
	 * <ol>
	 * <li>setting this tree node's parent reference, and</li>
	 * <li>adding this tree node to the parent's list of children.</li>
	 * </ol>
	 * 
	 * @param parent the new parent to attach this tree node to
	 */
	@SuppressWarnings("unchecked")
	private void attachToParent(final T parent) {
		assert parent != null;
		this.parent = parent;
		this.parent.children.add((T) this);
	}

	@Override
	public final T getChild(final int index) {
		if (index < 0) {
			throw new AssertionFailedException("index >= 0"); //$NON-NLS-1$
		}
		return children.get(index);
	}
	
	@Override
	public List<? extends T> getChildren() {
		return Collections.unmodifiableList(children);
	}

	@Override
	public T getParent() {
		return parent;
	}

	@Override
	public T getRoot() {
		@SuppressWarnings("unchecked")
		T node = (T) this;
		while (node.parent != null) {
			node = node.parent;
		}
		return node;
	}

	@Override
	public boolean hasChildren() {
		return ! children.isEmpty();
	}

	@Override
	public boolean hasParent() {
		return (parent != null);
	}
	
	@Override
	public boolean removeChild(final T child) {
		Assert.isNotNull(child, "child"); //$NON-NLS-1$
		if (children.contains(child)) {
			child.detachFromParent();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public final T setParent(final T newParent) {
		Assert.isNotNull(newParent, "newParent"); //$NON-NLS-1$
		final T oldParent = this.parent;
		detachFromParent();
		attachToParent(newParent);
		return oldParent;
	}

	@Override
	public int sizeOfChildren() {
		return children.size();
	}

	@Override
	public final T unsetParent() {
		final T oldParent = parent;
		detachFromParent();
		return oldParent;
	}

	private void checkForCycle(final T child, final T parent) {
		assert child != null;
		assert parent != null;
		
		if (child == parent) {
			throw new CycleException(NodeMessages.TreeNode_CannotAddSelfAsChild);
		}
		
		final TreeNodeConstraint<T> constraint = new TreeNodeConstrainEquals<T>(child);
		final PreOrderTreeNodeSearcher<T> searcher = new PreOrderTreeNodeSearcher<T>(constraint);
		searcher.setResultsSizeLimit(1);
		final List<T> results = searcher.search(parent.getRoot());
	
		if (results.size() > 0) {
			throw new CycleException(NodeMessages.TreeNode_CannotAddAnAncestorAsChild);
		}		
	}
	
	@Override
	public int getInDegree() {
		if (hasParent()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Set<T> getIncomingNodes() {
		if (hasParent()) {
			final Set<T> incomingNodes = new HashSet<T>();
			incomingNodes.add(parent);
			return incomingNodes;
		} else {
			return Collections.emptySet();
		}
	}

	@Override
	public int getOutDegree() {
		return sizeOfChildren();
	}

	@Override
	public Set<T> getOutgoingNodes() {
		return new HashSet<T>(children);
	}
	
	@Override
	public final boolean add(final T newChild) {
		return addChild(newChild);
	}
	
	@Override
	public final boolean addAll(final Collection<? extends T> newChildren) {
		boolean retVal = false;
		for (T newChild : newChildren) {
			if (addChild(newChild)) {
				retVal = true;
			}
		}
		return retVal;
	}
	
	@Override
	public void clear() {
		for (T child : children) {
			child.unsetParent();
		}
	}
	
	@Override
	public boolean contains(final Object object) {
		Assert.isNotNull(object, "object"); //$NON-NLS-1$
		@SuppressWarnings("unchecked")
		final TreeNodeIterator<T> iter = (new LightLevelOrderTreeNodeIteratorFactory<T>()).create((T) this);
		if (object != null) {
			while (iter.hasNext()) {
				if (iter.next().equals(object)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean containsAll(final Collection<?> collection) {
		Assert.isNotNull(collection, "collection"); //$NON-NLS-1$
		Iterator<?> collectionIterator = collection.iterator();
		while (collectionIterator.hasNext()) {
		    if (!contains(collectionIterator.next())) {
		    	return false;
		    }
		}
		return true;
	}

	/**
	 * The code that should be here is:
	 * 
	 * <pre>
	 * return size() == 0;
	 * </pre>
	 * 
	 * However, computing the size is unnecessary as it is always greater than
	 * zero. So, as an optimisation, always return <code>false</code>.
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() {
		return (new LightLevelOrderTreeNodeIteratorFactory<T>()).create((T) this);
	}

	@Override
	public boolean remove(final Object obj) {
		throw new UnsupportedOperationException();
	}
	
	public boolean removeAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean retainAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Computes the number of nodes present in the tree moving downward starting
	 * with this tree node.
	 * 
	 * @return the number of nodes below this tree node plus 1 for this tree
	 *         node
	 */
	@Override
	public int size() {
		int size = 1;
		for (T child : children) {
			size += child.size();
		}
		return size;
	}
	
	@Override
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		final FastLevelOrderTreeNodeIterator<T> iterator = new FastLevelOrderTreeNodeIterator<T>((T) this);
		return iterator.getNodeList().toArray();
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> T[] toArray(T[] a) {
//		final FastLevelOrderNodeIterator<T> iterator = new FastLevelOrderNodeIterator<T>((T)this);
//		return iterator.getNodeList().toArray( (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size()));
//	}
	
}
