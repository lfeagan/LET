package net.vectorcomputing.print.accounting;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import net.vectorcomputing.print.PrintPlugin;

import org.hibernate.Session;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@DynamicUpdate
@Table(name="Media", uniqueConstraints=@UniqueConstraint(columnNames={"maker", "model"}))
public class Media {

	public static enum Type {
		ROLL,
		SHEET;
	}
		
	/**
	 * Constructor only for Hibernate's use.
	 */
	public Media() {
		
	}
	
	public Media(String maker, String model, Type type, Finish finish, Size size, int quantity) {
		super();
		this.maker = maker;
		this.model = model;
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
	public UUID getUuid() { return uuid; }
	@SuppressWarnings("unused")
	private void setUuid(UUID uuid) { this.uuid = uuid; }
	
	@Column(name="maker", precision=128, nullable=false, unique=false)
	private String maker;
	public String getMaker() { return maker; }
	public void setMaker(String maker) { this.maker = maker; }

	@Column(name="model", precision=128, nullable=false, unique=false)
	private String model;
	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }

	@Column(name="type", unique=false, nullable=false)
	private Type type;
	public Type getType() { return type; }
	public void setType(Type type) { this.type = type; }

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="finish_fk", nullable=false, insertable=true, updatable=true)
	private Finish finish;
	public Finish getFinish() { return finish; }
	public void setFinish(Finish finish) { this.finish = finish; }

	@Column(name="size", unique=false, nullable=false)
	private Size size;
	public Size getSize() { return size; }
	public void setSize(Size size) { this.size = size; }

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
			priceHistory = PriceHistory.getPriceHistory(this.getUuid());
		}
		if (priceHistory == null) {
			System.err.println("unable to find price history");
			priceHistory = new PriceHistory();
			priceHistory.setUUID(getUuid());
		}
		return priceHistory;
	}
	
	public BigDecimal getPrice() {
		PriceHistory ph = getPriceHistory();
		BigDecimal cost = ph.currentCost();
		if (cost != null) {
			return cost;
		} else {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finish == null) ? 0 : finish.hashCode());
		result = prime * result + ((maker == null) ? 0 : maker.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
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
		builder.append(", model=");
		builder.append(model);
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
	
	public static void createBuiltins() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(new Media("Epson", "Enhanced Matte", Type.SHEET, Finish.getFinish("Matte"), new Size(13, 19), 50));
		session.save(new Media("Epson", "Premium Semi-gloss Photo", Type.SHEET, Finish.getFinish("Semi-Gloss"), new Size(13, 19), 50));
		session.save(new Media("Epson", "Glossy Photo", Type.SHEET, Finish.getFinish("Gloss"), new Size(8.5, 11), 50));
		
		session.getTransaction().commit();
		session.close();		
	}

}
