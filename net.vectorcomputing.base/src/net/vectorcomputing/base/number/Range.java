package net.vectorcomputing.base.number;

public interface Range<C extends Comparable<C>> {

	public RangeType<C> getRangeType();
	
	public C getBegin();
	
	public void setBegin(C begin);
	
	public C getEnd();
	
	public void setEnd(C end);
	
	public int compareToBegin(final C value);
	
	public int compareToEnd(final C value);

	public boolean isAdjacentToBegin(final C value);

	public boolean includes(final C value);
	
	public boolean isAdjacentToEnd(final C value);
	
}
