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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.catalog.IPhotoCatalogEvent;
import net.vectorcomputing.photo.catalog.IPhotoCatalogEventListener;
import net.vectorcomputing.photo.catalog.IPhotoCatalogs;
import net.vectorcomputing.photo.catalog.PhotoCatalogEventType;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.ListenerList;

public class PhotoCatalogs implements IPhotoCatalogs {

	private final List<IPhotoCatalog> catalogs = new ArrayList<IPhotoCatalog>();
	
	private final ListenerList listeners = new ListenerList();
	
	@Override
	public IPhotoCatalog create(IFileStore fileStore) {
		IPhotoCatalog catalog = new PhotoCatalog(fileStore);
		catalogs.add(catalog);
		return catalog;
	}
	
	@Override
	public IPhotoCatalog create(IPath path) throws CoreException {
		IPhotoCatalog catalog = new PhotoCatalog(path);
		addCatalog(catalog);
		return catalog;
	}
	
	@Override
	public boolean addCatalog(IPhotoCatalog catalog) {
		boolean returnValue = catalogs.add(catalog);
		publish(new PhotoCatalogEvent(PhotoCatalogEventType.ADD_CATALOG, catalog));
		return returnValue;
	}

	@Override
	public boolean removeCatalog(IPhotoCatalog catalog) {
		boolean returnValue = catalogs.remove(catalog);
		publish(new PhotoCatalogEvent(PhotoCatalogEventType.REMOVE_CATALOG, catalog));
		return returnValue;
	}

	@Override
	public List<IPhotoCatalog> getCatalogs() {
		return Collections.unmodifiableList(catalogs);
	}
	
	protected void publish(IPhotoCatalogEvent event) {
		for (Object listener : listeners.getListeners()) {
			((IPhotoCatalogEventListener) listener).handle(event);
		}
	}

	@Override
	public void addListener(IPhotoCatalogEventListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IPhotoCatalogEventListener listener) {
		listeners.remove(listener);
	}

}
