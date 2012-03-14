/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.vectorcomputing.ui.dialog;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Utility class to help with dialogs.
 * <p>
 * Note that a copy of this class exists in the org.eclipse.ui.internal package.
 * </p>
 */
public class DialogUtil {

	/**
	 * Prevent instantiation.
	 */
	private DialogUtil() {
	}

	public static void openError(String title, String message, IStatus status) {
		openError(PlatformUI.getWorkbench(), title, message, status);
	}

	public static void openError(IWorkbench workbench, String title, String message, IStatus status) {
		final Shell parent = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//		final Shell parent = workbench.getModalDialogShellProvider().getShell();
		if (status == null) {
			throw new NullPointerException("Status cannot be null");
		}
		ErrorDialog.openError(parent, title, message, status);
	}
	
	/**
	 * Open an error style dialog for PartInitException by including any extra
	 * information from the nested CoreException if present.
	 */
	public static void openError(Shell parent, String title, String message,
			PartInitException exception) {
		// Check for a nested CoreException
		CoreException nestedException = null;
		IStatus status = exception.getStatus();
		if (status != null && status.getException() instanceof CoreException) {
			nestedException = (CoreException) status.getException();
		}

		if (nestedException != null) {
			// Open an error dialog and include the extra
			// status information from the nested CoreException
			ErrorDialog.openError(parent, title, message,
					nestedException.getStatus());
		} else {
			// Open a regular error dialog since there is no
			// extra information to display. Don't use SWT.SHEET because
			// we don't know if the title contains important information.
			MessageDialog.openError(parent, title, message);
		}
	}

	/**
	 * Return the number of rows available in the current display using the
	 * current font.
	 * 
	 * @param parent
	 *            The Composite whose Font will be queried.
	 * @return int The result of the display size divided by the font size.
	 */
	public static int availableRows(Composite parent) {

		int fontHeight = (parent.getFont().getFontData())[0].getHeight();
		int displayHeight = parent.getDisplay().getClientArea().height;

		return displayHeight / fontHeight;
	}

	/**
	 * Return whether or not the font in the parent is the size of a regular
	 * font. Typically used to know if a font is smaller than the High Contrast
	 * Font. This method is used to make layout decisions based on screen space.
	 * 
	 * @param parent
	 *            The Composite whose Font will be queried.
	 * @return boolean. True if there are more than 50 lines of possible text in
	 *         the display.
	 */
	public static boolean inRegularFontMode(Composite parent) {

		return availableRows(parent) > 50;
	}
}
