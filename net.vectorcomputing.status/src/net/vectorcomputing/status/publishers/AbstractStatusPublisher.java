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
package net.vectorcomputing.status.publishers;

import java.util.List;

import net.vectorcomputing.status.IStatusChangeEvent;
import net.vectorcomputing.status.IStatusListener;
import net.vectorcomputing.status.IStatusPublisher;
import net.vectorcomputing.status.forwarders.AllStatusForwarder;
import net.vectorcomputing.status.forwarders.IStatusForwarder;
import net.vectorcomputing.status.listeners.IStatusListenerGroup;

import org.eclipse.core.runtime.Status;

public abstract class AbstractStatusPublisher implements IStatusPublisher {
	private final IStatusListenerGroup listeners;
	
	public AbstractStatusPublisher() {
		this.listeners = new AllStatusForwarder();
	}
	
	public AbstractStatusPublisher(IStatusForwarder statusForwarder) {
		this.listeners = statusForwarder;
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
	
	/**
	 * Publish the specified status to all registered {@link IStatusListener}s.
	 * @param event  the {@link Status} to be published
	 */
	protected final void publishStatusChangedEvent(IStatusChangeEvent<?> event) {
		listeners.publishStatusChangeEvent(event);
	}
}
