package net.vectorcomputing.ui.viewers;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

public class CalendarCellEditor extends TextCellEditor {

	private final SimpleDateFormat dateFormat;
	
	public CalendarCellEditor(SimpleDateFormat dateFormat) {
		super();
		this.dateFormat = dateFormat;
//		setValidator();
	}

	public CalendarCellEditor(Composite parent, SimpleDateFormat dateFormat) {
		super(parent);
		this.dateFormat = dateFormat;
//		setValidator();
	}

	public CalendarCellEditor(Composite parent, int style, SimpleDateFormat dateFormat) {
		super(parent, style);
		this.dateFormat = dateFormat;
//		setValidator();
	}
	
	private final void setValidator() {
		setValidator(new ICellEditorValidator() {
			@Override
			public String isValid(Object value) {
				String string = (String) value;
				try {
					dateFormat.parse(string);
					return null;
				} catch (Exception e) {
					return "Date is not parsable by pattern " + dateFormat.toPattern();
				}
			}
		});
	}
	
//	@Override
//	protected Object doGetValue() {
//		String string = (String) super.doGetValue();
//		Date date;
//		try {
//			date = dateFormat.parse(string);
//			GregorianCalendar cal = new GregorianCalendar();
//			cal.setTime(date);
//			return cal;
//		} catch (ParseException e) {
//			return null;
////			throw new IllegalArgumentException("Date is not properly formatted");
//		}
//	}
//
//	@Override
//	protected void doSetValue(Object value) {
//		Assert.isTrue(value instanceof Calendar);
//		Calendar cal = (Calendar) value;
//		super.doSetValue(dateFormat.format(cal.getTime()));
//	}
	
}
