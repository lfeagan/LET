package net.vectorcomputing.print.accounting;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
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
import net.vectorcomputing.print.preferences.PrintPreferences;

import org.hibernate.Session;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Table;

/**
 * 
 * @author lfeagan
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 */
@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(appliesTo="PriceHistory")
public class PriceHistory {
	
	/**
	 * Default constuctor as required by Hibernate. Not for application use.
	 */
	public PriceHistory() {
		
	}
	
	public PriceHistory(UUID uuid) {
		this.uuid = uuid;
	}
	
	@Id
	private UUID uuid;
	public UUID getUUID() { return uuid; }
	public void setUUID(UUID uuid) { this.uuid = uuid; }
	
	public static PriceHistory getPriceHistory(UUID uuid) {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		Object result =  session.createQuery("from PriceHistory where uuid = :theuuid").setParameter("theuuid", uuid).uniqueResult();
		PriceHistory ph;
		if (result != null) {
			ph = (PriceHistory) result;
		} else {
			ph = new PriceHistory(uuid);
			session.save(ph);
		}
		
		session.getTransaction().commit();
		session.close();
		return ph;
	}
	
	public static List<PriceHistory> getAll() {
		Session session = PrintPlugin.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PriceHistory> result = (List<PriceHistory>) session.createQuery("from PriceHistory").list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="Prices", joinColumns=@JoinColumn(name="uuid"))
	private Set<Price> prices = new HashSet<Price>();
	public Set<Price> getPrices() { return prices; }
	@SuppressWarnings("unused")
	public void setPrices(Collection<Price> prices) {
		System.err.println("setting prices");
		this.prices.clear();
		this.prices.addAll(prices);
		rebuildData();
	}
	
	private void rebuildData() {
		data.clear();
		for (Price price : prices) {
			data.put(price.getDate(), price.getPrice());
		}
	}
	
	@Transient
	private TreeMap<Calendar, BigDecimal> data = new TreeMap<Calendar, BigDecimal>();

//	public Set<Price> getPrices() {
//		Set<Price> prices = new HashSet<Price>();
//		for (Entry<Calendar, BigDecimal> entry : data.entrySet()) {
//			prices.add(new Price(entry.getKey(), entry.getValue()));
//		}
//		return prices;
//	}
	
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
		rebuildData();
		Entry<Calendar,BigDecimal> entry = data.floorEntry(when);
		if (entry == null) {
			return BigDecimal.ZERO;
//			throw new IllegalArgumentException("no data for date specified");
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
			sb.append(PrintPreferences.getDateFormat().format(calendar.getTime()));
			sb.append(',');
			BigDecimal price = data.get(calendar);
			sb.append(price);
			sb.append(']');
		}
		return sb.toString();
	}
	
}
