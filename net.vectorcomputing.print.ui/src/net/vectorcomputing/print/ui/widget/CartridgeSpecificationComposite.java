package net.vectorcomputing.print.ui.widget;

import java.text.ParseException;

import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.ui.column.CartridgeColumn;
import net.vectorcomputing.ui.viewers.ElementChangedEvent;
import net.vectorcomputing.ui.viewers.IElementChangedEvent;
import net.vectorcomputing.ui.viewers.IElementChangedListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CartridgeSpecificationComposite extends Composite {
	private Text uuidText;
	private Text modelText;
	private Text abbreviationText;
	private Text fillVolumeText;
	private Text makerText;
	private ListenerList elementChangedlisteners = new ListenerList();
	
	private CartridgeSpecification cartridgeSpecification = null;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CartridgeSpecificationComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label uuidLabel = new Label(this, SWT.NONE);
		uuidLabel.setText("UUID");
				
		uuidText = new Text(this, SWT.BORDER);
		uuidText.setEditable(false);
		uuidText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		uuidText.setEnabled(false);
		
		Label makerLabel = new Label(this, SWT.NONE);
		makerLabel.setText("Maker");
		
		makerText = new Text(this, SWT.BORDER);
		makerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (isReadOnly(style)) {
			makerText.setEditable(false);
		} else {
			makerText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if (cartridgeSpecification != null) {
						cartridgeSpecification.setMaker(makerText.getText());
					}
					elementChanged(new ElementChangedEvent(cartridgeSpecification, CartridgeColumn.Maker.getProperty(), makerText.getText()));
				}
			});
		}
		
		Label modelLabel = new Label(this, SWT.NONE);
		modelLabel.setText("Model");
		
		modelText = new Text(this, SWT.BORDER);
		modelText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (isReadOnly(style)) {
			modelText.setEditable(false);
		} else {
			modelText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					String name = modelText.getText();
					if (cartridgeSpecification != null) {
						cartridgeSpecification.setModel(modelText.getText());
					}
					elementChanged(new ElementChangedEvent(cartridgeSpecification, CartridgeColumn.Model.getProperty(), name));
					if (name == null) {
						return;
					}
					String abbreviation = CartridgeSpecification.generateAbbreviation(name);
					abbreviationText.setText(abbreviation);					
				}
			});
		}
		
		Label abbreviationLabel = new Label(this, SWT.NONE);
		abbreviationLabel.setText("Abbreviation");
		
		abbreviationText = new Text(this, SWT.BORDER);
		abbreviationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (isReadOnly(style)) {
			abbreviationText.setEditable(false);
		} else {
		abbreviationText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if (cartridgeSpecification != null) {
						cartridgeSpecification.setAbbreviation(abbreviationText.getText());
					}
					elementChanged(new ElementChangedEvent(cartridgeSpecification, CartridgeColumn.Abbreviation.getProperty(), abbreviationText.getText()));
				}
			});
		}
		
		Label fillVolumeLabel = new Label(this, SWT.NONE);
		fillVolumeLabel.setText("Fill Volume (mL)");
		
		fillVolumeText = new Text(this, SWT.BORDER);
		fillVolumeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (isReadOnly(style)) {
			fillVolumeText.setEditable(false);
		} else {
			fillVolumeText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if (cartridgeSpecification != null) {
						try {
							double fillVolume = Double.parseDouble(fillVolumeText.getText());
							cartridgeSpecification.setFillVolume(fillVolume);
						} catch (NumberFormatException nfe) {
							nfe.printStackTrace();
						}
					}
					elementChanged(new ElementChangedEvent(cartridgeSpecification, CartridgeColumn.FillVolume.getProperty(), fillVolumeText.getText()));
				}
			});
		}
	}
	
	private static final boolean isReadOnly(int style) {
		return (style & SWT.READ_ONLY) != 0;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setCartridgeSpecification(CartridgeSpecification cartridgeSpecification) {
		this.cartridgeSpecification = cartridgeSpecification;
		updateTexts();
	}
	
	private void updateTexts() {
		uuidText.setText(cartridgeSpecification.getUuid().toString());
		makerText.setText(cartridgeSpecification.getMaker());
		modelText.setText(cartridgeSpecification.getModel());
		fillVolumeText.setText(Double.toString(cartridgeSpecification.getFillVolume()));
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

	public CartridgeSpecification getCartridgeSpecification() {
		if (cartridgeSpecification == null) {
			return new CartridgeSpecification(getMaker(), getModel(), getFillVolume());
		} else {
			updateCartridgeSpecification();
			return cartridgeSpecification;
		}
	}
	
	public void updateCartridgeSpecification() {
		if (cartridgeSpecification != null) {
			cartridgeSpecification.setMaker(getMaker());
			cartridgeSpecification.setModel(getModel());
			cartridgeSpecification.setAbbreviation(getAbbreviation());
			cartridgeSpecification.setFillVolume(getFillVolume());
		}
	}

	public void addElementChangedListener(IElementChangedListener elementChangedListener) {
		elementChangedlisteners.add(elementChangedListener);
	}

	private void elementChanged(IElementChangedEvent event) {
		for (Object listener : elementChangedlisteners.getListeners()) {
			((IElementChangedListener) listener).elementChanged(event);
		}		
	}
	
}
