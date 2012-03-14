package net.vectorcomputing.print.ui.handler;

import net.vectorcomputing.print.ui.viewers.InkCartridgeSpecificationsView;
import net.vectorcomputing.ui.WorkbenchUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

public class RefreshInkCartridgeSpecificationsViewHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IViewPart viewPart = WorkbenchUtils.findView(InkCartridgeSpecificationsView.ID);
		if (viewPart == null) {
			return null;
		}

		if (viewPart instanceof InkCartridgeSpecificationsView) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					((InkCartridgeSpecificationsView) viewPart).refresh();
				}
			});
		}

		return null;
	}
}
