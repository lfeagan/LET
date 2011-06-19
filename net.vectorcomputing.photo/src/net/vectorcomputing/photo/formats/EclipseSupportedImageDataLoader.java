package net.vectorcomputing.photo.formats;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.MessageFormat;

import net.vectorcomputing.photo.PhotoPlugin;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.ImageData;

public final class EclipseSupportedImageDataLoader {
	
	public static final ImageData read(IFileStore fileStore) throws CoreException {
		InputStream is = null;
		BufferedInputStream bis = null;
		try {
			is = fileStore.openInputStream(0, null);
			bis = new BufferedInputStream(is, 2<<13);
			return new ImageData(bis);
		} catch (Exception e) {
			final String message = MessageFormat.format("Unable to read image data from {0}", fileStore.getName());
			throw new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, message, e));
		} finally {
			StreamCloser.closeWithoutExceptions(bis);
			StreamCloser.closeWithoutExceptions(is);
		}
	}
	
}
