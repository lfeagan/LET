package net.vectorcomputing.print.internal;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ORM {

	private static final String TEST_pathToCfg = "/Users/lfeagan/Workspaces/Eclipse/3.7.0/workspace/net.vectorcomputing.print/hibernate.cfg.xml";
	
	private static SessionFactory sessionFactory = null;
	
	// A SessionFactory is set up once for an application
	public static synchronized void start(String pathToCfg) {
		if (sessionFactory == null) {
			File file = new File(pathToCfg);
			sessionFactory = new Configuration().configure(file).buildSessionFactory();
		}
	}

	public static synchronized void stop() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}

	public static synchronized SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
