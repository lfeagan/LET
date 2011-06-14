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
package net.vectorcomputing.status;

import java.util.Set;

/**
 * Interface for grouping together multiple status publishers to have them
 * behave as a unified collection.
 * 
 * If no status publishers are added to the collection of publishers, IStatus.OK
 * will always be the status.
 * 
 */
public interface IGroupStatusPublisher extends IStatusPublisher {

	/**
	 * Adds a status publisher to the list of the publishers subscribed to.
	 * 
	 * @param publisher
	 *            the publisher to add to the set of status publishers
	 * @return <code>true</code> if the set of status publishers changed
	 */
	public boolean addStatusPublisher(IStatusPublisher publisher);

	/**
	 * Removes a status publisher from the list of publishers subscribed to.
	 * 
	 * @param publisher
	 *            the publisher to remove from the test of status publishers
	 * @return <code>true</code> if the set of status publishers changed
	 */
	public boolean removeStatusPublisher(IStatusPublisher publisher);

	/**
	 * Returns the set of status publishers being listened to.
	 * 
	 * @return the set of status publishers being listened to
	 */
	public Set<IStatusPublisher> getStatusPublishers();

}
