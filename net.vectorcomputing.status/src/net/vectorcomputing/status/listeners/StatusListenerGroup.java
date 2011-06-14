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

import java.util.Collections;
import java.util.List;

import net.vectorcomputing.status.IStatusChangeEvent;
import net.vectorcomputing.status.IStatusListener;


public class StatusListenerGroup implements IStatusListenerGroup {
	
	protected final StatusListeners listeners = new StatusListeners();
	
	@Override
	public void publishStatusChangeEvent(IStatusChangeEvent<?> event) {
		for (IStatusListener listener : listeners) {
			listener.statusChanged(event);
		}
	}
	
	@Override
	public void addStatusListener(IStatusListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeStatusListener(IStatusListener listener) {
		listeners.remove(listener);
	}

	@Override
	public List<IStatusListener> getStatusListeners() {
		return Collections.unmodifiableList(listeners.getStatusListenersList());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatusListenerGroup [statusListeners="); //$NON-NLS-1$
		builder.append(listeners.getStatusListenersList());
		builder.append("]"); //$NON-NLS-1$
		return builder.toString();
	}

}
