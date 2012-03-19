package net.vectorcomputing.print.accounting;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import net.vectorcomputing.print.PrintPlugin;

import org.hibernate.Session;

/**
 * Describes the price of something at a particular date. Typically used as a
 * collection in {@link PriceHistory}.
 * 
 * @author lfeagan
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@Embeddable
public class Price {

	/**
	 * Constructor for Hibernate's use only.
	 */
	public Price() {
		
	}
	
	/**
	 * Constructor for application's use.
	 * @param date
	 * @param price
	 */
	public Price(Calendar date, BigDecimal price) {
		this.date = date;
		this.price = price;
	}
	
	@Column(name="date", nullable=false)
	private Calendar date;
	public Calendar getDate() { return date; }
	public void setDate(Calendar date) { this.date = date; }
	
	@Column(name="price", nullable=false)
	private BigDecimal price;
	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }

	public static Set<Price> getPrices(UUID uuid) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		Set<Price> prices = new HashSet<Price>();
		List result = session.createQuery("from Price as price").list();
		for (Price price : (List<Price>) result) {
			prices.add(price);
			System.out.println(price.getPrice());
		}
		session.getTransaction().commit();
		session.close();
		return prices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		if (!(obj instanceof Price)) {
			return false;
		}
		Price other = (Price) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Price [date=");
		builder.append(date);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
	
}
