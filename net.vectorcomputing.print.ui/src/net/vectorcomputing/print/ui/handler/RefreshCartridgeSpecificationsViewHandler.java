package net.vectorcomputing.print.ui.handler;

import net.vectorcomputing.print.ui.viewers.CartridgeSpecificationsView;
import net.vectorcomputing.ui.WorkbenchUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

public class RefreshCartridgeSpecificationsViewHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IViewPart viewPart = WorkbenchUtils.findView(CartridgeSpecificationsView.ID);
		if (viewPart == null) {
			return null;
		}

		if (viewPart instanceof CartridgeSpecificationsView) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					((CartridgeSpecificationsView) viewPart).refresh();
				}
			});
		}

		return null;
	}
}
