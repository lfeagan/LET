package net.vectorcomputing.print.internal;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vectorcomputing.base.Assert;
import net.vectorcomputing.print.IColumnFormatter;

/**
 * A print columns stores and prints the lines present in a single column.
 */
public class FormattedColumn {
	
	private final IColumnFormatter format;
	private final ArrayList<String> lines = new ArrayList<String>();
	private int width = 0;

	public FormattedColumn(final IColumnFormatter format) {
		this.format = format;
	}
	
	/**
	 * Adds a single line to this column.
	 * 
	 * @param line
	 */
	public void addLine(final String line) {
		validateLine(line);
		lines.add(line);
		updateWidth(line);
	}
 	
	private void validateLine(final String line) {
		Assert.isNotNull(line, "line"); //$NON-NLS-1$
		
		// TODO assert that the line does not contain any carriage return/line feed characters
		
//		if (line.length() > format.getMaximumWidth()) {
//			throw new AssertionError("line.length() > format.getMaximumWidth()"); //$NON-NLS-1$
//		}
	}
	
	private void updateWidth(final String line) {
		if (line.length() > width) {
			width = line.length();
		}
	}
	
	public String getLine(final int index) {
		return lines.get(index);
	}
	
	public List<String> getLines() {
		return Collections.unmodifiableList(lines);
	}
	
	public int getNumberOfLines() {
		return lines.size();
	}

	public void trimToSize() {
		lines.trimToSize();
	}
	
	public int getWidth() {
		return width;
	}
	
	public void print(final PrintWriter pw, final int line) {
		if (line < getNumberOfLines()) {
			pw.print(getLine(line));
		} else {
			pw.print(EmptyStringCache.get().getIndentString(getWidth()));
		}
	}
	
}
