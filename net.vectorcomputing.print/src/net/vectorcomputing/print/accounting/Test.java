package net.vectorcomputing.print.accounting;

import java.util.Calendar;
import java.util.List;

import net.vectorcomputing.print.PrintPlugin;

import org.hibernate.Session;

public class Test {

	@SuppressWarnings({ "unchecked" })
	public void testBasicUsage() {
		// create a couple of events...
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(new CartridgeSpecification("Epson", "Cyan", 350));
		session.save(new CartridgeSpecification("Epson", "Magenta", 350));
		session.save(new CartridgeSpecification("Epson", "Yellow", 350));
		session.save(new CartridgeSpecification("Epson", "Black", 350));
		session.save(new CartridgeSpecification("Epson", "Foo", 351));
		
		CartridgeSpecification cs = new CartridgeSpecification("Epson", "Bar", 352);
		session.save(cs);
		
		Cartridge ic = new Cartridge("my id", cs, Calendar.getInstance());
		session.save(ic);
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		List result = session.createQuery("from CartridgeSpecification").list();
		for (CartridgeSpecification ics : (List<CartridgeSpecification>) result) {
			System.out.println(ics.toString());
			
			ics.getCartridges();
		}
		
		session.getTransaction().commit();
		session.close();
	}


	public static void main(String[] args) {
//		Database.start();
		
		Test test = new Test();
		test.testBasicUsage();
		
		// System.out.println(newUUID());
//		try {
//			CartridgeSpecification inkspec = CartridgeSpecifications.create("Epson Foo", 350);
//			CartridgeSpecification foo = CartridgeSpecifications
//					.get(inkspec.getUUID());
//			System.out.println(foo.getName());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

//		Database.stop();
	}

}
