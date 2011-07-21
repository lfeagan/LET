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

import net.vectorcomputing.photo.library.IPhoto;
import net.vectorcomputing.photo.library.IPhotoBook;
import net.vectorcomputing.photo.library.IPhotoLibrary;

public class PhotoBook extends AbstractPhotoLibrary implements IPhotoBook {

	private String name;
	private IPhotoLibrary library;
	private final List<IPhoto> photos = new ArrayList<IPhoto>();
	
	public PhotoBook(String name, IPhotoLibrary library) {
		this.name = name;
		this.library = library;
	}

	@Override
	public IPhotoLibrary getParent() {
		return library;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean isRootLibrary() {
		return false;
	}
	
	@Override
	public boolean addPhoto(IPhoto photo) {
		return photos.add(photo);
	}
	
	@Override
	public boolean removePhoto(IPhoto photo) {
		return photos.remove(photo);
	}	

	@Override
	public List<IPhoto> getPhotos() {
		// Need a new unmofifiable list that is an aggregation of other lists without requiring copying of the sub-lists
		return Collections.unmodifiableList(photos);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((library == null) ? 0 : library.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photos == null) ? 0 : photos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) { // $codepro.audit.disable cyclomaticComplexity
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PhotoBook)) {
			return false;
		}
		PhotoBook other = (PhotoBook) obj;
		if (library == null) {
			if (other.library != null) {
				return false;
			}
		} else if (!library.equals(other.library)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (photos == null) {
			if (other.photos != null) {
				return false;
			}
		} else if (!photos.equals(other.photos)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoBook [name="); //$NON-NLS-1$
		builder.append(name);
		builder.append(", library="); //$NON-NLS-1$
		builder.append(library);
		builder.append(", isRootLibrary()="); //$NON-NLS-1$
		builder.append(isRootLibrary());
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}

}
