package net.vectorcomputing.base.number;

import java.util.Date;

public class DateRange implements Range<Date> {
	
	private final RangeType<Date> rangeType;
	private Date begin;
	private Date end;

	public DateRange(final Date begin, final Date end) {
		this.rangeType = DateRangeType.UNLIMITED;
		this.begin = begin;
		this.end = end;
	}
	
	public DateRange(final RangeType<Date> rangeType, final Date begin, final Date end) {
		this.rangeType = rangeType;
		this.begin = begin;
		this.end = end;
	}
	
	@Override
	public RangeType<Date> getRangeType() {
		return rangeType;
	}
	
	@Override
	public Date getBegin() {
		return begin;
	}
	
	@Override
	public void setBegin(Date begin) {
		this.rangeType.validate(begin);
		this.begin = begin;
	}
	
	@Override
	public Date getEnd() {
		return end;
	}

	@Override
	public void setEnd(Date end) {
		this.rangeType.validate(end);
		this.end = end;
	}
	
	@Override
	public int compareToBegin(Date when) {
		if (begin != null) {
			return begin.compareTo(when);
		} else {
			if (when.getTime() == Long.MIN_VALUE) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	@Override
	public int compareToEnd(Date when) {
		if (end != null) {
			return end.compareTo(when);
		} else {
			if (when.getTime() == Long.MAX_VALUE) {
				return 0;
			} else {
				return -1;
			}			
		}
	}

	@Override
	public boolean includes(final Date when) {
		return (compareToBegin(when) >= 0 && compareToEnd(when) <= 0);
//		return !(begin.after(when) || end.before(when));
	}

	@Override
	public boolean isAdjacentToBegin(final Date when) {
		if (begin != null) {
			return (when.getTime() + 1 == begin.getTime());
		} else {
			return false;
		}
	}

	@Override
	public boolean isAdjacentToEnd(final Date when) {
		if (end != null) {
			return (when.getTime() - 1 == end.getTime());
		} else {
			return false;
		}
	}

}
