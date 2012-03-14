package net.vectorcomputing.print.ui.widget;

import java.util.Collection;
import java.util.Collections;

import net.vectorcomputing.print.accounting.Media;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class MediaTypeCombo extends ComboViewer {

	public MediaTypeCombo(Composite parent) {
		super(parent);
		setContentProvider(new MediaTypeContentProvider());
		setLabelProvider(new MediaTypeLabelProvider());
		setInput(Media.Type.values());
	}

	private static class MediaTypeContentProvider implements IStructuredContentProvider {
		private static final Object[] EMPTY_ARRAY = Collections.EMPTY_LIST.toArray();
		@SuppressWarnings("rawtypes")
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Media.Type) {
				Media.Type[] types = new Media.Type[1];
				types[0] = ((Media.Type) inputElement);
				return types;
			} else if (inputElement instanceof Collection) {
				return ((Collection) inputElement).toArray();
			} else {
				return EMPTY_ARRAY;
			}
		}
		@Override public void dispose() {}
		@Override public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}		
	}
	
	private static class MediaTypeLabelProvider implements ILabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof Media.Type) {
				Media.Type type = (Media.Type) element;
				return type.name();
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