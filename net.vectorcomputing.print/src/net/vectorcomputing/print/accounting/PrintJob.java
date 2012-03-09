package net.vectorcomputing.print.accounting;

import java.util.Calendar;
import java.util.Set;

public interface PrintJob {
	
	public Calendar getCalendar();
	
	public Media getMedia();
	
	public Set<InkCartridge> getInkUsage();
	
	public String getType();
	
	public String getFilename();
	
}
