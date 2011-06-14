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
package net.vectorcomputing.photo.internal.library;

import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.library.IPhoto;

import org.eclipse.core.filesystem.IFileStore;

public abstract class AbstractPhoto implements IPhoto {

	private IFileStore fileStore;
	private IPhotoCatalog catalog;
	
	public AbstractPhoto(IFileStore fileStore, IPhotoCatalog catalog) {
		this.fileStore = fileStore;
		this.catalog = catalog;
	}
	
	@Override
	public IFileStore getFileStore() {
		return fileStore;
	}
	
	@Override
	public IPhotoCatalog getCatalog() {
		return catalog;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catalog == null) ? 0 : catalog.hashCode());
		result = prime * result
				+ ((fileStore == null) ? 0 : fileStore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractPhoto)) {
			return false;
		}
		AbstractPhoto other = (AbstractPhoto) obj;
		if (catalog == null) {
			if (other.catalog != null) {
				return false;
			}
		} else if (!catalog.equals(other.catalog)) {
			return false;
		}
		if (fileStore == null) {
			if (other.fileStore != null) {
				return false;
			}
		} else if (!fileStore.equals(other.fileStore)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractPhoto [fileStore="); //$NON-NLS-1$
		builder.append(fileStore);
		builder.append(", catalog="); //$NON-NLS-1$
		builder.append(catalog);
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}
	
}
