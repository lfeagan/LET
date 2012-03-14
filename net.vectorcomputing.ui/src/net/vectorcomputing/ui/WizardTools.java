package net.vectorcomputing.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

public class WizardTools {
	
	public static IViewReference findViewReferenceById(String id) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		final IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
		for (IWorkbenchWindow window : windows) {
			final IWorkbenchPage[] pages = window.getPages();
			for (IWorkbenchPage page : pages) {
				final IViewReference[] viewRefs = page.getViewReferences();
				for (IViewReference viewRef : viewRefs) {
					System.out.println(toString(window, page, viewRef));
					if (viewRef.getId().equals(id)) {
						return viewRef;
					}
				}
			}
		}
		return null;
	}
	
	private static final String toString(IWorkbenchWindow window, IWorkbenchPage page, IViewReference viewRef) {
		final StringBuilder builder = new StringBuilder();
		builder.append("[window="); //$NON-NLS-1$
		builder.append(window);
		builder.append(", page="); //$NON-NLS-1$
		builder.append(page.getLabel());
		builder.append(", viewRef="); //$NON-NLS-1$
		builder.append(viewRef.getId());
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}
	
	/**
	 * 
	 * @param wizard ID (from extenstion point)
	 * @return IWorkbenchWizard wizard
	 */
	public static IWorkbenchWizard getWizard(String id){
		IWorkbenchWizard newWizard = null;
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWizardRegistry wizardRegistry = workbench.getNewWizardRegistry();
		IWizardDescriptor newWizardDescriptor = wizardRegistry.findWizard(id);
		try{
			newWizard = newWizardDescriptor.createWizard();
		}catch (CoreException e){
			//TODO logging
			e.printStackTrace();
		}
		
		return newWizard;
	}
	
	public static void openWizard(IWorkbenchWizard wizard, Shell shell) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IStructuredSelection selection = (IStructuredSelection) workbench
				.getActiveWorkbenchWindow().getSelectionService()
				.getSelection();
		wizard.init(workbench, selection);
		WizardDialog newDialog = new WizardDialog(shell, wizard);
		newDialog.create();
		newDialog.open();
	}
	
	public static void openWizard(IWorkbenchWizard wizard) {
		openWizard(wizard, getActiveShell());
	}
	
	public static Shell getActiveShell() {
		 final IWorkbench workbench = PlatformUI.getWorkbench();
		 if (workbench == null) {
			 return null;
		 }
		 
		 final IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
		 if (activeWindow == null) {
			 return null;
		 }
		 
		 return activeWindow.getShell();
	}
	
}
