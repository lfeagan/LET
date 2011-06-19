package net.vectorcomputing.photo.formats;

import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.internal.library.AbstractPhoto;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.ImageData;

public class BMPPhoto extends AbstractPhoto {

	private ImageData imageData;
	
	public BMPPhoto(IFileStore fileStore, IPhotoCatalog catalog) {
		super(fileStore, catalog);
	}
	
	@Override
	public ImageData getImageData() throws CoreException {
		if (imageData != null) {
			imageData = EclipseSupportedImageDataLoader.read(fileStore);
		}
		return imageData;
	}
	
}
