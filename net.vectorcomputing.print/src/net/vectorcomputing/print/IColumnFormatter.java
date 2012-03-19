package net.vectorcomputing.print;

import net.vectorcomputing.print.internal.FormattedColumn;

/**
 * @since 1.0
 */
public interface IColumnFormatter {

	public FormattedColumn format(final String string);

}