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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import net.vectorcomputing.status.IStatusProvider;
import net.vectorcomputing.status.IStatusPublisher;
import net.vectorcomputing.status.forwarders.CurrentStatusForwarder;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class GroupStatusPublisherAndProvider extends GroupStatusPublisher implements IStatusProvider {

	private static final int SEVERITY_ORDERED_STATUSES[] = { IStatus.ERROR, IStatus.WARNING, IStatus.INFO, IStatus.CANCEL, IStatus.OK };

	private final IStatusProvider groupStatusProvider = new GroupStatusProvider();
	
	public GroupStatusPublisherAndProvider() {
		forwarder = new CurrentStatusForwarder(groupStatusProvider);
	}
	
	@Override
	public boolean addStatusPublisher(IStatusPublisher publisher) {
		if (publisher instanceof IStatusProvider) {
			return super.addStatusPublisher(publisher);
		} else {
			throw new IllegalArgumentException("specified IStatusPublisher is not an IStatusProvider");
		}
	}
		
	public Set<IStatusProvider> getStatusProviders() {
		Set<IStatusProvider> providers = new HashSet<IStatusProvider>(statusPublishers.size());
		for (IStatusPublisher element : statusPublishers) {
			providers.add((IStatusProvider) element);
		}
		return providers;
	}
	
	@Override
	public IStatus getStatus() {
		return groupStatusProvider.getStatus();
	}
	
	private class GroupStatusProvider implements IStatusProvider {
		/**
		 * Retrieve the status of the field with the highest priority error.
		 * @return
		 * @see #getHighestPriorityError()
		 */
		@Override
		public IStatus getStatus() {
			List<IStatus> allStatuses = getAllPublishersStatus();
			
			for (int severity : SEVERITY_ORDERED_STATUSES) {
				List<IStatus> errors = extractStatusesWithSeverity(allStatuses, severity);
				if (errors.size() > 0) {
					return getHighestPriorityStatus(errors);
				}
			}
			
			if (allStatuses.size() > 0) {
				throw new RuntimeException("No status matched any severity being searched for even though all severities are defined");
			} else {
				return Status.OK_STATUS;
			}
		}
		
		private List<IStatus> extractStatusesWithSeverity(List<IStatus> statuses, int severity) {
			List<IStatus> errors = new ArrayList<IStatus>();
			for (IStatus status : statuses) {
				if (status.getSeverity() == severity) {
					errors.add(status);
				}
			}
			return errors;
		}
		
		private IStatus getHighestPriorityStatus(List<IStatus> statuses) {
			TreeMap<Integer, IStatus> priorities = new TreeMap<Integer, IStatus>();

			for (IStatus status : statuses) {
				priorities.put(extractPriority(status), status);
			}

			return priorities.firstEntry().getValue();
		}
		
		private Integer extractPriority(IStatus status) {
			return Integer.valueOf(status.getCode());
		}
		
		/**
		 * Retrieve the statuses of all the fields in that report status
		 * @return
		 */
		public List<IStatus> getAllPublishersStatus() {
			List<IStatus> statuses = new ArrayList<IStatus>(statusPublishers.size());
			for (IStatusProvider statusProvider : getStatusProviders()) {
				statuses.add(statusProvider.getStatus());
			}
			return statuses;
		}
	}

}
