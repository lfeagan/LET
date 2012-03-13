package net.vectorcomputing.print.ui.widget;

import net.vectorcomputing.print.accounting.InkCartridgeSpecification;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class InkCartridgeSpecificationComposite extends Composite {
	private Text uuidText;
	private Text nameText;
	private Text abbreviationText;
	private Text fillVolumeText;
	private Text makerText;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InkCartridgeSpecificationComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label uuidLabel = new Label(this, SWT.NONE);
		uuidLabel.setText("UUID");
		
		uuidText = new Text(this, SWT.BORDER);
		uuidText.setEditable(false);
		uuidText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label makerLabel = new Label(this, SWT.NONE);
		makerLabel.setText("Maker");
		
		makerText = new Text(this, SWT.BORDER);
		makerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText("Name");
		
		nameText = new Text(this, SWT.BORDER);
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String name = nameText.getText();
				if (name == null) {
					return;
				}
				String abbreviation = InkCartridgeSpecification.generateAbbreviation(name);
				abbreviationText.setText(abbreviation);
			}
		});
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label abbreviationLabel = new Label(this, SWT.NONE);
		abbreviationLabel.setText("Abbreviation");
		
		abbreviationText = new Text(this, SWT.BORDER);
		abbreviationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label fillVolumeLabel = new Label(this, SWT.NONE);
		fillVolumeLabel.setText("Fill Volume (mL)");
		
		fillVolumeText = new Text(this, SWT.BORDER);
		fillVolumeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getMaker() {
		return makerText.getText();
	}
	
	public String getName() {
		return nameText.getText();
	}
	
	public double getFillVolume() {
		String fillVolumeString = fillVolumeText.getText();
		try {
			Double fillVolume = Double.parseDouble(fillVolumeString);
			return fillVolume;
		} catch (Exception e) {
			throw new IllegalArgumentException("Input to fill volume is not parseable as a double");
		}
	}

	public InkCartridgeSpecification build() {
		return new InkCartridgeSpecification(getMaker(), getName(), getFillVolume());
	}
	
}
