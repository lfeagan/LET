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
