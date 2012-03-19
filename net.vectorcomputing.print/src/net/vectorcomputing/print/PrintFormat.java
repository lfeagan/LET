package net.vectorcomputing.print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @since 1.0
 */
public class PrintFormat {

	private final LineFormat lineFormat = new LineFormat();
	private final List<IColumnFormatter> columnFormatters = new ArrayList<IColumnFormatter>();

	public void addColumnFormatter(final IColumnFormatter columnFormatter) {
		columnFormatters.add(columnFormatter);
	}
	
	public IColumnFormatter getColumnFormatter(int index) {
		return columnFormatters.get(index);
	}
	
	public List<IColumnFormatter> getColumnFormatters() {
		return Collections.unmodifiableList(columnFormatters);
	}
	
	public LineFormat getLineFormat() {
		return lineFormat;
	}
	
}
