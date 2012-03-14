package net.vectorcomputing.ui.dialog;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class FileSelectionDialog extends FileDialog {

	private static final String DEFAULT_DIALOG_TEXT = "Select a file";
	private static final String DEFAULT_EXTENSIONS_FILTER[] = { "*.*" }; //$NON-NLS-1$
	
	private String fileDialogText = DEFAULT_DIALOG_TEXT;
	
	private final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
	
	private String fullPath = null;
	
	public FileSelectionDialog(Shell parentShell) {
		this(parentShell, SWT.OPEN);
	}
	
	public FileSelectionDialog(Shell parentShell, int style) {
		super(parentShell, style);
		setFilterExtensions(DEFAULT_EXTENSIONS_FILTER);
		setWorkspaceRootFilterPath();
		setText(fileDialogText);
	}
	
	private void setWorkspaceRootFilterPath() {
		// TODO Is getLocation correct here, consider using getFullPath
		IPath filterPath = workspaceRoot.getLocation();
		setFilterPath(filterPath.toOSString());
	}
	
	@Override
	public String open() {
		fullPath = super.open();
		return fullPath;
	}
	
	public String getFullPath() {
		return fullPath;
	}
	
	public IPath getIPath() {
		if (fullPath != null) {
			IPath path = new Path(fullPath);
			return path;
		} else {
			return null;
		}
	}
	
	public IFile getIFile() {
		IPath ipath = getIPath();
		if (ipath != null) {
			return workspaceRoot.getFileForLocation(ipath);
		} else {
			return null;
		}
	}
	
	public void setFileExtensionFiter (String newFileExtensionFilter) {
		super.setFilterExtensions(new String[] { newFileExtensionFilter , "*.*" }); //$NON-NLS-1$
	}
	
	public void setFileDialogLabel(String newFileDialogText) {
		fileDialogText = newFileDialogText;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
