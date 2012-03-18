package net.vectorcomputing.print.ui.widget;

import java.util.Calendar;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.Cartridge;
import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.ui.dialog.CartridgeSpecificationSelectionDialog;

import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.hibernate.Session;

public class CartridgeComposite extends Composite {
	private final Composite parent;
	private final Text specificationText;
	private final Button specificationSelectButton;
	private final Text uuidText;
	private final Text nameText;
	private final Text remainingVolumeText;
	private final Text priceText;
	private final Button remainingVolumeCustomButton;
	private final CalendarCombo installDateCombo;
	private final CalendarCombo disposeDateCombo;
	
	private Cartridge cartridge = null;
	private CartridgeSpecification specification = null;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CartridgeComposite(Composite parent, int style) {
		super(parent, style);
		this.parent = parent;
		setLayout(new GridLayout(3, false));
		
		Label lblSpecification = new Label(this, SWT.NONE);
		lblSpecification.setText("Specification");
		
		specificationText = new Text(this, SWT.NONE);
		specificationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		specificationText.setEditable(false);
		
		specificationSelectButton = new Button(this, SWT.NONE);
		specificationSelectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CartridgeSpecificationSelectionDialog dialog = new CartridgeSpecificationSelectionDialog(CartridgeComposite.this.parent.getShell());
				int returnCode = dialog.open();
				if (returnCode != SWT.CANCEL) {
					setCartridgeSpecification(dialog.getSelection());
				}
			}
		});
		specificationSelectButton.setText("Select");
		
		Label uuidLabel = new Label(this, SWT.NONE);
		uuidLabel.setText("UUID");
		
		uuidText = new Text(this, SWT.BORDER);
		uuidText.setEditable(false);
		uuidText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		
		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText("Name");
		
		nameText = new Text(this, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		
		Label remainingVolumeLabel = new Label(this, SWT.NONE);
		remainingVolumeLabel.setText("Remaining Volume");
		
		remainingVolumeText = new Text(this, SWT.BORDER);
		remainingVolumeText.setEditable(false);
		remainingVolumeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		remainingVolumeCustomButton = new Button(this, SWT.CHECK);
		remainingVolumeCustomButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				remainingVolumeText.setEditable(remainingVolumeCustomButton.getSelection());
			}
		});
		remainingVolumeCustomButton.setText("Custom");
		
		Label installDateLabel = new Label(this, SWT.NONE);
		installDateLabel.setText("Installed");
		
		installDateCombo = new CalendarCombo(this, SWT.NONE);
		installDateCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(this, SWT.NONE);
		
		Label disposeDateLabel = new Label(this, SWT.NONE);
		disposeDateLabel.setText("Disposed");
		
		disposeDateCombo = new CalendarCombo(this, SWT.NONE);
		disposeDateCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(this, SWT.NONE);
		
		Label priceLabel = new Label(this, SWT.NONE);
		priceLabel.setText("Price");
		
		priceText = new Text(this, SWT.BORDER);
		priceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button priceCurrentButton = new Button(this, SWT.NONE);
		priceCurrentButton.setToolTipText("Looks up the price based on the installation date.");
		priceCurrentButton.setText("Lookup");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setCartridge(Cartridge cartridge) {
		this.cartridge = cartridge;
		fetchSpecification(cartridge);
		updateButtons();
		updateText();
	}

	private void fetchSpecification(Cartridge cartridge) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();

		specification = cartridge.getSpecification();
		
		session.getTransaction().commit();
		session.close();
	}

	private CartridgeSpecification getCartridgeSpecification() {
		return specification;
	}

	public void setCartridgeSpecification(CartridgeSpecification specification) {
		this.specification = specification;
		updateSpecificationText();
	}
			
	public boolean isAlreadyCreated() {
		if (cartridge == null) {
			return false;
		} else {
			return cartridge.getUuid() != null;
		}
	}
	
	private void updateButtons() {
		specificationSelectButton.setEnabled(!isAlreadyCreated());
	}
			
	private void updateText() {
		updateSpecificationText();
		
		if (cartridge.getUuid() != null) {
			uuidText.setText(cartridge.getUuid().toString());
		} else {
			uuidText.setText(""); //$NON-NLS-1$
		}
		
		if (cartridge.getName() != null) {
			nameText.setText(cartridge.getName());
		} else {
			nameText.setText(""); //$NON-NLS-1$
		}
		
		installDateCombo.setDate(cartridge.getInstallDate());
		disposeDateCombo.setDate(cartridge.getDisposeDate());
		
		remainingVolumeText.setText(Double.toString(cartridge.getRemainingVolume()));
	}
	
	private void updateSpecificationText() {
		if (specification != null && specificationText != null && !isDisposed()) {
			specificationText.setText(specification.getMaker() + " " + specification.getModel() + " " + specification.getFillVolume() + " mL");
		}
	}
	
	public String getId() {
		return nameText.getText();
	}
	
	public double getRemainingVolume() {
		String remainingVolumeString = remainingVolumeText.getText();
		if (remainingVolumeString == null) {
			return getCartridgeSpecification().getFillVolume();
		} else {
			return Double.parseDouble(remainingVolumeString);
		}
	}
		
	private Calendar getInstallDate() {
		return installDateCombo.getDate();
	}
	
	public Cartridge getCartridge() {
		if (isAlreadyCreated()) {
			updateCartridge();
			return cartridge;
		} else {
			Cartridge ink = new Cartridge(getId(), getCartridgeSpecification(), getInstallDate());
			ink.setRemainingVolume(getRemainingVolume());
			ink.setDisposeDate(disposeDateCombo.getDate());
			return ink;
		}
	}
	
	private void updateCartridge() {
		cartridge.setInstallDate(installDateCombo.getDate());
		cartridge.setDisposeDate(disposeDateCombo.getDate());
		cartridge.setRemainingVolume(getRemainingVolume());
	}

	
}
