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
package net.vectorcomputing.photo.factory;

import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.photo.PhotoPlugin;
import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.library.IPhoto;

import org.eclipse.core.filesystem.IFileStore;

public interface IPhotoFactory {
	
	public static final String POINT_ID = PhotoPlugin.PLUGIN_ID + ".photoFactory"; //$NON-NLS-1$

	public IStringConstraint fileNameConstraint();
	
	public boolean accepts(IFileStore fileStore);
	
	public IPhoto createPhoto(IFileStore fileStore, IPhotoCatalog catalog);

	@Override
	public boolean equals(Object obj);
	
	@Override
	public int hashCode();

}
