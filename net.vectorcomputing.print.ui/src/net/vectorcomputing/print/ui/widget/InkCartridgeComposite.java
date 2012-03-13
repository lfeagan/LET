package net.vectorcomputing.print.ui.widget;

import java.util.Calendar;

import net.vectorcomputing.print.accounting.InkCartridge;
import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.dialog.InkCartridgeSpecificationSelectionDialog;

import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class InkCartridgeComposite extends Composite {
	private final Composite parent;
	private final Text uuidText;
	private final Text idText;
	private final Text remainingVolumeText;
	private final Text priceText;
	private final Button remainingVolumeCustomButton;
	private final CalendarCombo installDateCombo;
	private final CalendarCombo disposeDateCombo;
	
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
		
		TableCombo tableCombo = new TableCombo(this, SWT.NONE);
		tableCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button specificationSelectButton = new Button(this, SWT.NONE);
		specificationSelectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InkCartridgeSpecificationSelectionDialog dialog = new InkCartridgeSpecificationSelectionDialog(InkCartridgeComposite.this.parent.getShell());
				int returnCode = dialog.open();
				if (returnCode == SWT.OK) {
					specification = dialog.getSelection();
					System.out.println(specification.getMaker() + "-" + specification.getName());
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
		
		Label idLabel = new Label(this, SWT.NONE);
		idLabel.setText("ID");
		
		idText = new Text(this, SWT.BORDER);
		idText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
	
	public String getId() {
		return idText.getText();
	}
	
	public double getRemainingVolume() {
		String remainingVolumeString = remainingVolumeText.getText();
		if (remainingVolumeString != null) {
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

	public InkCartridge build() {
		InkCartridge ink = new InkCartridge(getId(), getSpecification(), getInstallDate());
		ink.setRemainingVolume(getRemainingVolume());
		return ink;
	}
	
}
