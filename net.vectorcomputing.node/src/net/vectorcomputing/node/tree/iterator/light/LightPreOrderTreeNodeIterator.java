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
 * A pre-order tree node iterator that consumes very little memory. . A light
 * iterator should be preferred over its fast variant whenever:
 * <ol>
 * <li>memory is scarce, or</li>
 * <li>only a small portion of the tree will be iterated.</li>
 * </ol>
 * 
 * @param <T>
 *            the type of tree node to iterate
 */
public class LightPreOrderTreeNodeIterator<T extends TreeNode<T>> extends
		AbstractLightTreeNodeIterator<T> {

	public LightPreOrderTreeNodeIterator(final T root) {
		this(root, TreeNodeIterator.NO_DEPTH_LIMIT);
	}

	public LightPreOrderTreeNodeIterator(final T rootNode, final int maxDepth) {
		super(rootNode, maxDepth);
	}

	protected void nextNode() {
		// Check for a root with no children
		if (explorer.isRoot() && !explorer.hasChildren()) {
			debug("iterator: root tree node has no children"); //$NON-NLS-1$
			nextNode = null;
		} else {
			if (explorer.hasChildren() && isDepthValid()) {
				debug("iterator: tree node has children and depth is valid"); //$NON-NLS-1$
				explorer.goToChild();
			} else {
				if (explorer.hasSiblingsToRight()) {
					explorer.goToNextSibling();
				} else {
					explorer.goToParentNextSibling();
					if (explorer.isRoot()) {
						nextNode = null;
					}
				}
			}
		}
	}

}
