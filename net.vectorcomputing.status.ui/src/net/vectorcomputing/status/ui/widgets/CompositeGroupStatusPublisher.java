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
package net.vectorcomputing.status.ui.widgets;

import java.util.List;
import java.util.Set;

import net.vectorcomputing.status.IGroupStatusPublisher;
import net.vectorcomputing.status.IStatusListener;
import net.vectorcomputing.status.IStatusProvider;
import net.vectorcomputing.status.IStatusPublisher;
import net.vectorcomputing.status.publishers.group.GroupStatusPublisherAndProvider;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Composite;

public class CompositeGroupStatusPublisher extends Composite implements IGroupStatusPublisher, IStatusProvider {

	protected final GroupStatusPublisherAndProvider groupStatusPublisher = new GroupStatusPublisherAndProvider();

	public CompositeGroupStatusPublisher(Composite parent, int style) {
		super(parent, style);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	@Override
	public void addStatusListener(IStatusListener listener) {
		groupStatusPublisher.addStatusListener(listener);
	}

	@Override
	public void removeStatusListener(IStatusListener listener) {
		groupStatusPublisher.removeStatusListener(listener);
	}

	@Override
	public List<IStatusListener> getStatusListeners() {
		return groupStatusPublisher.getStatusListeners();
	}

	@Override
	public boolean addStatusPublisher(IStatusPublisher publisher) {
		return groupStatusPublisher.addStatusPublisher(publisher);
	}

	@Override
	public boolean removeStatusPublisher(IStatusPublisher publisher) {
		return groupStatusPublisher.removeStatusPublisher(publisher);
	}

	@Override
	public Set<IStatusPublisher> getStatusPublishers() {
		return groupStatusPublisher.getStatusPublishers();
	}

	@Override
	public IStatus getStatus() {
		return groupStatusPublisher.getStatus();
	}

}
