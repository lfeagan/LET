package net.vectorcomputing.print.ui.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.vectorcomputing.print.PrintPlugin;
import net.vectorcomputing.print.accounting.Cartridge;
import net.vectorcomputing.print.accounting.CartridgeSpecification;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.hibernate.Session;

public class CartridgesContentProvider implements ITreeContentProvider {

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
		if (inputElement instanceof CartridgeSpecification) {
			Session session = PrintPlugin.getSessionFactory().openSession();
			session.beginTransaction();

			
			CartridgeSpecification specification = (CartridgeSpecification) inputElement;
			session.update(specification);
			Set<Cartridge> cartridges = specification.getCartridges();
			for (Cartridge cartridge : cartridges) {
				System.out.println(cartridge.getName());
			}
			
			Object[] result = cartridges.toArray();
			session.getTransaction().commit();
			session.close();
			
			return result;
		} else if (inputElement instanceof UUID) {
			// get the ink cartridge with the specified UUID
			UUID uuid = (UUID) inputElement;
			Session session = PrintPlugin.getSessionFactory().openSession();
			session.beginTransaction();
			
			Object result = session.createQuery("from Cartridge where uuid=:theuuid").setParameter(":theuuid", uuid).uniqueResult();

			session.getTransaction().commit();
			session.close();
			
			if (result instanceof Cartridge) {
				Cartridge[] Cartridges = new Cartridge[1];
				Cartridges[0] = (Cartridge) result;
				return Cartridges;
			} else {
				return EMPTY_ARRAY;
			}
		} else if (inputElement instanceof Collection) {
			return ((Collection) inputElement).toArray();
		} else {
			// get all ink cartridges
			Session session = PrintPlugin.getSessionFactory().openSession();
			session.beginTransaction();
			
			List<Cartridge> result = (List<Cartridge>) session.createQuery("from Cartridge").list();
			for (Cartridge cartridge : result) {
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