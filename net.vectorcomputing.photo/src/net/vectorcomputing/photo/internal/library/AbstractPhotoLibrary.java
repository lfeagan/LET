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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vectorcomputing.photo.library.IPhotoBook;
import net.vectorcomputing.photo.library.IPhotoLibrary;

public abstract class AbstractPhotoLibrary implements IPhotoLibrary {

	private final List<IPhotoBook> photoBooks = new ArrayList<IPhotoBook>();
	
	public boolean removeBook(IPhotoBook photoBook) {
		// unregister as listener to book metadata changes
		return photoBooks.remove(photoBook);
	}
	
	@Override
	public List<IPhotoBook> getBooks() {
		return Collections.unmodifiableList(photoBooks);
	}

	@Override
	public IPhotoBook getBook(String name) {
		IPhotoBook book = getExistingBook(name);
		if (book == null) {
			book = new PhotoBook(name, this);
		}
		return book;
	}
	
	protected IPhotoBook getExistingBook(String name) {
		for (IPhotoBook photoBook: photoBooks) {
			if (photoBook.getName().equals(name)) {
				return photoBook;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((photoBooks == null) ? 0 : photoBooks.hashCode());
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
		if (!(obj instanceof AbstractPhotoLibrary)) {
			return false;
		}
		AbstractPhotoLibrary other = (AbstractPhotoLibrary) obj;
		if (photoBooks == null) {
			if (other.photoBooks != null) {
				return false;
			}
		} else if (!photoBooks.equals(other.photoBooks)) {
			return false;
		}
		return true;
	}

}
