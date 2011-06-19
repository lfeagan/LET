package net.vectorcomputing.photo.formats;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.vectorcomputing.photo.PhotoPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public final class StreamCloser {

	private StreamCloser() { }
	
	public static final void closeWithoutExceptions(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// do nothing
			}			
		}
	}
	
	public static final void closeWithoutExceptions(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				// do nothing
			}
		}
	}	

	public static final void closeWithCoreException(InputStream is) throws CoreException {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, "Exception occurred while closing input stream", e));
			}
		}
	}

	public static final void closeWithCoreException(OutputStream os) throws CoreException {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, "Exception occurred while closing output stream", e));
			}
		}
	}
	
}
