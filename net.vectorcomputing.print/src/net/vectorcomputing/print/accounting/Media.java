package net.vectorcomputing.print.accounting;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@DynamicUpdate
@Table(name="Media", uniqueConstraints=@UniqueConstraint(columnNames={"maker", "name"}))
public class Media {

	public static enum Type {
		ROLL,
		SHEET;
	}
	
	public static enum Finish {
		MATTE,
		GLOSSY;
	}
	
	/**
	 * Constructor only for Hibernate's use.
	 */
	public Media() {
		
	}
	
	public Media(String maker, String name, Type type, Finish finish, Size size, int quantity) {
		super();
		this.maker = maker;
		this.name = name;
		this.type = type;
		this.finish = finish;
		this.size = size;
		this.quantity = quantity;
	}
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	@Column(name="uuid", unique=true, nullable=false)
	private UUID uuid;
	public UUID getUUID() { return uuid; }
	@SuppressWarnings("unused")
	private void setUUID(UUID uuid) { this.uuid = uuid; }
	
	@Column(name="maker", precision=128, nullable=false)
	private String maker;
	public String getMake() { return maker; }
	@SuppressWarnings("unused")
	private void setMaker(String maker) { this.maker = maker; }

	@Column(name="name", precision=128, nullable=false)
	private String name;
	public String getName() { return name; }
	@SuppressWarnings("unused")
	private void setName(String name) { this.name = name; }

	@Column(name="type", unique=false, nullable=false)
	private Type type;
	public Type getType() { return type; }
	@SuppressWarnings("unused")
	private void setType(Type type) { this.type = type; }

	@Column(name="finish", unique=false, nullable=false)
	private Finish finish;
	public Finish getFinish() { return finish; }
	@SuppressWarnings("unused")
	private void setFinish(Finish finish) { this.finish = finish; }

	@Column(name="size", unique=false, nullable=false)
	private Size size;
	public Size getSize() { return size; }
	@SuppressWarnings("unused")
	private void setSize(Size size) { this.size = size; }

	@Column(name="quantity", unique=false, nullable=false)
	private int quantity = 1;
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) {
		if (quantity < 1) {
			throw new IllegalArgumentException("quantity must be a positive integer");
		}
		this.quantity = quantity;
	}

	@Transient
	private PriceHistory priceHistory = null;
	public PriceHistory getPriceHistory() {
		if (priceHistory == null) {
			System.err.println("found price history");
			priceHistory = PriceHistory.getPriceHistory(this.getUUID());
		}
		if (priceHistory == null) {
			System.err.println("unable to find price history");
			priceHistory = new PriceHistory();
			priceHistory.setUUID(getUUID());
		}
		return priceHistory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finish == null) ? 0 : finish.hashCode());
		result = prime * result + ((maker == null) ? 0 : maker.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof Media)) {
			return false;
		}
		Media other = (Media) obj;
		if (finish != other.finish) {
			return false;
		}
		if (maker == null) {
			if (other.maker != null) {
				return false;
			}
		} else if (!maker.equals(other.maker)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (quantity != other.quantity) {
			return false;
		}
		if (size == null) {
			if (other.size != null) {
				return false;
			}
		} else if (!size.equals(other.size)) {
			return false;
		}
		if (type != other.type) {
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
		builder.append("Media [uuid=");
		builder.append(uuid);
		builder.append(", maker=");
		builder.append(maker);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", finish=");
		builder.append(finish);
		builder.append(", size=");
		builder.append(size);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", priceHistory=");
		builder.append(priceHistory);
		builder.append("]");
		return builder.toString();
	}
		
}
