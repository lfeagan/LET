package net.vectorcomputing.photo.internal.factory;

import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.base.string.constraint.StringConstrainEndsWithIgnoreCase;
import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.factory.IPhotoFactory;
import net.vectorcomputing.photo.formats.TIFFPhoto;
import net.vectorcomputing.photo.library.IPhoto;

import org.eclipse.core.filesystem.IFileStore;

public class TIFFPhotoFactory implements IPhotoFactory {

	private static final IStringConstraint constraint;
	
	static {
		constraint = new StringConstrainEndsWithIgnoreCase(".tiff"); //$NON-NLS-1$
	}
	
	@Override
	public IStringConstraint fileNameConstraint() {
		return constraint;
	}

	@Override
	public boolean accepts(IFileStore file) {
		return true;
	}
	
	@Override
	public IPhoto createPhoto(IFileStore fileStore, IPhotoCatalog catalog) {
		return new TIFFPhoto(fileStore, catalog);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			return obj.getClass().equals(TIFFPhotoFactory.class);
		} else {
			return false;
		}
	}

}
