package net.vectorcomputing.print.internal;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FormattedRow {
	
	private final List<FormattedColumn> printColumns = new ArrayList<FormattedColumn>();
	
	public void addPrintColumn(final FormattedColumn printColumn) {
		printColumns.add(printColumn);
	}
	
	public FormattedColumn getPrintColumn(final int index) {
		return printColumns.get(index);
	}
	
	public FormattedColumn removePrintColumn(final int index) {
		return printColumns.remove(index);
	}
	
	public List<FormattedColumn> getPrintColumns() {
		return Collections.unmodifiableList(printColumns);
	}
	
	public void print(PrintWriter pw) {	
		final int lines = getMaximumLines();
		for (int line=0; line < lines; ++line) {
			for (FormattedColumn pc : printColumns) {
				pc.print(pw, line);
			}
			pw.print('\n');
		}
	}
	
	private int getMaximumLines() {
		int maximumLines = 0;
		for (FormattedColumn pc : printColumns) {
			if (pc.getNumberOfLines() > maximumLines) {
				maximumLines = pc.getNumberOfLines();
			}
		}
		return maximumLines;
	}
	
}
