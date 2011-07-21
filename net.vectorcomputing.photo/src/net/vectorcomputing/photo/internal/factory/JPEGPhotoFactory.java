/*******************************************************************************
 * Copyright 2011 Lance Feagan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.vectorcomputing.photo.internal.factory;

import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.base.string.constraint.StringConstrainEndsWithIgnoreCase;
import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.factory.IPhotoFactory;
import net.vectorcomputing.photo.formats.JPEGPhoto;
import net.vectorcomputing.photo.library.IPhoto;

import org.eclipse.core.filesystem.IFileStore;

public class JPEGPhotoFactory implements IPhotoFactory {

	private static final IStringConstraint CONSTRAINT;
	
	static {
		CONSTRAINT = new StringConstrainEndsWithIgnoreCase(".jpg"); //$NON-NLS-1$
	}
	
	@Override
	public IStringConstraint fileNameConstraint() {
		return CONSTRAINT;
	}

	@Override
	public boolean accepts(IFileStore file) {
		return true;
	}
	
	@Override
	public IPhoto createPhoto(IFileStore fileStore, IPhotoCatalog catalog) {
		return new JPEGPhoto(fileStore, catalog);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			return obj.getClass().equals(JPEGPhotoFactory.class);
		} else {
			return false;
		}
	}
	
}
