package net.vectorcomputing.base.number;

public interface RangeWithValue<C extends Comparable<C>, V> extends Range<C> {
	
	public V getValue();
	
	public void setValue(V value);

}
