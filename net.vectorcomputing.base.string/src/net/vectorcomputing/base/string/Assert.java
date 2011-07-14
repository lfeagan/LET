package net.vectorcomputing.base.string;

public final class Assert {

	public static final void isNotNull(final Object object) {
		isNotNull(object, ""); //$NON-NLS-1$
	}

	public static final void isNotNull(final Object object, final String message) {
		if (object == null) {
			final StringBuilder sb = new StringBuilder("null argument: ");  //$NON-NLS-1$
			if (message.length() > 0) {
				sb.append(": "); //$NON-NLS-1$
				sb.append(message);
			}
			throw new IllegalArgumentException(sb.toString());
		}
	}

}
