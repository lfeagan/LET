package net.vectorcomputing.print.ui.widget;

import java.util.Collection;
import java.util.Collections;

import net.vectorcomputing.print.accounting.Media;
import net.vectorcomputing.print.accounting.Finish;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class MediaFinishCombo extends ComboViewer {

	public MediaFinishCombo(Composite parent) {
		super(parent);
		setContentProvider(new MediaFinishContentProvider());
		setLabelProvider(new MediaFinishLabelProvider());
		setInput(Finish.getAll());
	}

	private static class MediaFinishContentProvider implements IStructuredContentProvider {
		private static final Object[] EMPTY_ARRAY = Collections.EMPTY_LIST.toArray();
		@SuppressWarnings("rawtypes")
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Finish) {
				Finish[] finishes = new Finish[1];
				finishes[0] = ((Finish) inputElement);
				return finishes;
			} else if (inputElement instanceof Finish[]) {
				return ((Finish[]) inputElement);
			} else if (inputElement instanceof Collection) {
				return ((Collection) inputElement).toArray();
			} else {
				return EMPTY_ARRAY;
			}
		}
		@Override public void dispose() {}
		@Override public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}		
	}
	
	private static class MediaFinishLabelProvider implements ILabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof Finish) {
				Finish finish = (Finish) element;
				return finish.getName();
			} else {
				return null;
			}
		}
		@Override
		public void addListener(ILabelProviderListener listener) {}
		@Override
		public void dispose() {}
		@Override
		public boolean isLabelProperty(Object element, String property) { return false; }
		@Override
		public void removeListener(ILabelProviderListener listener) {}
		@Override
		public Image getImage(Object element) { return null; }
	}
}
