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

import java.util.Calendar;

import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.catalog.IPhotoCatalogUpdateStatistics;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class PhotoCatalogsCellLabelProvider extends CellLabelProvider {

	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		if (element instanceof IPhotoCatalog){
			IPhotoCatalog catalog = (IPhotoCatalog) element;
			int index = cell.getColumnIndex();
			switch (index){
			case 0:
				cell.setText(catalog.getFileStore().getName());
				break;
			case 1:
				cell.setText(Integer.toString(catalog.getPhotos().size()));
				break;
			case 2:
				IPhotoCatalogUpdateStatistics stats = catalog.getUpdateStatistics();
				if (stats != null) {
					Calendar endTime = stats.getEndTime();
					if (endTime != null) {
						cell.setText(endTime.toString());
					}
				}
				break;
			default:
				break;
			}
		}
	}
	
}
