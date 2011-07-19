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
package net.vectorcomputing.node;

import org.eclipse.osgi.util.NLS;

/**
 * Externalized strings.
 */
public class NodeMessages extends NLS {
	private static final String BUNDLE_NAME = "net.vectorcomputing.node.messages"; //$NON-NLS-1$
	public static String NodeIterator_NoNextElementExists;
	public static String NodeIterator_NoPreviousElementExists;
	public static String TreeNode_CannotAddAnAncestorAsChild;
	public static String TreeNode_CannotAddSelfAsChild;
	public static String NodeLocation_DepthIsLessThanZero;
	public static String Nodes_ListSizeMustBeGreaterThanZero;
	public static String TreeNodes_StartAndFinishNodesDoNotShareACommonRoot;
	public static String TreeNodes_ZeroNodesSatisfyTheConstraint;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NodeMessages.class);
	}

	private NodeMessages() {
	}
}
