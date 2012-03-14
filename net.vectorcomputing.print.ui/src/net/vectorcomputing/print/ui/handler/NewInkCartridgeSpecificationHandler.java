package net.vectorcomputing.print.ui.handler;

import net.vectorcomputing.print.ui.wizard.InkCartridgeSpecificationNewWizard;
import net.vectorcomputing.ui.WizardTools;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWizard;

public class NewInkCartridgeSpecificationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWizard wizard = WizardTools.getWizard(InkCartridgeSpecificationNewWizard.ID);
		WizardTools.openWizard(wizard);
		return null;
	}
	
}
