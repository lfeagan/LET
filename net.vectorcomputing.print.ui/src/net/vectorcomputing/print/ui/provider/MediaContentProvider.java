package net.vectorcomputing.print.ui.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.Media;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.hibernate.Session;

public class MediaContentProvider implements ITreeContentProvider {

	private static final Object[] EMPTY_ARRAY = Collections.EMPTY_LIST.toArray();

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	/**
	 * Returns an array of ink cartridges. If the input element is an:
	 * <ol>
	 * <li>Ink cartridge specification, returns all ink cartridges that it specifies</li>
	 * <li>
	 * 
	 * </ol>
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Media) {
			Media[] media = new Media[1];
			media[0] = (Media) inputElement;
			return media;
		} else if (inputElement instanceof UUID) {
			// get the media with the specified UUID
			UUID uuid = (UUID) inputElement;
			Session session = PrintPlugin.getSessionFactory().openSession();
			session.beginTransaction();
			
			Object result = session.createQuery("from Media where uuid=:theuuid").setParameter(":theuuid", uuid).uniqueResult();

			session.getTransaction().commit();
			session.close();
			
			if (result instanceof Media) {
				Media[] media = new Media[1];
				media[0] = (Media) result;
				return media;
			} else {
				return EMPTY_ARRAY;
			}
		} else if (inputElement instanceof Collection) {
			return ((Collection) inputElement).toArray();
		} else {
			// get all ink media
			Session session = PrintPlugin.getSessionFactory().openSession();
			session.beginTransaction();
			
			List<Media> result = (List<Media>) session.createQuery("from Media").list();
			for (Media cartridge : result) {
				System.out.println(cartridge);
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
