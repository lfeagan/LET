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

import java.util.Collection;
import java.util.Collections;

import net.vectorcomputing.photo.catalog.IPhotoCatalogs;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PhotoCatalogsContentProvider implements ITreeContentProvider {

	private Object[] EMPTY_ARRAY = Collections.EMPTY_LIST.toArray();

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof Collection<?>) {
			return ((Collection<?>)inputElement).toArray();
		} else if (inputElement instanceof IPhotoCatalogs) {
			return ((IPhotoCatalogs) inputElement).getCatalogs().toArray();
		} else {
			return null;
		}
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}

}
