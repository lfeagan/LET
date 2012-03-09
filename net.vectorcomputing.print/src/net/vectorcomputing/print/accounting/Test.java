package net.vectorcomputing.print.accounting;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;

public class Test {

	@SuppressWarnings({ "unchecked" })
	public void testBasicUsage() {
		// create a couple of events...
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(new InkCartridgeSpecification("Cyan", 350));
		session.save(new InkCartridgeSpecification("Magenta", 350));
		session.save(new InkCartridgeSpecification("Yellow", 350));
		session.save(new InkCartridgeSpecification("Black", 350));
		session.save(new InkCartridgeSpecification("Foo", 351));
		
		InkCartridgeSpecification cs = new InkCartridgeSpecification("Bar", 352);
		session.save(cs);
		
		InkCartridge ic = new InkCartridge("my id", cs);
		session.save(ic);
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = Database.getSessionFactory().openSession();
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
