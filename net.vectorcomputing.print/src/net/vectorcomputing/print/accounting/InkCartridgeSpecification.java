package net.vectorcomputing.print.accounting;

import java.util.HashSet;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "InkCartridgeSpecification", uniqueConstraints=@UniqueConstraint(columnNames={"maker", "model"}))
public class InkCartridgeSpecification {
	
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

	@Column(name="abbreviation", length=10, nullable=false, unique=false)
	private String abbreviation;
	public String getAbbreviation() { return abbreviation; }
	public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }

	@Column(name="fillVolume", nullable=false, unique=false)
	private double fillVolume;
	public double getFillVolume() { return fillVolume; }
	public void setFillVolume(double fillVolume) { this.fillVolume = fillVolume; }
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cartridge_fk")
	private final Set<InkCartridge> inkCartridges = new HashSet<InkCartridge>();
	public Set<InkCartridge> getInkCartridges() { return inkCartridges; }
	void addInkCartridge(InkCartridge inkCartridge) { inkCartridge.setSpecification(this); this.inkCartridges.add(inkCartridge); }
	void setInkCartridges(Set<InkCartridge> inkCartridges) { this.inkCartridges.addAll(inkCartridges); }
	
//	private Map<UUID,InkCartridge> inkCartridges;
//	public Map<UUID,InkCartridge> getInkCartridges() { return inkCartridges; }
//	void addInkCartridge(InkCartridge inkCartridge) { inkCartridge.setSpecification(this); this.inkCartridges.put(inkCartridge.getUUID(), inkCartridge); }
//	void setInkCartridges(Map<UUID,InkCartridge> inkCartridges) { this.inkCartridges = inkCartridges; }

	
	@Transient
	private PriceHistory priceHistory = null;
	public PriceHistory getPriceHistory() {
		if (priceHistory == null) {
			priceHistory = PriceHistory.getPriceHistory(this.getUuid());
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
	 * @param model
	 * @param abbreviation
	 * @param fillVolume
	 */
	public InkCartridgeSpecification(UUID uuid, String maker, String model, String abbreviation, double fillVolume) {
		this.uuid = uuid;
		this.maker = maker;
		this.model = model;
		this.abbreviation = abbreviation;
		this.fillVolume = fillVolume;
	}
	
	public InkCartridgeSpecification(String maker, String model, double fillVolume) {
		this(maker, model, generateAbbreviation(model), fillVolume);
	}
	
	public static final String generateAbbreviation(String model) {
		String[] decomposition = model.split(" ");
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
				if (element.length() > 0) {
					abbreviation.append(element.substring(0, 1));
				}
			}
		}
		
		return abbreviation.toString();
	}
	
	public InkCartridgeSpecification(String maker, String model, String abbreviation, double fillVolume) {
		this.maker = maker;
		this.model = model;
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
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
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
		builder.append(", model=");
		builder.append(model);
		builder.append(", abbreviation=");
		builder.append(abbreviation);
		builder.append(", fillVolume=");
		builder.append(fillVolume);
		builder.append("]");
		return builder.toString();
	}
	
}
