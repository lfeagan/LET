package net.vectorcomputing.print.ui.column;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import net.vectorcomputing.print.accounting.Price;
import net.vectorcomputing.print.preferences.PrintPreferences;
import net.vectorcomputing.ui.ColumnSpecification;
import net.vectorcomputing.ui.viewers.AbstractColumnValueIO;
import net.vectorcomputing.ui.viewers.BigDecimalCellEditor;
import net.vectorcomputing.ui.viewers.CalendarCellEditor;
import net.vectorcomputing.ui.viewers.ColumnViewerColumn;
import net.vectorcomputing.ui.viewers.ColumnViewerColumnFactory;
import net.vectorcomputing.ui.viewers.ElementChangedEvent;
import net.vectorcomputing.ui.viewers.IColumnViewerColumn;

public enum PriceHistoryColumn {
	Date("Date",
			new DateColumnFactory(),
			new Comparator<Price>() {
				@Override
				public int compare(Price price1, Price price2) {
					return price1.getDate().compareTo(price2.getDate());
				}		
		}),
	Price("Price",
			new PriceColumnFactory(),
			new Comparator<Price>() {
				@Override
				public int compare(Price price1, Price price2) {
					return price1.getPrice().compareTo(price2.getPrice());
				}
		});
	
	private final String property;
	private final ColumnViewerColumnFactory factory;
	private final Comparator<Price> comparator;
	private PriceHistoryColumn(String property, ColumnViewerColumnFactory factory, Comparator<Price> comparator) {
		this.property = property;
		this.factory = factory;
		this.comparator = comparator;
	}
	
	public String getProperty() {
		return property;
	}
	
	public IColumnViewerColumn newColumnViewerColumn(int index) {
		return factory.build(index);
	}
		
	private static class DateColumnFactory implements ColumnViewerColumnFactory {
		private static class DateColumn extends ColumnViewerColumn {
			private DateColumn(int index) {
				super(index,
						new ColumnSpecification(PriceHistoryColumn.Date.getProperty()),
						new CalendarCellEditor(new SimpleDateFormat("yyyy-MM-dd")),
						new AbstractColumnValueIO() {
							@Override
							public Object getValue(Object element) {
								Price price = (Price) element;
								Calendar date = price.getDate();
								if (date != null) {
									try {
										String formattedDate = PrintPreferences.getDateFormat().format(date.getTime());
										return formattedDate;
									} catch (Exception e) {
										e.printStackTrace();
										return "";
									}
								} else {
									return ""; //$NON-NLS-1$
								}
							}

							@Override
							public void modify(Object element, Object value) {
								Price price = (Price) element;
								try {
									String input = (String) value;
									Date date = PrintPreferences.getDateFormat().parse(input);
									GregorianCalendar calendar = new GregorianCalendar();
									calendar.setTime(date);
									price.setDate(calendar);
									elementChanged(new ElementChangedEvent(price, PriceHistoryColumn.Date.getProperty(), calendar));
								} catch (ParseException e) {
									// do nothing
								}
							}
						});
			}
		}
		
		@Override
		public IColumnViewerColumn build(int index) {
			return new DateColumn(index);
		}
	}

	private static class PriceColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(PriceHistoryColumn.Price.getProperty()),
					new BigDecimalCellEditor(),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Price price = (Price) element;
							return price.getPrice().toString();
						}
		
						@Override
						public void modify(Object element, Object value) {
							Price price = (Price) element;			
							String input = (String) value;
							BigDecimal oldPrice = price.getPrice();
							BigDecimal newPrice = new BigDecimal(input);
							price.setPrice(newPrice);
							elementChanged(new ElementChangedEvent(price, PriceHistoryColumn.Price.getProperty(), oldPrice, newPrice));
						}
					});
		}
	}

	public Comparator<Price> getComparator() {
		return comparator;
	}

}
