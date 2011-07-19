package net.vectorcomputing.tuple;

import java.io.PrintWriter;
import java.util.List;

public class TuplePrettyPrinter {

	private static final int DEFAULT_LINE_LENGTH = 80;
	private int lineLength = DEFAULT_LINE_LENGTH;
	
	private static final int DEFAULT_MINIMUM_SPACES_BETWEEN_ENTRIES = 2;
	private int minimumSpacesBetweenEntries = DEFAULT_MINIMUM_SPACES_BETWEEN_ENTRIES;
	
	public void setLineLength(final int lineLength) {
		this.lineLength = lineLength;
	}

	public int getLineLength() {
		return lineLength;
	}

	public void setMinimumSpacesBetweenEntries(final int minimumSpacesBetweenEntries) {
		this.minimumSpacesBetweenEntries = minimumSpacesBetweenEntries;
	}
	
	public int getMinimumSpacesBetweenEntries() {
		return minimumSpacesBetweenEntries;
	}

	
	public void print(PrintWriter pw, List<Tuple> tuples) {
		final int maxTupleSize = getMaxTupleSize(tuples);
		final int[] maxEntryLengths = getMaxEntryLengths(tuples, maxTupleSize);

		int unwrappedEntriesLength = 0;
		for (int i=0; i < maxEntryLengths.length; ++i) {
			unwrappedEntriesLength += maxEntryLengths[i];
		}
		
		final int unwrappedLength = minimumSpacesBetweenEntries * (maxTupleSize - 1) + unwrappedEntriesLength;
		
//		for (Tuple tuple : tuples) {
//			pw.println(buildLine(tuple, maxKeyLength, valueStart));
//		}
	}

	private static final int getMaxTupleSize(final List<Tuple> tuples) {
		int maxTupleSize = 0;
		int tupleSize;
		for (Tuple tuple : tuples) {
			tupleSize = tuple.size();
			if (tupleSize > maxTupleSize) {
				maxTupleSize = tupleSize;
			}
		}
		return maxTupleSize;
	}
	
	private static final int[] getMaxEntryLengths(final List<Tuple> tuples, final int maxTupleSize) {
		int[] maxEntryLengths = new int[maxTupleSize];
		for (Tuple tuple : tuples) {
			for (int i=0; i < tuple.size(); ++i) {
				Object entry = tuple.getEntry(i);
				if (entry instanceof String) {
					String string = (String) entry;
					if (string.length() > maxEntryLengths[i]) {
						maxEntryLengths[i] = string.length();
					}
				} else {
					throw new RuntimeException("encountered non-string object");
				}
			}			
		}		
		return maxEntryLengths;
	}

	
}
