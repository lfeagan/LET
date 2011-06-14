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
package net.vectorcomputing.photo.ui.widgets;

import java.util.List;

import net.vectorcomputing.photo.PhotoPlugin;
import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.catalog.IPhotoCatalogEvent;
import net.vectorcomputing.photo.catalog.IPhotoCatalogEventListener;
import net.vectorcomputing.photo.catalog.IPhotoCatalogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class PhotoCatalogsCombo extends Combo implements IPhotoCatalogEventListener {

	private final IPhotoCatalogs catalogs = PhotoPlugin.getCatalogs();
	private List<IPhotoCatalog> photoCatalogs;
	
	public PhotoCatalogsCombo(Composite parent, int style) {
		super(parent, style | SWT.READ_ONLY);
		catalogs.addListener(this);
		populate();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents sub-classing of SWT components
	}
	
	private final void populate() {
		removeAll();
		photoCatalogs = catalogs.getCatalogs();
		for (IPhotoCatalog catalog : photoCatalogs) {
			add(catalog.getFileStore().getName());
		}
	}
	
	public IPhotoCatalog getSelectedCatalog() {
		return photoCatalogs.get(getSelectionIndex());
	}

	@Override
	public void dispose() {
		catalogs.removeListener(this);
		super.dispose();
	}

	@Override
	public void handle(IPhotoCatalogEvent event) {
		this.getDisplay().asyncExec(new CatalogEventHandler(event));
	}
	
	private final class CatalogEventHandler implements Runnable {

		private final IPhotoCatalogEvent event;
		
		public CatalogEventHandler(IPhotoCatalogEvent event) {
			this.event = event;
		}
		
		@Override
		public void run() {
			populate();			
		}
		
	}
	
}
