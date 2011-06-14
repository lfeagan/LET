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


public interface IPhotoFactoryDescriptor {

	static final String ATTR_ID = "id"; //$NON-NLS-1$
	static final String ATTR_NAME = "name"; //$NON-NLS-1$
	static final String ATTR_CLASS = "class"; //$NON-NLS-1$
	
	public String getId();
	
	public String getName();
	
	public IPhotoFactory getFactory();
	
	@Override
	public boolean equals(Object obj);
	
	@Override
	public int hashCode();
	
}
