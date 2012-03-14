package net.vectorcomputing.ui.widget;

//import java.io.File;
//
//import org.eclipse.core.resources.IFile;
//import org.eclipse.core.resources.IWorkspaceRoot;
//import org.eclipse.core.resources.ResourcesPlugin;
//import org.eclipse.core.runtime.IPath;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Path;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//
//import net.vectorcomputing.status.ui.AbstractStatusPublishingComposite;
//import net.vectorcomputing.status.ui.ArcsStatusUiPlugin;
//import net.vectorcomputing.ui.dialog.FileSelectionDialog;
//
//public class FileSelectionComposite extends AbstractStatusPublishingComposite {
//
//	private Label fileLabel;
//	private Text fileNameText;
//	private Button browseButton;
//
//	private Shell parentShell;
//	
//	private IFile selection = null;
//	
//	private IPath path;
//	private FileSelectionDialog fileDialog;
//	
//	public static final String KEY_INPUT_LABEL = "INPUT_LABEL";
//	
//	private static final String DEFAULT_FILE_LABEL = "File:";
//	private static final String DEFAULT_DIALOG_TEXT = "Select a file";
//	private static final String DEFAULT_BROWSE_LABEL = "Browse...";
//	private static final String DEFAULT_EXTENSIONS_FILTER[] = { "*.*" }; //$NON-NLS-1$
//	
//	private String fileLabelText = DEFAULT_FILE_LABEL;
//	private String fileDialogText = DEFAULT_DIALOG_TEXT;
//	private String browseButtonText = DEFAULT_BROWSE_LABEL;
//	private String[] fileExtensionFilter = DEFAULT_EXTENSIONS_FILTER;
//	
//	public FileSelectionComposite(Composite parent, int style) {
//		this(parent, style, false);
//	}
//	
//	public FileSelectionComposite(Composite parent, int style, boolean suppressFileLabel) {
//		super(parent, style);
//		parentShell = parent.getShell();
//		
//		if (suppressFileLabel) {
//			setLayout(new GridLayout(2, false));
//		} else {
//			setLayout(new GridLayout(3, false));
//			fileLabel = new Label(this, SWT.NONE);
//			fileLabel.setText(fileLabelText);
//		}
//				
//		fileNameText = new Text(this, SWT.BORDER);
//		fileNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//		browseButton = new Button(this, SWT.NONE);
//		browseButton.setText(browseButtonText);
//		browseButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
//		browseButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent selectionEven) {
//				IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
//				// TODO Is getLocation correct here, consider using getFullPath
//				IPath filterPath = workspaceRoot.getLocation();
//				
//				fileDialog = new FileSelectionDialog(parentShell, SWT.OPEN);
//				fileDialog.setText(fileDialogText);
//				fileDialog.setFilterPath(filterPath.toOSString());
//				
//				fileDialog.setFilterExtensions(fileExtensionFilter);
//				String fileName = fileDialog.open();
//				if (fileName == null) {
//					return;
//				}
//				
//				path = new Path(fileName);
//				selection = workspaceRoot.getFileForLocation(path);
//				fileNameText.setText(path.toOSString());
//				verifyAndPublishStatus();
//			}
//		});		
//	}
//	
//	public void setInputHelpLabel (String newFileLabelText) {
//		fileLabelText = newFileLabelText;
//		if (fileLabel != null) {
//			fileLabel.setText(fileLabelText);
//		}
//	}
//	
//	public void setFileExtensionsFilter (String[] newFileExtensionsFilter) {
//		fileExtensionFilter = newFileExtensionsFilter;
//	}
//	
//	public void setFileExtensionFiter (String newFileExtensionFilter) {
//		fileExtensionFilter = new String[] { newFileExtensionFilter , DEFAULT_EXTENSIONS_FILTER[0] };
//	}
//	
//	public void setFileDialogLabel(String newFileDialogText) {
//		fileDialogText = newFileDialogText;
//	}
//	
//	public void setBrowseButtonLabel(String newBrowseButtonText) {
//		browseButtonText = newBrowseButtonText;
//		browseButton.setText(browseButtonText);
//	}
//
//	@Override
//	public IStatus getStatus() {
//		if (path == null) {
//			return new Status(IStatus.WARNING, ArcsStatusUiPlugin.PLUGIN_ID, "A file must be selected");
//		} else {
//			return Status.OK_STATUS;
//		}
//	}
//	
//	public IPath getIPath() {
//		return path;
//	}
//	
//	public IFile getIFile() {
//		return selection;
//	}
//	
//	public File getFile() {
//		return path.toFile();
//	}
//	
//}
