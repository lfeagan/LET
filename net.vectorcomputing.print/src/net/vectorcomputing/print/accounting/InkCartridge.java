package net.vectorcomputing.print.accounting;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Table;

@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(appliesTo = "InkCartridge")
public class InkCartridge {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable=false)
	private UUID uuid;
	public UUID getUUID() { return uuid; }
	@SuppressWarnings("unused")
	private void setUUID(UUID uuid) { this.uuid = uuid; }
	
	@Column(name="id", length=128, nullable=false, unique=true)
	private String id;
	public String getId() { return id; }
	@SuppressWarnings("unused")
	private void setId(String id) { this.id = id; }
	
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


	@Column(name="installation", nullable=false)
	private Calendar installation;
	public Calendar getInstallation() { return installation; }
	@SuppressWarnings("unused")
	private void setInstallation(Calendar installation) { this.installation = installation; }

	@Column(name="disposal", length=128, nullable=true)
	private Calendar disposal;
	public Calendar getDisposal() { return disposal; }
	@SuppressWarnings("unused")
	private void setDisposal(Calendar disposal) { this.disposal = disposal; }
	public boolean isDisposed() { return disposal != null; }

	@ManyToOne
	@JoinColumn(name="cartridge_fk", insertable=false, updatable=false)
	private InkCartridgeSpecification specification;
	public InkCartridgeSpecification getSpecification() {
		return specification;
	}
	@SuppressWarnings("unused")
	private void setSpecification(InkCartridgeSpecification specification) {
		this.specification = specification;
	}
	
	/**
	 * Constructor only for Hibernate's use.
	 */
	public InkCartridge() {
		
	}
	
	public InkCartridge(String id, InkCartridgeSpecification specification) {
		this.id = id;
		this.specification = specification;
		this.installation = Calendar.getInstance();
		this.remainingVolume = specification.getFillVolume();
	}
	
	public String getName() {
		return specification.getName();
	}

	public String getAbbreviation() {
		return specification.getAbbreviation();
	}

	public double getFillVolume() {
		return specification.getFillVolume();
	}
	
}
