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

import net.vectorcomputing.photo.factory.IPhotoFactoryDescriptor;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class PhotoFactoriesCellLabelProvider extends CellLabelProvider {

	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		if (element instanceof IPhotoFactoryDescriptor){
			IPhotoFactoryDescriptor factory = (IPhotoFactoryDescriptor) element;
			int index = cell.getColumnIndex();
			switch (index){
			case 0:
				cell.setText(factory.getId());
				break;
			case 1:
				cell.setText(factory.getName());
				break;
			default:
				break;
			}
		}
	}
	
}
