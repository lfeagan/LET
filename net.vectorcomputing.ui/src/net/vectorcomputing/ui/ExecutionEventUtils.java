package net.vectorcomputing.ui;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

public final class ExecutionEventUtils {

	public static IProgressMonitor getProgressMonitor(ExecutionEvent event) {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		if (activePart instanceof IViewPart) {
			return ((IViewPart) activePart).getViewSite().getActionBars().getStatusLineManager().getProgressMonitor();
		} else if (activePart instanceof IEditorPart) {
			return ((IEditorPart) activePart).getEditorSite().getActionBars().getStatusLineManager().getProgressMonitor();
		} else {
			return new NullProgressMonitor();
		}
	}
	
	public static IUndoContext getUndoContext(ExecutionEvent event) {
		IWorkbenchPart activeEditor = HandlerUtil.getActivePart(event);
		if (activeEditor instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) activeEditor;
			Object adapted = adaptable.getAdapter(IUndoContext.class);
			if (adapted instanceof IUndoContext) {
				return (IUndoContext) adapted;
			}
		}
		return null;
	}
	
	public static IViewPart getActiveViewPart(ExecutionEvent event) {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		if (activePart instanceof IViewPart) {
			IViewPart viewPart = (IViewPart) activePart;
			return viewPart;
		}
		return null;
	}
	
}
