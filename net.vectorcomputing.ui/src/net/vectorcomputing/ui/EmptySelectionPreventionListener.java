package net.vectorcomputing.ui;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;

public class EmptySelectionPreventionListener implements SelectionListener {
	
	private final List list;
	
	private int previousSelection = 0;
	
	public EmptySelectionPreventionListener(List list) {
		this.list = list;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		selectPreviousOrRemember();
	}
		
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		selectPreviousOrRemember();
	}
	
	private void selectPreviousOrRemember() {
		int selectionIndex = list.getSelectionIndex();
		if (selectionIndex == -1) {
			selectPreviousSelection();
		} else {
			previousSelection = selectionIndex;
		}
	}
		
	private void selectPreviousSelection() {
		if (list.getItemCount() > 0) {
			list.select(previousSelection);
		}
	}
	
}
