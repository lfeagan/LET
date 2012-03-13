package net.vectorcomputing.base.number;

import java.util.List;

public interface MultiRange<C extends Comparable<C>> {

	/**
	 * Returns a list of all ranges in the interval.
	 * 
	 * @return list of all ranges in the interval.
	 */
	public abstract List<Range<C>> getRanges();

	/**
	 * Returns the range type used by this interval. The range type determines
	 * the minimum and maximum valid values in a range
	 * 
	 * @return the range type used by this interval.
	 */
	public abstract RangeType<C> getRangeType();

	/**
	 * Includes the specified value in this interval.
	 * @param value 
	 */
	public abstract void includeValue(C value);

	/**
	 * Includes a range of values in the interval.
	 * 
	 * @param begin
	 * @param end
	 */
	public abstract void includeRange(C begin, C end);

	/**
	 * Includes all values in this interval. Uses the range type to determine
	 * the minimum and maximum valid values.
	 */
	public abstract void includeAll();

	/**
	 * Excludes the specified value from this interval.
	 * 
	 * @param value
	 */
	public abstract void excludeValue(C value);

	/**
	 * Excludes the specified range of values from begin to end from this
	 * interval.
	 * 
	 * @param begin
	 * @param end
	 */
	public abstract void excludeRange(C begin, C end);

	/**
	 * Excludes all values from this interval (typically by clearing the list of
	 * ranges).
	 */
	public abstract void excludeAll();

	/**
	 * Returns <code>true</code> if the specified value is contained by a range
	 * that is part of this interval.
	 * 
	 * @param value
	 *            the value to be tested for inclusion in a range that is part
	 *            of this interval
	 * @return <code>true</code> if the specified value is contained by a range
	 *         that is part of this interval
	 */
	public abstract boolean isIncluded(C value);

}