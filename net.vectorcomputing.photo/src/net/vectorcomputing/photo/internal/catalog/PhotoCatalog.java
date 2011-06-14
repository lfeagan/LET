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
package net.vectorcomputing.photo.internal.catalog;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vectorcomputing.photo.PhotoPlugin;
import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.catalog.IPhotoCatalogUpdateStatistics;
import net.vectorcomputing.photo.factory.IPhotoFactoryDescriptor;
import net.vectorcomputing.photo.library.IPhoto;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class PhotoCatalog implements IPhotoCatalog {

	private final URI uri;
	private final IFileStore store;
	private final List<IPhoto> photos = new ArrayList<IPhoto>();
	
	private IPhotoCatalogUpdateStatistics updateStatistics;
	
	public PhotoCatalog(IFileStore fileStore) {
		this.store = fileStore;
		this.uri = fileStore.toURI();
	}
	
	public PhotoCatalog(URI uri) throws CoreException {
		this.uri = uri;
		store = EFS.getStore(uri);
		update();
	}
	
	public PhotoCatalog(IPath path) throws CoreException {
		this(toURI(path));
	}
	
	private static final URI toURI(IPath path) throws CoreException {
		try {
			return new URI("file", path.toOSString(), null); //$NON-NLS-1$
		} catch (URISyntaxException e) {
			String message = MessageFormat.format("Unable to create photo catalog from path {0}", path.toOSString());
			throw new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, message, e));
		}
	}
	
	@Override
	public IFileStore getFileStore() {
		return store;
	}
	
	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public boolean isAvailable() {
		return store.fetchInfo().exists();
	}

	@Override
	public List<IPhoto> getPhotos() {
		return Collections.unmodifiableList(photos);
	}

	@Override
	public synchronized void update() {
		updateStatistics = new PhotoCatalogUpdateStatistics();
		photos.clear();
		try {
			IFileStore[] childStores = store.childStores(EFS.NONE, null);
			addPhotos(childStores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addPhotos(IFileStore[] fileStores) throws CoreException {
		for (IFileStore fileStore : fileStores) {
			IFileInfo fileInfo = fileStore.fetchInfo();
			if (fileInfo.exists()) {
				if (fileInfo.isDirectory()) {
					addPhotos(fileStore.childStores(EFS.NONE, null));
				} else {
					IPhotoFactoryDescriptor pfd = PhotoPlugin.getFactoryRegistry().findFactoryFor(fileStore);
					if (pfd != null) {
						IPhoto photo = pfd.getFactory().createPhoto(fileStore, this);
						photos.add(photo);
					}
				}
			}
		}
	}
	
	@Override
	public IPhotoCatalogUpdateStatistics getUpdateStatistics() {
		return updateStatistics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		if (!(obj instanceof PhotoCatalog)) {
			return false;
		}
		PhotoCatalog other = (PhotoCatalog) obj;
		if (uri == null) {
			if (other.uri != null) {
				return false;
			}
		} else if (!uri.equals(other.uri)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoCatalog [uri="); //$NON-NLS-1$
		builder.append(uri);
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}
	
	

}
