package net.vectorcomputing.base.number;

import java.util.Calendar;
import java.util.Date;

public class DateRangeType implements RangeType<Date> {

	public static final Date ABSOLUTE_MINIMUM;
	public static final Date ABSOLUTE_MAXIMUM;
	public static final RangeType<Date> UNLIMITED;
	
	static {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.MIN_VALUE);
		ABSOLUTE_MINIMUM = cal.getTime();
		cal.setTimeInMillis(Long.MAX_VALUE);
		ABSOLUTE_MAXIMUM = cal.getTime();
		UNLIMITED = new DateRangeType(ABSOLUTE_MINIMUM, ABSOLUTE_MAXIMUM);
	}
	
	private final Date minimum;
	private final Date maximum;
	private final String name;
	
	public DateRangeType(Date minimum, Date maximum) {
		this(minimum, maximum, IntegerRangeType.class.getSimpleName());
	}
	
	public DateRangeType(Date minimum, Date maximum, String name) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.name = name;
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public Date minimum() {
		return minimum;
	}

	@Override
	public Date maximum() {
		return maximum;
	}

	@Override
	public boolean isValid(Date when) {
		return !(minimum.after(when) || maximum.before(when));
	}
	
	@Override
	public void validate(Date when) throws IllegalArgumentException {
		if (minimum.after(when)) {
			throw new IllegalArgumentException("value is less than minimum");
		} else if (maximum.before(when)) {
			throw new IllegalArgumentException("value is greater than maximum");
		}
	}

	@Override
	public Range<Date> newRange(Date begin, Date end) {
		return new DateRange(begin,end);
	}

}
