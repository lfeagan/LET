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
package net.vectorcomputing.node.tree.iterator.light;

import net.vectorcomputing.node.tree.TreeNode;
import net.vectorcomputing.node.tree.iterator.TreeNodeIterator;

/**
 * A level-order tree node iterator that consumes very little memory. A light
 * iterator should be preferred over its fast variant whenever:
 * <ol>
 * <li>memory is scarce, or</li>
 * <li>only a small portion of the tree will be iterated.</li>
 * </ol>
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public class LightLevelOrderTreeNodeIterator<T extends TreeNode<T>> extends
		AbstractLightTreeNodeIterator<T> {

	private int desiredDepth = 0;

	public LightLevelOrderTreeNodeIterator(final T root) {
		this(root, TreeNodeIterator.NO_DEPTH_LIMIT);
	}

	public LightLevelOrderTreeNodeIterator(final T rootNode, final int depthLimit) {
		super(rootNode, depthLimit);
	}

	@Override
	protected void nextNode() {
		if (explorer.getDepth() == desiredDepth) {
			debug("at desired depth"); //$NON-NLS-1$
			if (explorer.goToNextSibling()) {
				debug("node has more siblings"); //$NON-NLS-1$
			} else {
				debug("node has no more siblings, going to parent next sibling"); //$NON-NLS-1$
				explorer.goToParentNextSibling();

				if (explorer.hasParent()) {
					// FIXME It is possible this will be called from a location
					// where it is not possible to go to the desired depth
					try {
						explorer.goToDepth(desiredDepth);
					} catch (IllegalArgumentException e) {
						terminateOrMoveToNextLevel();
					}
				} else {
					/*
					 * After repeatedly calling hasParent, arrived back at the
					 * root node
					 */
					debug("at root"); //$NON-NLS-1$
					terminateOrMoveToNextLevel();
				}
			}
		} else {
			debug("going to desired depth"); //$NON-NLS-1$
			explorer.goToDepth(desiredDepth);
		}
	}

	/**
	 * We ran out of parent's siblings to go to. One of two situations is
	 * possible here:
	 * <ol>
	 * <li>the next depth exists ==> go to it, or</li>
	 * <li>the next depth does not exist ==> iteration is complete.</li>
	 * </ol>
	 */
	private void terminateOrMoveToNextLevel() {
		if (explorer.hasDepth(desiredDepth + 1)) {
			debug("next depth exists"); //$NON-NLS-1$
			goToNextDepth();
		} else {
			debug("next depth does not exist"); //$NON-NLS-1$
			nextNode = null;
		}
	}

	/**
	 * <p>
	 * Goes to the next depth in the tree.
	 * </p>
	 * <p>
	 * <b>WARNING:</b> Calling this method when no next level exists will result
	 * in an infinite loop.
	 * </p>
	 */
	private void goToNextDepth() {
		debug("going to next level"); //$NON-NLS-1$
		++desiredDepth;
		if (isDepthValid(desiredDepth)) {
			explorer.goToRoot();
			explorer.goToDepth(desiredDepth);
		} else {
			nextNode = null;
		}
	}

}
