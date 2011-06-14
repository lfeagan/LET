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
package net.vectorcomputing.photo.ui.actions;

import net.vectorcomputing.photo.catalog.IPhotoCatalog;

import org.eclipse.jface.action.Action;

public class RemovePhotoCatalogAction extends Action {
	
	public RemovePhotoCatalogAction() {
		super("Remove Photo Catalog");
		setDescription("Add new photo catalog");
		setToolTipText("my tool tip");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
