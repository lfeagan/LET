package net.vectorcomputing.print.accounting;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import net.vectorcomputing.base.number.DateRangeWithValue;
import net.vectorcomputing.print.PrintPlugin;

import org.hibernate.Session;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Table;

@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(appliesTo="PriceHistory")
public class PriceHistory {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	/**
	 * Default constuctor as required by Hibernate. Not for application use.
	 */
	public PriceHistory() {
		
	}
	
	@Id
	private UUID uuid;
	public UUID getUUID() { return uuid; }
	public void setUUID(UUID uuid) { this.uuid = uuid; }
	
	public static PriceHistory getPriceHistory(UUID uuid) {
//		Set<Price> prices = Price.getPrices(uuid);
//		return new PriceHistory(uuid, prices);
		
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
//		Set<Price> prices = new HashSet<Price>();
		List result = session.createQuery("from PriceHistory where uuid = :theuuid").setParameter("theuuid", uuid).list();
		PriceHistory p = null;
		for (PriceHistory priceHistory : (List<PriceHistory>) result) {
			p = priceHistory;
			System.out.println(priceHistory.getUUID());
		}
		session.getTransaction().commit();
		session.close();
		return p;
	}
	
//	public PriceHistory(UUID uuid, Collection<Price> prices) {
//		this.uuid = uuid;
//		this.prices.addAll(prices);
//	}
	
//	public PriceHistory(Collection<Price> prices) {
//		for (Price price : prices) {
//			data.put(price.getDate(), price.getPrice());
//		}
//	}
		
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="Prices", joinColumns=@JoinColumn(name="uuid"))
	private Set<Price> prices = new HashSet<Price>();
	public Set<Price> getPrices() { return prices; }
	@SuppressWarnings("unused")
	private void setPrices(Set<Price> prices) {
		System.err.println("setting prices");
		this.prices = prices;
		for (Price price : prices) {
			data.put(price.getDate(), price.getPrice());
		}
	}
	
	@Transient
	private TreeMap<Calendar, BigDecimal> data = new TreeMap<Calendar, BigDecimal>();
	
	/**
	 * Adds a new price at the current time.
	 * @param price
	 */
	public void addCost(BigDecimal price) {
		addCost(Calendar.getInstance(), price);
	}
	
	public void addCost(Calendar begin, BigDecimal price) {
		prices.add(new Price(begin, price));
		data.put(begin, price);
	}

	public void clear() {
		prices.clear();
		data.clear();
	}

	public BigDecimal currentCost() {
		DateRangeWithValue<BigDecimal> current = getCurrentCalendarRange();
		return current.getValue();
	}
	
	public BigDecimal getCost(Calendar when) {
		Entry<Calendar,BigDecimal> entry = data.floorEntry(when);
		if (entry == null) {
			throw new IllegalArgumentException("no data for date specified");
		}
		
		return entry.getValue();
	}
	
	public DateRangeWithValue<BigDecimal> getOldestCalendarRange() {
		Entry<Calendar,BigDecimal> first = data.firstEntry();
		Calendar begin = first.getKey();
		BigDecimal price = first.getValue();
		Calendar end = data.higherKey(begin);
		return new DateRangeWithValue<BigDecimal>(begin.getTime(), end.getTime(), price);
	}
	
	/**
	 * @throws RuntimeException if no prices are defined
	 * @return
	 */
	public DateRangeWithValue<BigDecimal> getCurrentCalendarRange() {
		Entry<Calendar,BigDecimal> last = data.lastEntry();
		if (last == null) {
			throw new RuntimeException("no prices are defined");
		}
		Calendar begin = last.getKey();
		BigDecimal price = last.getValue();
		return new DateRangeWithValue<BigDecimal>(begin.getTime(), null, price);		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		if (!(obj instanceof PriceHistory)) {
			return false;
		}
		PriceHistory other = (PriceHistory) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
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
		StringBuilder sb = new StringBuilder();
		sb.append("PriceHistory ");
		NavigableSet<Calendar> calendars = data.navigableKeySet();
		boolean beforeFirst = false;
		for (Calendar calendar : calendars) {
			if (beforeFirst) {
				sb.append(", ");
			} else {
				beforeFirst = true;
			}
			sb.append('[');
			sb.append(dateFormat.format(calendar.getTime()));
			sb.append(',');
			BigDecimal price = data.get(calendar);
			sb.append(price);
			sb.append(']');
		}
		return sb.toString();
	}
	
}
