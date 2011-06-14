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

import net.vectorcomputing.photo.library.IPhotoLibrary;

public class PhotoLibraryRoot extends AbstractPhotoLibrary implements IPhotoLibrary {

	public PhotoLibraryRoot() {
	}
	
	@Override
	public synchronized void setName(String name) {
		// TODO decide if we should throw an exception instead of doing nothing for the root photo library
	}

	@Override
	public synchronized String getName() {
		return ""; //$NON-NLS-1$
	}
	
	public String getUniqueBookName(String desiredName) {
		if (getBook(desiredName) == null) {
			return desiredName;
		} else {
			int counter = 0;
			String uniqueName;
			do {
				uniqueName = desiredName + Integer.toString(counter);
				++ counter;
			} while (getBook(uniqueName) != null);
			
			return uniqueName;
		}
	}

	@Override
	public boolean isRootLibrary() {
		return true;
	}
	
}
