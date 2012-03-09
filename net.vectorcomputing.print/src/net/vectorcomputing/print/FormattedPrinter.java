package net.vectorcomputing.print;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.vectorcomputing.print.internal.FormattedRow;

public class FormattedPrinter {

	private final PrintFormat printFormat;

	/**
	 * 
	 * @param printFormat
	 */
	public FormattedPrinter(final PrintFormat printFormat) {
		this.printFormat = printFormat;
	}

	public void printRows(final PrintWriter pw, final List<List<String>> strings) {
		final List<FormattedRow> rows = buildFormattedRows(strings);
		for (FormattedRow row : rows) {
			row.print(pw);
		}		
	}

	public void printRow(final PrintWriter pw, final List<String> listOfString) {
		final List<List<String>> listOfListOfString = new ArrayList<List<String>>(1);
		listOfListOfString.add(listOfString);
		printRows(pw, listOfListOfString);
	}

	private List<FormattedRow> buildFormattedRows(final List<List<String>> listOfListOfString) {
		final List<FormattedRow> rows = new ArrayList<FormattedRow>();
		for (List<String> listOfString : listOfListOfString) {
			rows.add(buildFormattedRow(listOfString));
		}
		return rows;
	}

	private FormattedRow buildFormattedRow(final List<String> strings) {
		final FormattedRow row = new FormattedRow();
		for (int i=0; i < strings.size(); ++i) {
			row.addPrintColumn(printFormat.getColumnFormatter(i).format(strings.get(i)));
		}
		return row;
	}
	
	public static PrintFormat createPrintFormat() {
		final PrintFormat pf = new PrintFormat();
		pf.addColumnFormatter(new ColumnFormatter(10, 20, 0));
		pf.addColumnFormatter(new ColumnFormatter(10, 12, 0));
		pf.addColumnFormatter(new ColumnFormatter(10, 30, 0));
		pf.addColumnFormatter(new ColumnFormatter(10, 20, 0));
		return pf;
	}

	private static List<List<String>> createRows() {
		final List<List<String>> rows = new ArrayList<List<String>>();

		final List<String> firstRow = new ArrayList<String>();
		firstRow.add("delta");
		firstRow.add("alpha alpha alpha alpha");
		firstRow.add("beta beta beta beta beta beta beta beta");
		firstRow.add("gamma gamma gamma gamma gamma gamma gamma");		
		rows.add(firstRow);

		final List<String> secondRow = new ArrayList<String>();
		secondRow.add("alpha alpha alpha alpha alpha");
		secondRow.add("beta beta beta beta beta beta beta beta");
		secondRow.add("gamma gamma gamma gamma gamma gamma gamma");
		rows.add(secondRow);
		
		return rows;
	}
	
	public static void main(String[] args) {
		final List<List<String>> rows = createRows();
		FormattedPrinter pp = new FormattedPrinter(createPrintFormat());
		PrintWriter pw = new PrintWriter(System.out);
		pp.printRows(pw, rows);
		pw.flush();
	}
	
}
