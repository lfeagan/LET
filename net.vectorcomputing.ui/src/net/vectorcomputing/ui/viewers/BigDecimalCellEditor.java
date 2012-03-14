package net.vectorcomputing.ui.viewers;

import java.math.BigDecimal;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

public class BigDecimalCellEditor extends TextCellEditor {

	public BigDecimalCellEditor() {
		super();
		setValidator();
	}

	public BigDecimalCellEditor(Composite parent) {
		super(parent);
		setValidator();
	}

	public BigDecimalCellEditor(Composite parent, int style) {
		super(parent, style);
		setValidator();
	}
	
	private final void setValidator() {
		setValidator(new ICellEditorValidator() {
			@Override
			public String isValid(Object value) {
				String string = (String) value;
				try {
					new BigDecimal(string);
					return null;
				} catch (Exception e) {
					return "Input is not parsable as a BigDecimal";
				}
			}
		});
	}

//	@Override
//	protected Object doGetValue() {
//		String value = (String) super.doGetValue();
//		return new BigDecimal(value);
//	}
//
//	@Override
//	protected void doSetValue(Object value) {
//		Assert.isTrue(value instanceof BigDecimal);
//		super.doSetValue(value.toString());
//	}

}
