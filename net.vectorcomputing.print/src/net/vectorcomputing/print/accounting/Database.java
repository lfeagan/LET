package net.vectorcomputing.print.accounting;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {

	private static SessionFactory sessionFactory = null;
	
	// A SessionFactory is set up once for an application
	private static void initialize() {
		synchronized(Database.class) {
			if (sessionFactory == null) {
				File file = new File("/Users/lfeagan/Workspaces/Eclipse/3.7.0/workspace/net.vectorcomputing.print/hibernate.cfg.xml");
				sessionFactory = new Configuration().configure(file).buildSessionFactory();
			}
		}
	}

	protected static void close() throws Exception {
		synchronized(Database.class) {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

	public static SessionFactory getSessionFactory() {
		initialize();
		return sessionFactory;
	}

}
