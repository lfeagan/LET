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
import net.vectorcomputing.photo.catalog.PhotoCatalogEventType;

public class PhotoCatalogEvent implements IPhotoCatalogEvent {

	private final PhotoCatalogEventType eventType;
	private final List<IPhotoCatalog> catalogs;
	private final boolean isMultiCatalog;
	
	public PhotoCatalogEvent(PhotoCatalogEventType eventType) {
		this.eventType = eventType;
		this.catalogs = Collections.emptyList();
		this.isMultiCatalog = false;
	}

	public PhotoCatalogEvent(PhotoCatalogEventType eventType, IPhotoCatalog catalog) {
		this.eventType = eventType;
		this.catalogs = new ArrayList<IPhotoCatalog>(1);
		catalogs.add(catalog);
		this.isMultiCatalog = false;
	}
	
	public PhotoCatalogEvent(PhotoCatalogEventType eventType, List<IPhotoCatalog> catalogs) {
		this.eventType = eventType;
		this.catalogs = catalogs;
		this.isMultiCatalog = catalogs.size() > 1;
	}
	
	@Override
	public PhotoCatalogEventType getEventType() {
		return eventType;
	}
	
	public IPhotoCatalog getCatalog() {
		return catalogs.get(0);
	}
	
	@Override
	public List<IPhotoCatalog> getCatalogs() {
		return catalogs;
	}
	
	@Override
	public boolean isMultiCatalog() {
		return isMultiCatalog;
	}

}
