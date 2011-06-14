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
package net.vectorcomputing.status.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import net.vectorcomputing.status.IStatusListener;

import org.eclipse.core.runtime.ListenerList;

public class StatusListeners implements Iterable<IStatusListener>{

	private final ListenerList statusListeners = new ListenerList();

	public void add(IStatusListener listener) {
		statusListeners.add(listener);
	}
	
	public void remove(IStatusListener listener) {
		statusListeners.remove(listener);
	}
	
	public void clear() {
		statusListeners.clear();
	}
	
	public boolean isEmpty() {
		return statusListeners.isEmpty();
	}
	
	public int size() {
		return statusListeners.size();
	}
	
	public IStatusListener[] getStatusListeners() {
		return (IStatusListener[]) statusListeners.getListeners();
	}
	
	public List<IStatusListener> getStatusListenersList() {
		return Arrays.asList(getStatusListeners());
	}
	
	@Override
	public ListIterator<IStatusListener> iterator() {
		return getStatusListenersList().listIterator();
	}
	
}
