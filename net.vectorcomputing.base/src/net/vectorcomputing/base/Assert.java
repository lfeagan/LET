package net.vectorcomputing.base;

public class Assert {

	public static void isNotNull(final Object object) {
		isNotNull(object, null);
	}
	
	public static void isNotNull(final Object object, final String messageDetail) {
		if (object == null) {
			StringBuilder message = new StringBuilder();
			message.append("null argument");
			if (messageDetail != null && messageDetail.length() > 0) {
				message.append(':');
				message.append(messageDetail);
			}
			throw new AssertionError(message.toString());
		}
	}
	
}
