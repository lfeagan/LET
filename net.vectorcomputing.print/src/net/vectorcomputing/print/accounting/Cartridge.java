package net.vectorcomputing.print.accounting;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * A cartridge represents an instance of a {@link CartridgeSpecification}. In addition to a reference to the parent specification, it has fields for:
 * <ul>
 * <li>name = a user-specified name to identify the cartridge, in combination with the specification this must be unique</li>
 * <li>remaining volume = the amount of ink left in the cartridge</li>
 * <li>install date = the date the cartridge was put into service, used to determine the price</li>
 * <li>disposal date = the date the cartridge was taken out of service</li>
 * </ul>
 * 
 * @author lfeagan
 * @since 1.0
 * 
 * @noextend This interface is not intended to be extended by clients.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="Cartridge", uniqueConstraints=@UniqueConstraint(columnNames={"specification_fk", "name"}))
public class Cartridge {
	
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
	
	@Column(name="remainingVolume", nullable=false, unique=false)
	private double remainingVolume;
	public double getRemainingVolume() {
		return remainingVolume;
	}
	public void setRemainingVolume(double remainingVolume) {
		if (remainingVolume > specification.getFillVolume()) {
			throw new IllegalArgumentException("Remaining volume cannot be set higher than the fill volume");
		}
		
		this.remainingVolume = remainingVolume;
	}

	@Column(name="installDate", nullable=false)
	private Calendar installDate;
	public Calendar getInstallDate() { return installDate; }
	public void setInstallDate(Calendar installDate) { this.installDate = installDate; }

	@Column(name="disposalDate", nullable=true)
	private Calendar disposalDate;
	public Calendar getDisposeDate() { return disposalDate; }
	public void setDisposeDate(Calendar disposalDate) { this.disposalDate = disposalDate; }
	public boolean isDisposed() { return disposalDate != null; }

	@ManyToOne(optional=false)
	@JoinColumn(name="specification_fk", updatable=false)
	private CartridgeSpecification specification;
	public CartridgeSpecification getSpecification() {
		return specification;
	}
	void setSpecification(CartridgeSpecification specification) {
		this.specification = specification;
	}
	
	/**
	 * Constructor only for Hibernate's use.
	 */
	public Cartridge() {
		
	}
	
	public Cartridge(String name, CartridgeSpecification specification, Calendar installDate) {
		this.name = name;
		this.specification = specification;
		this.installDate = installDate;
		this.remainingVolume = specification.getFillVolume();
	}
	
	public String getMaker() {
		return getSpecification().getMaker();
	}
	
	public String getModel() {
		return getSpecification().getModel();
	}

	public String getAbbreviation() {
		return getSpecification().getAbbreviation();
	}

	public double getFillVolume() {
		return getSpecification().getFillVolume();
	}
	
	public BigDecimal getPrice() {
		PriceHistory ph = PriceHistory.getPriceHistory(specification.getUuid());
		BigDecimal cost = ph.getCost(getInstallDate());
		if (cost != null) {
			return cost;
		} else {
			return BigDecimal.ZERO;
		}
	}
	
}
