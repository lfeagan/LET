package net.vectorcomputing.base.number;

public class IntegerRangeType implements RangeType<Integer> {

	public static final RangeType<Integer> UNLIMITED = new IntegerRangeType(Integer.MIN_VALUE, Integer.MAX_VALUE);
	
	private final Integer minimum;
	private final Integer maximum;
	private final String name;
	
	public IntegerRangeType(Integer minimum, Integer maximum) {
		this(minimum, maximum, IntegerRangeType.class.getSimpleName());
	}
	
	public IntegerRangeType(Integer minimum, Integer maximum, String name) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.name = name;
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public Integer minimum() {
		return minimum;
	}

	@Override
	public Integer maximum() {
		return maximum;
	}

	@Override
	public boolean isValid(Integer value) {
		return (minimum <= value) && (value <= maximum);
	}
	
	@Override
	public void validate(Integer value) throws IllegalArgumentException {
		if (value < minimum) {
			throw new IllegalArgumentException("value is less than minimum");
		} else if (value > maximum) {
			throw new IllegalArgumentException("value is greater than maximum");
		}
	}

	@Override
	public Range<Integer> newRange(Integer begin, Integer end) {
		return new IntegerRange(begin,end);
	}

}
