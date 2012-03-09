package net.vectorcomputing.print;

import net.vectorcomputing.print.internal.FormattedColumn;

public interface IColumnFormatter {

	public FormattedColumn format(final String string);

}