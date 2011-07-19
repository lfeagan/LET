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
package net.vectorcomputing.node.tree.search;

import java.util.List;

import net.vectorcomputing.node.tree.TreeNode;

/**
 * Interface used to search for tree nodes that satisfy a constraint.
 * 
 * @param <T>
 *            the type of tree node to be searched
 */
public interface ITreeNodeSearcher<T extends TreeNode<T>> {

	public static final int NO_RESULTS_SIZE_LIMIT = -1;

	/**
	 * Performs a search starting with the top tree node.
	 * 
	 * @param top
	 *            the tree node to search downward from
	 * @return the search results
	 */
	public List<T> search(final T top);

	/**
	 * Configures the depth to limit searches to.
	 * 
	 * @param depthLimit
	 *            the depth to limit searches to
	 * @throws IllegalArgumentException
	 *             if the depth limit is less than or equal to zero
	 */
	public void setDepthLimit(final int depthLimit);

	/**
	 * Resets the depth limit to be infinite.
	 */
	public void unsetDepthLimit();

	/**
	 * Sets the maximum number of results that a search will return.
	 * 
	 * @param resultsSizeLimit
	 *            the maximum size of the results returned from a search
	 * @throws IllegalArgumentException
	 *             if the results size limit is less than or equal to zero
	 */
	public void setResultsSizeLimit(final int resultsSizeLimit);

	/**
	 * Resets the maximum size of the results returned from a search to be
	 * infinite.
	 */
	public void unsetResultsSizeLimit();

}
