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

import net.vectorcomputing.status.IStatusChangeEvent;
import net.vectorcomputing.status.IStatusPublisher;

/**
 * Interface used for publishing status change events to a group of listeners.
 */
public interface IStatusListenerGroup extends IStatusPublisher {

	/**
	 * Publishes a status change event to the entire listener group
	 * 
	 * @param event the status change event to publish
	 */
	public void publishStatusChangeEvent(IStatusChangeEvent<?> event);
	
}
