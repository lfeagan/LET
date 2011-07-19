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

import net.vectorcomputing.node.NodeMessages;

import org.eclipse.core.runtime.Assert;

/**
 * Defines a very general concept of tree node locations by storing the child
 * offset at each level of a tree.
 * 
 * @param <T>
 *            the type of tree node to store locations for
 */
public class TreeNodeLocation<T extends TreeNode<T>> implements
		Comparable<TreeNodeLocation<T>> {

	private static final int DEFAULT_CHILD_OFFSETS_INDEX = -1;
	private int childOffsetsIndex = DEFAULT_CHILD_OFFSETS_INDEX;
	private final ArrayList<Integer> childOffsets = new ArrayList<Integer>();

	/**
	 * Constructor with zero depth.
	 */
	public TreeNodeLocation() {
	}

	/**
	 * Copy constructor.
	 * 
	 * @param source
	 *            the tree node location to copy from
	 */
	public TreeNodeLocation(final TreeNodeLocation<T> source) {
		Assert.isNotNull(source, "source"); //$NON-NLS-1$
		this.childOffsetsIndex = source.childOffsetsIndex;
		this.childOffsets.addAll(source.childOffsets);
	}

	/**
	 * Resets the location to the initial state.
	 */
	public void reset() {
		childOffsets.clear();
		childOffsetsIndex = DEFAULT_CHILD_OFFSETS_INDEX;
	}

	/**
	 * Updates the location to refer to the parent of the last tree node.
	 * 
	 * @return <code>true</code> if the location changed
	 */
	public boolean goToParent() {
		if (isRoot()) {
			// the root node has no parents ==> location is unchanged
			return false;
		}

		childOffsets.remove(childOffsetsIndex);
		--childOffsetsIndex;
		return true;
	}

	/**
	 * Determines if the location refers to the root tree node at present.
	 * 
	 * @return <code>true</code> if the location refers to the root tree node at
	 *         present.
	 */
	public final boolean isRoot() {
		return childOffsetsIndex == DEFAULT_CHILD_OFFSETS_INDEX;
	}

	/**
	 * Determines if the location refers to a non-root tree node at present.
	 * 
	 * @return <code>true</code> if the location refers to a non-root tree node
	 *         at present
	 */
	public final boolean isNonRoot() {
		return childOffsetsIndex > DEFAULT_CHILD_OFFSETS_INDEX;
	}

	/**
	 * Updates the location to refer to the first child of the current tree
	 * node.
	 */
	public void goToChild() {
		childOffsets.add(0);
		++childOffsetsIndex;
	}

	/**
	 * Updates the location to refer to the child to the right of the current
	 * tree node.
	 * 
	 * @return <code>true</code> if the location changed
	 */
	public boolean goToNextSibling() {
		if (isRoot()) {
			// the root node has no siblings ==> location is unchanged
			return false;
		}

		final int currentChildOffset = childOffsets.get(childOffsetsIndex);
		childOffsets.set(childOffsetsIndex, currentChildOffset + 1);
		return true;
	}

	/**
	 * Updates the location to refer to the child to the left of the current
	 * tree node.
	 * 
	 * @return <code>true</code> if the location changed
	 */
	public boolean goToPreviousSibling() {
		if (isRoot()) {
			// the root node has no siblings ==> location is unchanged
			return false;
		}

		final int currentOffset = childOffsets.get(childOffsetsIndex);
		if (currentOffset > 0) {
			childOffsets.set(childOffsetsIndex, currentOffset - 1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Makes this location the same as another tree node location by copying all
	 * values.
	 * 
	 * @param source
	 *            the tree node location to copy from
	 */
	public void copy(TreeNodeLocation<T> source) {
		Assert.isNotNull(source, "source"); //$NON-NLS-1$
		childOffsets.clear();
		childOffsets.addAll(source.childOffsets);
		childOffsetsIndex = source.childOffsetsIndex;
	}

	/**
	 * Returns the depth of the location at present.
	 * 
	 * @return the depth of the location at present.
	 */
	public int getDepth() {
		return childOffsetsIndex + 1;
	}

	/**
	 * Returns the offset of the child at the specified depth.
	 * 
	 * @param depth
	 *            how deep to look into location indices
	 * @return the value at the specified depth index
	 */
	public int getChildOffset(final int depth) {
		if (depth <= 0) {
			return 0;
		} else {
			return childOffsets.get(depth - 1);
		}
	}

	/**
	 * Returns the tree node at the current location from the beginning tree
	 * node.
	 * 
	 * @param begin
	 *            the tree node to begin at
	 * @return the tree node at the current location from the beginning tree
	 *         node
	 */
	public T get(T begin) {
		Assert.isNotNull(begin, "begin"); //$NON-NLS-1$
		return get(begin, getDepth());
	}

	/**
	 * Returns the tree node at the specified depth along the current location
	 * path from the root.
	 * 
	 * @param begin
	 *            the tree node to begin walking the tree at
	 * @param depth
	 *            how deep into this location to stop walking the tree
	 * @return the tree node at the specified depth along this location from the
	 *         beginning tree node
	 */
	public T get(final T begin, final int depth) {
		Assert.isNotNull(begin, "begin"); //$NON-NLS-1$
		if (depth < 0) {
			throw new IllegalArgumentException(
					NodeMessages.NodeLocation_DepthIsLessThanZero);
		}

		T node = begin;
		int index = 0;
		while (index < depth) {
			node = node.getChild(childOffsets.get(index));
			++index;
		}
		return node;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childOffsets == null) ? 0 : childOffsets.hashCode());
		result = prime * result + childOffsetsIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof TreeNodeLocation<?>)) {
			return false;
		}

		final TreeNodeLocation<?> other = (TreeNodeLocation<?>) obj;
		if (childOffsets == null) {
			if (other.childOffsets != null) {
				return false;
			}
		} else if (!childOffsets.equals(other.childOffsets)) {
			return false;
		}

		if (childOffsetsIndex != other.childOffsetsIndex) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NodeLocation [childOffsetsIndex="); //$NON-NLS-1$
		builder.append(childOffsetsIndex);
		builder.append(", childOffsets="); //$NON-NLS-1$
		builder.append(childOffsets);
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}

	/**
	 * Performs a component-wise comparison between two locations by looking at
	 * each level in succession until one that is greater or less than the other
	 * is found. Only if all levels are equal are the two equal. In the case
	 * where the depths are unequal and all of parts of the length-in-common are
	 * equal, the longer location is considered to be greater, even if the
	 * non-common components are all zero.
	 */
	@Override
	public int compareTo(TreeNodeLocation<T> other) {
		Assert.isNotNull(other, "other"); //$NON-NLS-1$
		final int indexDelta = compareChildOffsetsIndex(other);
		if (indexDelta != 0) {
			return indexDelta;
		}

		return compareToInternal(other);
	}

	private final int compareChildOffsetsIndex(final TreeNodeLocation<T> other) {
		return this.childOffsetsIndex - other.childOffsetsIndex;
	}

	/**
	 * Returns the lesser of child offsets index of the current location or
	 * another location.
	 * 
	 * @param other
	 * @return
	 */
	private int getSmallestChildOffsetsIndex(final TreeNodeLocation<T> other) {
		return childOffsetsIndex < other.childOffsetsIndex ? childOffsetsIndex : other.childOffsetsIndex;
	}

	/**
	 * Compares another tree node location to this, but only compares up-to the
	 * specified depth. Offset values are compared starting with child offsets
	 * index zero.
	 * <ul>
	 * <li>If the offsets at a particular level are equal, the next depth level
	 * is compared.</li>
	 * <li>If the offsets at a particular level are not equal, if a</li>
	 * <li>If the offsets at all depths are equal, zero will be returned.</li>
	 * </ul>
	 * 
	 * @param other
	 * @return
	 */
	private int compareToInternal(final TreeNodeLocation<T> other) {
		final int commonIndex = getSmallestChildOffsetsIndex(other);
		for (int i = 0; i < commonIndex; ++i) {
			int thisLocationValue = this.childOffsets.get(i);
			int otherLocationValue = other.childOffsets.get(i);
			if (thisLocationValue < otherLocationValue) {
				return thisLocationValue - otherLocationValue;
			} else if (thisLocationValue > otherLocationValue) {
				return thisLocationValue - otherLocationValue;
			}
		}
		return 0;
	}

}
