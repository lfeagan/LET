package net.vectorcomputing.base.number;

public interface RangeType<C extends Comparable<C>> {
	
	public String name();
	
	public C minimum();

	public C maximum();

	public boolean isValid(C value);
	
	public void validate(C value) throws IllegalArgumentException;

	public Range<C> newRange(C begin, C end);
	
}
