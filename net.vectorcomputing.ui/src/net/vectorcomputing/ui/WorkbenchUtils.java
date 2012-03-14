package net.vectorcomputing.ui;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class WorkbenchUtils {

	/**
	 * Searches all windows and pages in the workbench for a view part with the
	 * specified id.
	 * 
	 * @param viewId
	 *            the view part id to find
	 * @return the view part with the specified id or <code>null</code> if no
	 *         matching view part can be found
	 * @see IWorkbenchPage#findView(String)
	 */
	public static IViewPart findView(String viewId) {
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page: window.getPages()) {
				IViewPart viewPart = page.findView(viewId);
				if (viewPart != null) {
					return viewPart;
				}
			}
		}
		return null;
	}

	/**
	 * Searches all windows and pages in the workbench for an editor part that
	 * is editing the specified editor input.
	 * 
	 * @param input the editor input to find
	 * @return the editor part associated with the specified editor input or
	 *         <code>null</code> if no editor can be found
	 * @see IWorkbenchPage#findEditor(IEditorInput)
	 */
	public static IEditorPart findEditor(IEditorInput input) {
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page: window.getPages()) {
				IEditorPart editorPart = page.findEditor(input);
				if (editorPart != null) {
					return editorPart;
				}
			}
		}
		return null;
	}

	/**
	 * Searches all windows and pages in the workbench for editor references.
	 * 
	 * @param input
	 * @param editorId
	 * @param matchFlags
	 * @return
	 * @see IWorkbenchPage#findEditors(IEditorInput, String, int)
	 */
	public static IEditorReference[] findEditors(IEditorInput input, String editorId, int matchFlags) {
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page: window.getPages()) {
				IEditorReference[] editors = page.findEditors(input, editorId, matchFlags);
				if (editors != null) {
					return editors;
				}	
			}
		}
		return new IEditorReference[0];
	}

}
