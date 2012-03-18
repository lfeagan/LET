package net.vectorcomputing.print.accounting;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(name="PrintJob")
public class PrintJob {
	
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

	@Column(name="type", length=128, nullable=false, unique=true)
	private String type;
	public String getType() { return type; }
	@SuppressWarnings("unused")
	private void setType(String type) { this.type = type; }

	@Column(name="date", nullable=false)
	private Calendar date;
	public Calendar getDate() { return date; }
	public void setDate(Calendar date) { this.date = date; }
	
	@ManyToOne(optional=false)
	@JoinColumn(name="mediaUsage_fk", updatable=false)
	private MediaUsage mediaUsage;
	public MediaUsage getMediaUsage() {
		return mediaUsage;
	}
	void setMediaUsage(MediaUsage mediaUsage) {
		this.mediaUsage = mediaUsage;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="CartridgeUsage", joinColumns=@JoinColumn(name="uuid"))
	private Set<CartridgeUsage> cartridgeUsage = new HashSet<CartridgeUsage>();
	public Set<CartridgeUsage> getCartridgeUsage() { return cartridgeUsage; }
	public void addCartridgeUsage(CartridgeUsage cartridgeUsage) {
		this.cartridgeUsage.add(cartridgeUsage);
	}
	public void setCartridgeUsage(Collection<CartridgeUsage> cartridgeUsage) {
		this.cartridgeUsage.clear();
		this.cartridgeUsage.addAll(cartridgeUsage);
	}

	public PrintJob(String name, String type, Calendar date) {
		this.name = name;
		this.type = type;
		this.date = date;
	}
	
}
