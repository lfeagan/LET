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
package net.vectorcomputing.photo.internal.factory;

import net.vectorcomputing.photo.PhotoMessages;
import net.vectorcomputing.photo.PhotoPlugin;
import net.vectorcomputing.photo.factory.IPhotoFactory;
import net.vectorcomputing.photo.factory.IPhotoFactoryDescriptor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class PhotoFactoryDescriptor implements IPhotoFactoryDescriptor {

	private final String id;
	private final String name;
	private final IPhotoFactory factory;
	
	public PhotoFactoryDescriptor(IConfigurationElement element) throws CoreException {
		this.id = element.getAttribute(ATTR_ID);
		this.name = element.getAttribute(ATTR_NAME);
		try {
			this.factory = (IPhotoFactory) element.createExecutableExtension(ATTR_CLASS);
		} catch (ClassCastException e) {
			throw buildFactoryCreationException(e, id);
		}
	}
	
	private static final CoreException buildFactoryCreationException(Exception e, String id) {
		return new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, PhotoMessages.PhotoFactoryDescriptor_UnableToCreatePhotoFactory + id, e));
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPhotoFactory getFactory() {
		return factory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factory == null) ? 0 : factory.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof PhotoFactoryDescriptor)) {
			return false;
		}
		PhotoFactoryDescriptor other = (PhotoFactoryDescriptor) obj;
		if (factory == null) {
			if (other.factory != null) {
				return false;
			}
		} else if (!factory.equals(other.factory)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoFactoryDescriptor [id="); //$NON-NLS-1$
		builder.append(id);
		builder.append(", name="); //$NON-NLS-1$
		builder.append(name);
		builder.append(", factory="); //$NON-NLS-1$
		builder.append(factory);
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}

}
