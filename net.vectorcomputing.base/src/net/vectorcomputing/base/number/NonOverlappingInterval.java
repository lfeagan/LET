package net.vectorcomputing.base.number;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class NonOverlappingInterval<C extends Comparable<C>> implements MultiRange<C> {

	private final RangeType<C> type;
	private final List<Range<C>> ranges = new ArrayList<Range<C>>();
	
	public NonOverlappingInterval(RangeType<C> type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#getRanges()
	 */
	@Override
	public List<Range<C>> getRanges() {
		return ranges;
	}
	
	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#getRangeType()
	 */
	@Override
	public RangeType<C> getRangeType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#includeValue(C)
	 */
	@Override
	public void includeValue(C value) {
		if (!type.isValid(value)) {
			String message = MessageFormat.format(
					"Invalid value of {0} specified. {1} <= value <= {2}",
					value, type.minimum(), type.maximum());
			throw new IllegalArgumentException(message);
		}

		if (ranges.size() == 0) {
			ranges.add(type.newRange(value, value));
		} else {
			int i_max = ranges.size() - 1;
			for (int i=0; i <= i_max; ++i) {
				Range<C> range = ranges.get(i);
				
				if (range.compareToBegin(value) > 0) { // previously: if (value < range.getBegin()) {
					if (range.isAdjacentToBegin(value)) {
						range.setBegin(value);
						break;
					} else {
						ranges.add(i, type.newRange(value,value));
						break;
					}
				} else if (range.includes(value)) {
					// the value is already included in an existing range, so simply return
					break;
				} else if (range.compareToEnd(value) < 0) { // previously: (value > range.getEnd())
					if (range.isAdjacentToEnd(value)) {
						range.setEnd(value);
						break;
					} else if (i == i_max) {
						ranges.add(type.newRange(value,value));
						break;
					}
				}
			}
		}
		mergeRanges();
	}
	
	private void mergeRanges() {
		int i = 0;
		int max_i = ranges.size() - 1;
		while (i < max_i) {
			Range<C> first = ranges.get(i);
			Range<C> second = ranges.get(i+1);
			if (first.isAdjacentToEnd(second.getBegin())) { // previously: (first.getEnd() + 1 == second.getBegin())
				first.setEnd(second.getEnd());
				ranges.remove(second);
				--max_i;
			} else {
				++i;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#includeRange(C, C)
	 */
	@Override
	public void includeRange(C begin, C end) {
		if (!type.isValid(begin)) {
			String message = MessageFormat.format(
					"Invalid begin value of {0} specified. {1} <= begin <= {2}",
					begin, type.minimum(), type.maximum());
			throw new IllegalArgumentException(message);
		}

		if (!type.isValid(end)) {
			String message = MessageFormat.format(
					"Invalid end value of {0} specified. {1} <= end <= {2}",
					end, type.minimum(), type.maximum());
			throw new IllegalArgumentException(message);
		}
		
		// Base case: no ranges exist, simply add a new range
		if (ranges.size() == 0) {
			ranges.add(type.newRange(begin, end));
			return;
		}
		
		// If begin is equal to end, use includeValue to simplify things
		if (begin == end) {
			includeValue(begin);
			return;
		}
		
		boolean doneIncluding = false;

update:		for (int index = 0; index < ranges.size(); ++index) {
			if (doneIncluding) {
				break;
			}
			Range<C> range = ranges.get(index);
			// if begin and end are left of first range, either create a new range or merge if adjacent
			if (range.compareToBegin(begin) > 0) { // previously: (begin < range.getBegin())
				if ((range.compareToBegin(end) > 0) && !range.isAdjacentToBegin(end)) {
					// scenario: existing = [[4,6]], new is [1,2] (or something else entirely to the left and not adjacent)
					ranges.add(index, type.newRange(begin, end));
					doneIncluding = true;
					break update;
				} else if (range.isAdjacentToBegin(end) || range.includes(end)) {
					// scenario: existing = [[4,6]], new is [1,3], [1,4], [1,5], or [1,6]
					range.setBegin(begin);
					doneIncluding = true;
					break update;
				} else if (range.isAdjacentToEnd(end)) {
					// scenario: existing = [[4,6]], new is [1,7]
					range.setBegin(begin);
					range.setEnd(end);
					doneIncluding = true;
					break update;
				} else {
					range.setBegin(begin);
					doneIncluding = processRightRange(range, begin, end, index);
					assert(doneIncluding);
					if (doneIncluding == false) {
						System.out.println("WTF");
					}
				}
			} else if (range.includes(begin)) {
				if (range.includes(end)) {
					doneIncluding = true;
					break update;
				} else if (range.isAdjacentToEnd(end)) {
					range.setEnd(end);
					doneIncluding = true;
					break update;
				} else {
					doneIncluding = processRightRange(range, begin, end, index);
					assert(doneIncluding);
				}
			} else if (range.isAdjacentToEnd(begin)) {
				System.out.println("case missed");
			}
		}
		
		if (!doneIncluding) {
			ranges.add(type.newRange(begin, end));
			// System.out.println("not done including " + begin + " " + end);
		}

		mergeRanges();		
	}
	
	private boolean processRightRange(Range<C> range, C begin, C end, int index) {
		boolean doneIncluding = false;
		if (ranges.size() == 1) {
			range.setEnd(end);
			return true;
		}

		// scenario: existing = [[4,6], [10,12]], new is [1, 12]
		final int rightIndex = index + 1;
		while (rightIndex < ranges.size()) {
			Range<C> rightRange = ranges.get(rightIndex);
			if (rightRange.compareToBegin(end) > 0 && !rightRange.isAdjacentToBegin(end)) {
				range.setEnd(end);
				return true;
			} else if (rightRange.isAdjacentToBegin(end) || rightRange.includes(end)) {
				range.setEnd(rightRange.getEnd());
				ranges.remove(rightIndex);
				return true;
			} else if (rightRange.isAdjacentToEnd(end)) {
				range.setEnd(end);
				ranges.remove(rightIndex);
				return true;
			} else if (rightRange.compareToEnd(end) < 0) {
				// System.out.println("range.end < end");
				ranges.remove(rightIndex);
			} else {
				throw new RuntimeException("Somehow the end is not to the left, included, or to the right of the range");
			}
		}

		if (rightIndex >= ranges.size() && !doneIncluding) {
			range.setEnd(end);
			return true;
		}
		
		throw new RuntimeException("Processing of interval right-range failed");
	}
	
	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#includeAll()
	 */
	@Override
	public void includeAll() {
		excludeAll();
		includeRange(type.minimum(), type.maximum());
	}

	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#excludeValue(C)
	 */
	@Override
	public void excludeValue(C value) {

	}

	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#excludeRange(C, C)
	 */
	@Override
	public void excludeRange(C begin, C end) {

	}

	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#excludeAll()
	 */
	@Override
	public void excludeAll() {
		ranges.clear();
	}

	/* (non-Javadoc)
	 * @see net.vectorcomputing.base.number.IInterval#isIncluded(C)
	 */
	@Override
	public boolean isIncluded(C value) {
		for (Range<C> interval : ranges) {
			if (interval.includes(value)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ranges: [");
		boolean afterFirst = false;
		for (Range<C> range : ranges) {
			if (afterFirst) {
				sb.append(",");
			}
			if (range.getBegin() == range.getEnd()) {
				sb.append(range.getBegin());
			} else {
				sb.append('[');
				sb.append(range.getBegin());
				sb.append(',');
				sb.append(range.getEnd());
				sb.append(']');
			}
			afterFirst = true;
		}
		sb.append(']');
		return sb.toString();
	}

}
