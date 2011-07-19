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
package net.vectorcomputing.property.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.vectorcomputing.node.tree.TreeNodes;
import net.vectorcomputing.property.node.PropertyNode;

import org.junit.Test;

public class TestNodeUtils {

	/* Example
	 *     A
	 *    /
	 *   B
	 *  / \
	 * C    D
	 *     /
	 *    E
	 * 
	 * paths to root:
	 * startToRoot  = [ C , B , A ]
	 * finishToRoot = [ E , D , B , A ]
	 * 
	 * after while loop:
	 * sToRIndex = 0
	 * fToRIndex = 1
	 * 
	 * at end:
	 * startToFinish = [ C , B , D , E ]
	 */
	@Test
	public void getPathTest() {
		final PropertyNode a = new PropertyNode("A");
		final PropertyNode b = new PropertyNode("B", a);
		final PropertyNode c = new PropertyNode("C", b);
		final PropertyNode d = new PropertyNode("D", b);
		final PropertyNode e = new PropertyNode("E", d);

		final List<PropertyNode> expectedPath = new ArrayList<PropertyNode>(4);
		expectedPath.add(c);
		expectedPath.add(b);
		expectedPath.add(d);
		expectedPath.add(e);
		
		final List<PropertyNode> path = TreeNodes.getPath(c, e);
		assertEquals(expectedPath, path);
	}
}
