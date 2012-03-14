package net.vectorcomputing.print.ui.provider;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.InkCartridgeSpecification;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.hibernate.Session;

public class InkCartridgeSpecificationsContentProvider implements ITreeContentProvider {

	private static final Object[] EMPTY_ARRAY = Collections.EMPTY_LIST.toArray();

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof UUID) {
			// get the ink cartridge specification with the specified UUID
			UUID uuid = (UUID) inputElement;
			
			return EMPTY_ARRAY;
		} else {
			Session session = PrintPlugin.getSessionFactory().openSession();
			session.beginTransaction();
			
			List<InkCartridgeSpecification> result = (List<InkCartridgeSpecification>) session.createQuery("from InkCartridgeSpecification").list();
			for (InkCartridgeSpecification ics : result) {
				System.out.println(ics);
			}

			session.getTransaction().commit();
			session.close();
			
			return result.toArray();
		}
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}
	
}
