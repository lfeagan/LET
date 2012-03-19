package net.vectorcomputing.print.accounting;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.vectorcomputing.print.PrintPlugin;

import org.hibernate.Session;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 
 * @author lfeagan
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="Printer")
public class Printer {
	
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

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cartridge_fk")
	private final Set<Cartridge> cartridges = new HashSet<Cartridge>();
	public Set<Cartridge> getCartridges() {
		return cartridges;
	}
	void addCartridge(Cartridge cartridge) {
		cartridges.add(cartridge);
	}
	void setCartridges(Set<Cartridge> cartridges) {
		cartridges.addAll(cartridges);
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="specification_fk", updatable=false)
	private PrinterSpecification specification;
	public PrinterSpecification getSpecification() {
		return specification;
	}
	void setSpecification(PrinterSpecification specification) {
		this.specification = specification;
	}
	
	public Printer(PrinterSpecification specification, String name, Collection<? extends Cartridge> loadedCartridges) {
		this.specification = specification;
		this.name = name;
		this.cartridges.addAll(loadedCartridges);
	}

	public static Printer get(UUID uuid) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		Printer result = (Printer) session.createQuery("from Printer where uuid = :theuuid").setParameter("theuuid", uuid).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public static final List<Printer> getAll() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Printer> result = (List<Printer>) session.createQuery("from Printer").list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
}
