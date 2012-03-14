package net.vectorcomputing.print.ui.widget;

import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.editor.IDirty;

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
	private Text modelText;
	private Text abbreviationText;
	private Text fillVolumeText;
	private Text makerText;
	private IDirty dirty = null;
	
	private InkCartridgeSpecification inkCartridgeSpecification = null;

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
		makerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				markDirty();
			}
		});
		makerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label modelLabel = new Label(this, SWT.NONE);
		modelLabel.setText("Model");
		
		modelText = new Text(this, SWT.BORDER);
		modelText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String name = modelText.getText();
				if (name == null) {
					return;
				}
				String abbreviation = InkCartridgeSpecification.generateAbbreviation(name);
				abbreviationText.setText(abbreviation);
				markDirty();
			}
		});
		modelText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label abbreviationLabel = new Label(this, SWT.NONE);
		abbreviationLabel.setText("Abbreviation");
		
		abbreviationText = new Text(this, SWT.BORDER);
		abbreviationText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				markDirty();
			}
		});
		abbreviationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label fillVolumeLabel = new Label(this, SWT.NONE);
		fillVolumeLabel.setText("Fill Volume (mL)");
		
		fillVolumeText = new Text(this, SWT.BORDER);
		fillVolumeText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				markDirty();
			}
		});
		fillVolumeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setInkCartridgeSpecification(InkCartridgeSpecification inkCartridgeSpecification) {
		this.inkCartridgeSpecification = inkCartridgeSpecification;
		updateTexts();
	}
	
	private void updateTexts() {
		uuidText.setText(inkCartridgeSpecification.getUuid().toString());
		makerText.setText(inkCartridgeSpecification.getMaker());
		modelText.setText(inkCartridgeSpecification.getModel());
		fillVolumeText.setText(Double.toString(inkCartridgeSpecification.getFillVolume()));
	}
	
	public String getMaker() {
		return makerText.getText();
	}
	
	public String getModel() {
		return modelText.getText();
	}
	
	public String getAbbreviation() {
		return abbreviationText.getText();
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

	public InkCartridgeSpecification getInkCartridgeSpecification() {
		if (inkCartridgeSpecification == null) {
			return new InkCartridgeSpecification(getMaker(), getModel(), getFillVolume());
		} else {
			updateInkCartridgeSpecification();
			return inkCartridgeSpecification;
		}
	}
	
	public void updateInkCartridgeSpecification() {
		if (inkCartridgeSpecification != null) {
			inkCartridgeSpecification.setMaker(getMaker());
			inkCartridgeSpecification.setModel(getModel());
			inkCartridgeSpecification.setAbbreviation(getAbbreviation());
			inkCartridgeSpecification.setFillVolume(getFillVolume());
		}
	}

	public void setDirty(IDirty dirty) {
		this.dirty = dirty;
	}

	private void markDirty() {
		if (dirty != null) {
			dirty.setIsDirty(true);
		}
	}
	
}
