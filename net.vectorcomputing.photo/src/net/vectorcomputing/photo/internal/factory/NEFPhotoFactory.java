package net.vectorcomputing.photo.internal.factory;

import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.base.string.constraint.StringConstrainEndsWithIgnoreCase;
import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.factory.IPhotoFactory;
import net.vectorcomputing.photo.formats.NEFPhoto;
import net.vectorcomputing.photo.library.IPhoto;

import org.eclipse.core.filesystem.IFileStore;

public class NEFPhotoFactory implements IPhotoFactory {

	private static final IStringConstraint constraint;
	
	static {
		constraint = new StringConstrainEndsWithIgnoreCase(".nef"); //$NON-NLS-1$
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
		return new NEFPhoto(fileStore, catalog);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			return obj.getClass().equals(NEFPhotoFactory.class);
		} else {
			return false;
		}
	}

}
