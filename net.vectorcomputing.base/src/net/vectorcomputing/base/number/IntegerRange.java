package net.vectorcomputing.base.number;

public class IntegerRange implements Range<Integer> {

	private final RangeType<Integer> rangeType;
	private Integer begin;
	private Integer end;

	public IntegerRange(final Integer begin, final Integer end) {
		this.rangeType = IntegerRangeType.UNLIMITED;
		this.begin = begin;
		this.end = end;
	}
	
	public IntegerRange(final RangeType<Integer> rangeType, final Integer begin, final Integer end) {
		this.rangeType = rangeType;
		this.begin = begin;
		this.end = end;
	}
	
	@Override
	public RangeType<Integer> getRangeType() {
		return rangeType;
	}
	
	@Override
	public Integer getBegin() {
		return begin;
	}
	
	@Override
	public void setBegin(Integer begin) {
		this.rangeType.validate(begin);
		this.begin = begin;
	}
	
	@Override
	public Integer getEnd() {
		return end;
	}

	@Override
	public void setEnd(Integer end) {
		this.end = end;
	}
	
	@Override
	public int compareToBegin(Integer value) {
		return begin.compareTo(value);
	}

	@Override
	public int compareToEnd(Integer value) {
		return end.compareTo(value);
	}

	@Override
	public boolean includes(final Integer value) {
		return (begin <= value) && (value <= end);
	}

	@Override
	public boolean isAdjacentToBegin(final Integer value) {
		return (value + 1 == begin);
	}

	@Override
	public boolean isAdjacentToEnd(final Integer value) {
		return (value - 1 == end);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + begin;
		result = prime * result + end;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Range)) {
			return false;
		}
		IntegerRange other = (IntegerRange) obj;
		if (begin != other.begin) {
			return false;
		}
		if (end != other.end) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IntegerRange [begin=");
		builder.append(begin);
		builder.append(", end=");
		builder.append(end);
		builder.append("]");
		return builder.toString();
	}

}
