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
package net.vectorcomputing.status.forwarders;

import java.util.List;

import net.vectorcomputing.status.IStatusChangeEvent;
import net.vectorcomputing.status.IStatusListener;
import net.vectorcomputing.status.listeners.IStatusListenerGroup;
import net.vectorcomputing.status.listeners.StatusListenerGroup;

public abstract class AbstractStatusForwarder implements IStatusForwarder {

	private final IStatusListenerGroup listeners = new StatusListenerGroup();
	
	@Override
	public void publishStatusChangeEvent(IStatusChangeEvent<?> event) {
		listeners.publishStatusChangeEvent(event);
	}
	
	@Override
	public void addStatusListener(IStatusListener listener) {
		listeners.addStatusListener(listener);
	}
	
	@Override
	public void removeStatusListener(IStatusListener listener) {
		listeners.removeStatusListener(listener);
	}
	
	@Override
	public List<IStatusListener> getStatusListeners() {
		return listeners.getStatusListeners();
	}
	
}
