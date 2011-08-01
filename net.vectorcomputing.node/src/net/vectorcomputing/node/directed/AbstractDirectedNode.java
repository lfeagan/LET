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
package net.vectorcomputing.node.directed;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDirectedNode<T extends DirectedNode<T>> implements DirectedNode<T> {

	private final Set<T> incomingNodes = new HashSet<T>();
	private final Set<T> outgoingNodes = new HashSet<T>();

	@Override
	public int getInDegree() {
		return incomingNodes.size();
	}
	
	@Override
	public Set<T> getIncomingNodes() {
		return Collections.unmodifiableSet(incomingNodes);
	}
	
	@Override
	public int getOutDegree() {
		return outgoingNodes.size();
	}
	
	@Override
	public Set<T> getOutgoingNodes() {
		return Collections.unmodifiableSet(outgoingNodes);
	}

}
