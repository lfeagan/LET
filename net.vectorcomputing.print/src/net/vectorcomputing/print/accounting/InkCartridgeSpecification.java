package net.vectorcomputing.print.accounting;

import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Table;

@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(appliesTo = "InkCartridgeSpecification")
public class InkCartridgeSpecification {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable=false)
	private UUID uuid;
	public UUID getUUID() { return uuid; }
	@SuppressWarnings("unused")
	private void setUUID(UUID uuid) { this.uuid = uuid; }
	
	@Column(name="name", length=128, nullable=false, unique=true)
	private String name;
	public String getName() { return name; }
	@SuppressWarnings("unused")
	private void setName(String name) { this.name = name; }

	@Column(name="abbreviation", length=10, nullable=false, unique=false)
	private String abbreviation;
	public String getAbbreviation() { return abbreviation; }
	@SuppressWarnings("unused")
	private void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }

	@Column(name="fillVolume", nullable=false, unique=false)
	private double fillVolume;
	public double getFillVolume() { return fillVolume; }
	@SuppressWarnings("unused")
	private void setFillVolume(double fillVolume) { this.fillVolume = fillVolume; }
	
	@OneToMany
	@JoinColumn(name="InkCartridges_FK")
	private Map<UUID,InkCartridge> inkCartridges;
	public Map<UUID,InkCartridge> getInkCartridges() { return inkCartridges; }
	public void setInkCartridges(Map<UUID,InkCartridge> inkCartridges) { this.inkCartridges = inkCartridges; }
	
	@Transient
	private PriceHistory priceHistory = null;
	public PriceHistory getPriceHistory() {
		if (priceHistory == null) {
			priceHistory = PriceHistory.getPriceHistory(this.getUUID());
		}
		return priceHistory;
	}

	/**
	 * Constructor for Hibernate use only.
	 */
	public InkCartridgeSpecification() {
		
	}
	
	/**
	 * Constructor for application use.
	 * @param uuid
	 * @param name
	 * @param abbreviation
	 * @param fillVolume
	 */
	public InkCartridgeSpecification(UUID uuid, String name, String abbreviation, double fillVolume) {
		this.uuid = uuid;
		this.name = name;
		this.abbreviation = abbreviation;
		this.fillVolume = fillVolume;
	}
	
	public InkCartridgeSpecification(String name, double fillVolume) {
		this(name, findAbbreviation(name), fillVolume);
	}
	
	private static final String findAbbreviation(String name) {
		String[] decomposition = name.split(" ");
		StringBuilder abbreviation = new StringBuilder();
		for (String element : decomposition){
			if (element.equalsIgnoreCase("Cyan")) {
				abbreviation.append('C');;
			} else if (element.equalsIgnoreCase("Magenta")) {
				abbreviation.append('M');
			} else if (element.equalsIgnoreCase("Yellow")) {
				abbreviation.append('Y');
			} else if (element.equalsIgnoreCase("Black")) {
				abbreviation.append('K');
			} else if (element.equalsIgnoreCase("Light")) {
				abbreviation.append('L');
			} else if (element.equalsIgnoreCase("Vivid")) {
				abbreviation.append('V');
			} else {
				return name.substring(0, 1);
			}
		}
		
		return abbreviation.toString();
	}
	
	public InkCartridgeSpecification(String name, String abbreviation, double fillVolume) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.fillVolume = fillVolume;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abbreviation == null) ? 0 : abbreviation.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fillVolume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof InkCartridgeSpecification)) {
			return false;
		}
		InkCartridgeSpecification other = (InkCartridgeSpecification) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null) {
				return false;
			}
		} else if (!abbreviation.equals(other.abbreviation)) {
			return false;
		}
		if (Double.doubleToLongBits(fillVolume) != Double
				.doubleToLongBits(other.fillVolume)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InkCartridgeSpecification [uuid=");
		builder.append(uuid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", abbreviation=");
		builder.append(abbreviation);
		builder.append(", fillVolume=");
		builder.append(fillVolume);
		builder.append("]");
		return builder.toString();
	}
	
}
