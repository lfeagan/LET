package net.vectorcomputing.print.preferences;

import java.text.SimpleDateFormat;

public final class PrintPreferences {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat getDateFormat() {
		return DATE_FORMAT;
	}
	
}
