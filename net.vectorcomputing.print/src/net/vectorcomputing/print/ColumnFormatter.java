package net.vectorcomputing.print;

import net.vectorcomputing.print.internal.EmptyStringCache;
import net.vectorcomputing.print.internal.FormattedColumn;

/**
 * @since 1.0
 */
public class ColumnFormatter implements IColumnFormatter {

	private static final TextJustification DEFAULT_TEXT_JUSTIFICATION = TextJustification.LEFT;
	private static final int DEFAULT_MINIMUM_WIDTH = 10;
	private static final int DEFAULT_MAXIMUM_WIDTH = 40;
	
	private TextJustification justification = DEFAULT_TEXT_JUSTIFICATION;
	private int minimumWidth = DEFAULT_MINIMUM_WIDTH;
	private int maximumWidth = DEFAULT_MAXIMUM_WIDTH;
	private int padBefore = 1;
	
	public ColumnFormatter() {
		
	}

	/**
	 * Constructor for a column formatter with arguments for common settings.
	 * 
	 * @param mimimumWidth sets the minimum width
	 * @param maximumWidth
	 * @param padBefore
	 */
	public ColumnFormatter(int mimimumWidth, int maximumWidth, int padBefore) {
		setMinimumWidth(mimimumWidth);
		setMaximumWidth(maximumWidth);
		setPadBefore(padBefore);
	}

	/**
	 * Sets the minimum width allowed for this column (in characters).
	 * 
	 * @param minimumWidth
	 *            the minimum width allowed for this column (in characters).
	 * @throws AssertionError
	 *             if the minimum width is less than zero
	 */
	public void setMinimumWidth(final int minimumWidth) {
		if (minimumWidth < 0) {
			throw new AssertionError("minimumWidth >= 0"); //$NON-NLS-1$
		}
		this.minimumWidth = minimumWidth;
	}

	/**
	 * Returns the minimum width allowed for this column (in characters).
	 * 
	 * @return the minimum width allowed for this column (in characters).
	 */
	public int getMinimumWidth() {
		return minimumWidth;
	}

	/**
	 * Sets the maximum width allowed for this column (in characters).
	 * 
	 * @param maximumWidth
	 *            the maximum width allowed for this column (in characters).
	 * @throws AssertionError
	 *             if the maximum width is less than zero
	 */
	public void setMaximumWidth(final int maximumWidth) {
		if (maximumWidth < 0) {
			throw new AssertionError("maximumWidth >= 0"); //$NON-NLS-1$
		}
		this.maximumWidth = maximumWidth;
	}

	/**
	 * Returns the maximum width allowed for this column (in characters).
	 * 
	 * @return the maximum width allowed for this column (in characters).
	 */
	public int getMaximumWidth() {
		return maximumWidth;
	}

	/**
	 * Sets the number of characters to be place before they text of this
	 * column. This padding will be present at the start of every line of
	 * output.
	 * 
	 * @param padBefore
	 *            the number of characters to place before the text of this
	 *            column on each line.
	 */
	public void setPadBefore(final int padBefore) {
		if (padBefore < 0) {
			throw new AssertionError("padBefore >= 0"); //$NON-NLS-1$
		}
		this.padBefore = padBefore;
	}

	/**
	 * Returns the number of space characters placed before each line of
	 * formatted output.
	 * 
	 * @return the number of space characters placed before each line of
	 *         formatted output.
	 */
	public int getPadBefore() {
		return padBefore;
	}

	public void setJustification(final TextJustification justification) {
		this.justification = justification;
	}

	public TextJustification getJustification() {
		return justification;
	}
	
	/* (non-Javadoc)
	 * @see net.vectorcomputing.print.IColumnFormat#format(java.lang.String)
	 */
	@Override
	public FormattedColumn format(final String string) {
		switch (justification) {
		case LEFT:
			return formatLeftJustified(string);
		case RIGHT:
			
		}

		return null;
	}
	
	private FormattedColumn formatLeftJustified(final String string) {
		final FormattedColumn formattedColumn = new FormattedColumn(this);

		if (string.length() < minimumWidth) {
			formattedColumn.addLine(buildLine(string, minimumWidth));
			return formattedColumn;
		}
		
		int effectiveWidth = maximumWidth - padBefore;
		
		int begin = 0; // the index of the beginning (inclusive) of the sub-string
		int end = 0; // the index of the end (exclusive) of the sub-string
		int remaining = string.length();
		while (remaining > 0) {
			/**
			 * Skip leading spaces by moving from left-to-right until a
			 * non-space character is found.
			 */
			while (remaining > 0 && string.charAt(begin) == ' ') {
				++begin;
				--remaining;
			}
			
			if (remaining < effectiveWidth) {
				end = string.length();
			} else {
				end = begin + effectiveWidth;

				/**
				 * Avoid splicing a word in half by moving the end from
				 * right-to-left to find a space character to cleanly cut out a
				 * sub-string.
				 */
				while (string.charAt(end-1) != ' ') {
					--end;
					if (end <= begin) {
						break;
					}
				}
			}
			
			formattedColumn.addLine(buildLine(string.substring(begin, end), effectiveWidth));
			begin = end;
			remaining = string.length() - begin;			
		}

		formattedColumn.trimToSize();
		return formattedColumn;
	}
	
	private final String buildLine(final String string, int width) {
		final StringBuilder line = new StringBuilder();
		line.append(EmptyStringCache.get().getIndentString(padBefore));
		line.append(string);
		if (line.length() < width) {
			line.append(EmptyStringCache.get().getIndentString(width - line.length()));
		}
		return line.toString();
	}
	
}
