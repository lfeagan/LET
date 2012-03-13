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
		
		session.save(new InkCartridgeSpecification("Epson", "Cyan", 350));
		session.save(new InkCartridgeSpecification("Epson", "Magenta", 350));
		session.save(new InkCartridgeSpecification("Epson", "Yellow", 350));
		session.save(new InkCartridgeSpecification("Epson", "Black", 350));
		session.save(new InkCartridgeSpecification("Epson", "Foo", 351));
		
		InkCartridgeSpecification cs = new InkCartridgeSpecification("Epson", "Bar", 352);
		session.save(cs);
		
		InkCartridge ic = new InkCartridge("my id", cs, Calendar.getInstance());
		session.save(ic);
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		List result = session.createQuery("from InkCartridgeSpecification").list();
		for (InkCartridgeSpecification ics : (List<InkCartridgeSpecification>) result) {
			System.out.println(ics.toString());
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
//			InkCartridgeSpecification inkspec = InkCartridgeSpecifications.create("Epson Foo", 350);
//			InkCartridgeSpecification foo = InkCartridgeSpecifications
//					.get(inkspec.getUUID());
//			System.out.println(foo.getName());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

//		Database.stop();
	}

}
