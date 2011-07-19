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
package net.vectorcomputing.node.tree.iterator;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.eclipse.core.runtime.Assert;

import net.vectorcomputing.node.NodeMessages;
import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.TreeNodeLocation;

/**
 * Iterates over a {@link TreeNodeLocation} as though it were a list of tree
 * nodes.
 * 
 * @param <T>
 *            the type of tree node to iterate
 * @see ListIterator
 */
public class TreeNodeLocationIterator<T extends TreeNode<T>> implements ListIterator<T> {

	private static final int ROOT_DEPTH = -1;
	private final TreeNodeLocation<T> location;
	private final T root;
	private int depth = ROOT_DEPTH;

	public TreeNodeLocationIterator(final T root, final TreeNodeLocation<T> location) {
		Assert.isNotNull(root, "root"); //$NON-NLS-1$
		Assert.isNotNull(location, "location"); //$NON-NLS-1$
		this.root = root;
		this.location = location;
	}

	@Override
	public boolean hasNext() {
		return (depth < location.getDepth());
	}
	
	@Override
	public T next() {
		if (hasNext()) {
			++depth;
			return location.get(root, depth);
		} else {
			throw new NoSuchElementException(NodeMessages.NodeIterator_NoNextElementExists);
		}
	}
	
	@Override
	public boolean hasPrevious() {
		return !(depth == ROOT_DEPTH);
	}
	
	@Override
	public T previous() {
		if (hasPrevious()) {
			--depth;
			return location.get(root, depth);
		} else {
			throw new NoSuchElementException(NodeMessages.NodeIterator_NoPreviousElementExists);
		}
	}
	
	@Override
	public int nextIndex() {
		if (hasNext()) {
			return depth + 1;
		} else {
			return location.getDepth();
		}
	}

	@Override
	public int previousIndex() {
		if (hasPrevious()) {
			return depth - 1;
		} else {
			return ROOT_DEPTH;
		}		
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void set(T e) {
		throw new UnsupportedOperationException();		
	}
	
	@Override
	public void add(T e) {
		throw new UnsupportedOperationException();
	}
}
