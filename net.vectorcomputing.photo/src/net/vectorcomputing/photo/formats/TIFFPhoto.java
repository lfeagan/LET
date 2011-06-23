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
package net.vectorcomputing.photo.formats;

import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.internal.library.AbstractPhoto;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.ImageData;

public class TIFFPhoto extends AbstractPhoto {

	private ImageData imageData;

	public TIFFPhoto(IFileStore fileStore, IPhotoCatalog catalog) {
		super(fileStore, catalog);
	}
	
	@Override
	public ImageData getImageData() throws CoreException {
		if (imageData != null) {
			imageData = EclipseSupportedImageDataLoader.read(fileStore);
		}
		return imageData;
	}
	
}
