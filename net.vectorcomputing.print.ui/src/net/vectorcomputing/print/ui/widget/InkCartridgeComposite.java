package net.vectorcomputing.print.ui.widget;

import java.util.Calendar;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.InkCartridge;
import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.dialog.InkCartridgeSpecificationSelectionDialog;

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

public class InkCartridgeComposite extends Composite {
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
	
	private InkCartridge cartridge = null;
	private InkCartridgeSpecification specification = null;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InkCartridgeComposite(Composite parent, int style) {
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
				InkCartridgeSpecificationSelectionDialog dialog = new InkCartridgeSpecificationSelectionDialog(InkCartridgeComposite.this.parent.getShell());
				int returnCode = dialog.open();
				if (returnCode != SWT.CANCEL) {
					specification = dialog.getSelection();
					updateSpecificationText();
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
	
	public void setInkCartridge(InkCartridge inkCartridge) {
		this.cartridge = inkCartridge;
		fetchSpecification(inkCartridge);
		updateButtons();
		updateInput();
	}
	
	private void fetchSpecification(InkCartridge inkCartridge) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();

		specification = inkCartridge.getSpecification();
		
		session.getTransaction().commit();
		session.close();
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
		
	private void updateSpecificationText() {
		if (specification != null && specificationText != null && !isDisposed()) {
			specificationText.setText(specification.getMaker() + " " + specification.getModel() + " " + specification.getFillVolume() + " mL");
		}
	}
	
	private void updateInput() {
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getId() {
		return nameText.getText();
	}
	
	public double getRemainingVolume() {
		String remainingVolumeString = remainingVolumeText.getText();
		if (remainingVolumeString == null) {
			return getSpecification().getFillVolume();
		} else {
			return Double.parseDouble(remainingVolumeString);
		}
	}
	
	private InkCartridgeSpecification getSpecification() {
		return specification;
	}

	private Calendar getInstallDate() {
		return installDateCombo.getDate();
	}
	
	public InkCartridge getInkCartridge() {
		if (isAlreadyCreated()) {
			updateCartridge();
			return cartridge;
		} else {
			InkCartridge ink = new InkCartridge(getId(), getSpecification(), getInstallDate());
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
