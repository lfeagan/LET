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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.vectorcomputing.print.PrintPlugin;

import org.hibernate.Session;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * Specification of a printer and the ink specifications it can use.
 * 
 * @author lfeagan
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "PrinterSpecification", uniqueConstraints=@UniqueConstraint(columnNames={"maker", "model"}))
public class PrinterSpecification {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable=false)
	private UUID uuid;
	public UUID getUuid() { return uuid; }
	@SuppressWarnings("unused")
	private void setUuid(UUID uuid) { this.uuid = uuid; }

	@Column(name="maker", length=128, nullable=false, unique=false)
	private String maker;
	public String getMaker() { return maker; }
	public void setMaker(String maker) { this.maker = maker; }	
	
	@Column(name="model", length=128, nullable=false, unique=false)
	private String model;
	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cartridgeSpecification_fk")
	private final Set<CartridgeSpecification> cartridgeSpecifications = new HashSet<CartridgeSpecification>();
	public Set<CartridgeSpecification> getCartridgeSpecifications() {
		return cartridgeSpecifications;
	}
	void addCartridgeSpecification(CartridgeSpecification cartridgeSpecification) {
		cartridgeSpecifications.add(cartridgeSpecification);
	}
	void setCartridgeSpecifications(Set<CartridgeSpecification> cartridgeSpecifications) {
		cartridgeSpecifications.addAll(cartridgeSpecifications);
	}
		
	public PrinterSpecification(String maker, String model, Collection<? extends CartridgeSpecification> cartridgeSpecifications) {
		this.maker = maker;
		this.model = model;
		this.cartridgeSpecifications.addAll(cartridgeSpecifications);
	}
	
	public static PrinterSpecification get(UUID uuid) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		PrinterSpecification result = (PrinterSpecification) session.createQuery("from PrinterSpecification where uuid = :theuuid").setParameter("theuuid", uuid).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public static final List<PrinterSpecification> getAll() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PrinterSpecification> result = (List<PrinterSpecification>) session.createQuery("from PrinterSpecification").list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
}
