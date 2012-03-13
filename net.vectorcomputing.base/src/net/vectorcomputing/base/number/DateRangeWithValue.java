package net.vectorcomputing.base.number;

import java.util.Date;

public class DateRangeWithValue<V> extends DateRange implements RangeWithValue<Date,V> {

	private V value;
	
	public DateRangeWithValue(Date begin, Date end) {
		super(begin, end);
	}

	public DateRangeWithValue(Date begin, Date end, V value) {
		super(begin, end);
		this.value = value;
	}
	
	public DateRangeWithValue(RangeType<Date> rangeType, Date begin, Date end) {
		super(rangeType, begin, end);
	}
	
	public DateRangeWithValue(RangeType<Date> rangeType, Date begin, Date end, V value) {
		super(rangeType, begin, end);
		this.value = value;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	@Override
	public void setValue(V value) {
		this.value = value;
	}
	
}
