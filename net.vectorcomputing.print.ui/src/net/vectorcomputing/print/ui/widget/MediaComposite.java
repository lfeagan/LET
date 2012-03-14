package net.vectorcomputing.print.ui.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class MediaComposite extends Composite {
	private Text uuidText;
	private Text makerText;
	private Text modelText;
	private MediaTypeCombo typeCombo;
	private MediaFinishCombo finishCombo;
	private Text widthText;
	private Text lengthText;
	private Text quantityText;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MediaComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label lblUuid = new Label(this, SWT.NONE);
		lblUuid.setText("UUID");
		
		uuidText = new Text(this, SWT.BORDER);
		uuidText.setEditable(false);
		uuidText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label makerLabel = new Label(this, SWT.NONE);
		makerLabel.setText("Maker");
		
		makerText = new Text(this, SWT.BORDER);
		makerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label modelLabel = new Label(this, SWT.NONE);
		modelLabel.setText("Model");
		
		modelText = new Text(this, SWT.BORDER);
		modelText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label typeLabel = new Label(this, SWT.NONE);
		typeLabel.setText("Type");
		typeCombo = new MediaTypeCombo(this);
		Combo combo = typeCombo.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label finishLabel = new Label(this, SWT.NONE);
		finishLabel.setText("Finish");
		finishCombo = new MediaFinishCombo(this);
		Combo combo_1 = finishCombo.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label widthLabel = new Label(this, SWT.NONE);
		widthLabel.setText("Width");
		
		widthText = new Text(this, SWT.BORDER);
		widthText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lengthLabel = new Label(this, SWT.NONE);
		lengthLabel.setText("Length");
		
		lengthText = new Text(this, SWT.BORDER);
		lengthText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label quantityLabel = new Label(this, SWT.NONE);
		quantityLabel.setText("Quantity");
		
		quantityText = new Text(this, SWT.BORDER);
		quantityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
