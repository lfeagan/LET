package net.vectorcomputing.node.directed;

import java.util.Set;

public interface DirectedNode<T extends DirectedNode<T>> {

	public int getInDegree();
	
	public Set<T> getIncomingNodes();
	
	public int getOutDegree();
	
	public Set<T> getOutgoingNodes();
}
