package net.vectorcomputing.print.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.hibernate.SessionFactory;

public class HibernateEditorInput implements IEditorInput {

	private final SessionFactory sessionFactory;
	private final Object object;
	
	public HibernateEditorInput(SessionFactory sessionFactory, Object object) {
		this.sessionFactory = sessionFactory;
		this.object = object;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Object getObject() {
		return object;
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "foo";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "A tool tip";
	}
	
	

}
