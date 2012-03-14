package net.vectorcomputing.print.ui.dialog;

import net.vectorcomputing.print.accounting.InkCartridgeSpecification;
import net.vectorcomputing.print.ui.widget.InkCartridgeSpecificationsComposite;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class InkCartridgeSpecificationSelectionDialog extends Dialog {

	private InkCartridgeSpecification specification = null;
	private InkCartridgeSpecificationsComposite specs;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public InkCartridgeSpecificationSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout());
		specs = new InkCartridgeSpecificationsComposite(container, SWT.SINGLE);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	@Override
	protected void okPressed() {
		specification = specs.getSelection();
		super.okPressed();
	}
	
	public InkCartridgeSpecification getSelection() {
		return specification;
	}

}
