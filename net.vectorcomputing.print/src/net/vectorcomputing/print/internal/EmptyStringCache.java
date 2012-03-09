package net.vectorcomputing.print.internal;

import net.vectorcomputing.base.string.GeneratedStringCache;

public class EmptyStringCache {

	private static final GeneratedStringCache emptyStringCache = new GeneratedStringCache(" "); //$NON-NLS-1$
	
	public static GeneratedStringCache get() {
		return emptyStringCache;
	}
	
}
