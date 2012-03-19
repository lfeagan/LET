package net.vectorcomputing.print.preferences;

import java.text.SimpleDateFormat;

/**
 * Used to get and set preferences related to printing.
 * 
 * @author lfeagan
 * @since 1.0
 */
public final class PrintPreferences {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat getDateFormat() {
		return DATE_FORMAT;
	}

}
