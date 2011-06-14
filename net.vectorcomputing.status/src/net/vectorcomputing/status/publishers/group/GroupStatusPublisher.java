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
package net.vectorcomputing.status.publishers.group;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.vectorcomputing.status.IGroupStatusPublisher;
import net.vectorcomputing.status.IStatusChangeEvent;
import net.vectorcomputing.status.IStatusListener;
import net.vectorcomputing.status.IStatusPublisher;
import net.vectorcomputing.status.forwarders.AllStatusForwarder;
import net.vectorcomputing.status.forwarders.IStatusForwarder;

public class GroupStatusPublisher implements IGroupStatusPublisher {
	
	protected IStatusForwarder forwarder;
	protected final Set<IStatusPublisher> statusPublishers = Collections.synchronizedSet(new HashSet<IStatusPublisher>());
	
	public GroupStatusPublisher() {
		forwarder = new AllStatusForwarder();
	}
	
	@Override
	public boolean addStatusPublisher(IStatusPublisher publisher) {
		boolean wasAdded = statusPublishers.add(publisher);
		if (wasAdded) {
			publisher.addStatusListener(forwarder);
		}
		return wasAdded;
	}
	
	@Override
	public boolean removeStatusPublisher(IStatusPublisher publisher) {
		boolean wasRemoved = statusPublishers.remove(publisher);
		if (wasRemoved) {
			publisher.removeStatusListener(forwarder);
		}
		return wasRemoved;
	}

	@Override
	public Set<IStatusPublisher> getStatusPublishers() {
		return Collections.unmodifiableSet(statusPublishers);
	}
	
	@Override
	public void addStatusListener(IStatusListener listener) {
		forwarder.addStatusListener(listener);
	}

	@Override
	public void removeStatusListener(IStatusListener listener) {
		forwarder.removeStatusListener(listener);
	}

	@Override
	public List<IStatusListener> getStatusListeners() {
		return forwarder.getStatusListeners();
	}
	
	protected void publishStatusChangedEvent(IStatusChangeEvent<?> statusChangedEvent) {
		forwarder.publishStatusChangeEvent(statusChangedEvent);
	}
}
