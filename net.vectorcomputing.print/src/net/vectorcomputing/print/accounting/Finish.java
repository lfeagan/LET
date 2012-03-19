package net.vectorcomputing.print.accounting;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.vectorcomputing.print.PrintPlugin;

import org.hibernate.Session;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author lfeagan
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 */
@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(name="Finish")
public class Finish {
	
	private static final String[] BUILTINS = { "Matte", "Semi-Gloss", "Gloss" };
	public static Finish MATTE;
	public static Finish SEMIGLOSS;
	public static Finish GLOSS;

	public static void createBuiltins() {
		MATTE = getFinish(BUILTINS[0]);
		SEMIGLOSS = getFinish(BUILTINS[1]);
		GLOSS = getFinish(BUILTINS[2]);
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable=false)
	private UUID uuid;
	public UUID getUuid() { return uuid; }
	@SuppressWarnings("unused")
	private void setUuid(UUID uuid) { this.uuid = uuid; }

	@Column(name="name", length=128, nullable=false, unique=true)
	private String name;
	public String getName() { return name; }
	@SuppressWarnings("unused")
	private void setName(String name) { this.name = name; }
	
	/**
	 * Default no-arg constructor for use by Hibernate/JPA.
	 */
	public Finish() {}
	
	public Finish(String name) {
		this.name = name;
	}
	
	public static List<Finish> getAll() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Finish> result = (List<Finish>) session.createQuery("from Finish").list();
		session.getTransaction().commit();
		session.close();
		return result;		
	}
	
	public static Finish getFinish(String name) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		Object result =  session.createQuery("from Finish where name = :thename").setParameter("thename", name).uniqueResult();
		Finish mf;
		if (result != null) {
			mf = (Finish) result;
		} else {
			mf = new Finish(name);
			session.save(mf);
		}		
		session.getTransaction().commit();
		session.close();
		return mf;
	}

	public static Finish getFinish(UUID uuid) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		Object result =  session.createQuery("from Finish where uuid = :theuuid").setParameter("theuuid", uuid).uniqueResult();
		Finish mf = null;
		if (result != null) {
			mf = (Finish) result;
		}		
		session.getTransaction().commit();
		session.close();
		return mf;
	}
	
}
