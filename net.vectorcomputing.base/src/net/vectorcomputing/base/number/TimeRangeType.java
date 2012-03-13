package net.vectorcomputing.base.number;

import java.util.Calendar;

public enum TimeRangeType implements RangeType<Integer> {	
	MILLISECOND("millisecond", "ms", Calendar.MILLISECOND, 0, 999),
	SECOND("second", "s", Calendar.SECOND, 0, 59),
	MINUTE("minute", "m", Calendar.MINUTE, 0, 59),
	HOUR_OF_DAY("hour", "h", Calendar.HOUR_OF_DAY, 0, 23),
	DAY_OF_MONTH("day of month", "DoM", Calendar.DAY_OF_MONTH, 1, 31),
	DAY_OF_WEEK("day of week", "DoW", Calendar.DAY_OF_WEEK, 0, 6),
	DAY_OF_YEAR("day of year", "DoY", Calendar.DAY_OF_YEAR, 1, 366),
	MONTH("month", "M", Calendar.MONTH, 0, 11),
	YEAR("year", "Y", Calendar.YEAR, Integer.MIN_VALUE, Integer.MAX_VALUE);
	
	private final String name;
	private final String abbreviation;
	private final int calendarUnit;
	private final int minimum;
	private final int maximum;

	private TimeRangeType(String name, String abbreviation, int calendarUnit, int minimum, int maximum) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.calendarUnit = calendarUnit;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public String getName() {
		return name;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public int getCalendarUnit() {
		return calendarUnit;
	}

	@Override
	public Integer minimum() {
		return minimum;
	}

	@Override
	public Integer maximum() {
		return maximum;
	}
	
	/**
	 * Returns <code>true</code> if the value is greater than or equal to the
	 * minimum and less than or equal to the maximum.
	 * 
	 * @param value
	 *            the integer value to validate
	 * @return <code>true</code> if the value is greater than or equal to the
	 *         minimum and less than or equal to the maximum.
	 */
	@Override
	public boolean isValid(Integer value) {
		return (minimum <= value) && (value <= maximum);
	}

	@Override
	public Range<Integer> newRange(Integer begin, Integer end) {
		return new IntegerRange(this, begin, end);
	}

	public static boolean isValid(Integer value, int calendarUnit) {
		for (TimeRangeType type : TimeRangeType.values()) {
			if (type.getCalendarUnit() == calendarUnit) {
				return type.isValid(value);
			}
		}
		throw new IllegalArgumentException(
				"The calendar unit specified as the interval type is unsupported or is not valid");
	}
	
	@Override
	public void validate(Integer value) throws IllegalArgumentException {
		
	}

}
