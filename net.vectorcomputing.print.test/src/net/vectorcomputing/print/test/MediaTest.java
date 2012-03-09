package net.vectorcomputing.print.test;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import net.vectorcomputing.print.accounting.Database;
import net.vectorcomputing.print.accounting.Media;
import net.vectorcomputing.print.accounting.PriceHistory;
import net.vectorcomputing.print.accounting.Size;

import org.hibernate.Session;
import org.junit.Test;

public class MediaTest {
	
	@Test
	public void addMedia() {
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		Media matte = new Media("Epson", "Enhanced Matte", Media.Type.SHEET, Media.Finish.MATTE, new Size(13, 19), 50);
		session.save(matte);
		PriceHistory priceHistory = matte.getPriceHistory();
		priceHistory.addCost(new GregorianCalendar(2000, 0, 1), BigDecimal.valueOf(1.00));
		priceHistory.addCost(new GregorianCalendar(2001, 0, 1), BigDecimal.valueOf(2.00));
		priceHistory.addCost(new GregorianCalendar(2002, 0, 1), BigDecimal.valueOf(4.00));
		priceHistory.addCost(new GregorianCalendar(2003, 0, 1), BigDecimal.valueOf(8.00));
		session.save(priceHistory);
		
		Media glossy = new Media("Epson", "Glossy", Media.Type.SHEET, Media.Finish.MATTE, new Size(13, 19), 50);
		session.save(glossy);
		PriceHistory glossyPrices = glossy.getPriceHistory();
		glossyPrices.addCost(BigDecimal.valueOf(1.23));
		session.save(glossy);
		session.save(glossyPrices);
		
		session.getTransaction().commit();
		session.close();	
	}
	
	@Test
	public void getMedia() {
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery("from Media").list();
		for (Media media : (List<Media>) result) {
			media.getPriceHistory();
			System.out.println(media);
		}


		session.getTransaction().commit();
		session.close();	
	}

}
