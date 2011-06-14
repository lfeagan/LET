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

import java.util.List;

/**
 * <p>
 * Interface implemented by classes that publish status change events to set of
 * registered status listeners. The interface only specifies methods for
 * registering, unregistering and listing the set of objects that wish to be
 * informed of the changes in the status of the object implementing this
 * interface.
 * </p>
 * <p>
 * When implemented in an MVC system, this interface is typically implemented by
 * controller classes. Model classes may also implement this interface. While
 * view classes do not implement this interface, they depend on this interface
 * to receive notification of changes in the status that require notification to
 * the user, typically by a status line or icon change.
 * </p>
 */
public interface IStatusPublisher {

	/**
	 * Registers a status listener.
	 * 
	 * @param listener
	 *            the status listener to be added to the set of status listeners
	 * @return <code>true</code> if the set of status listeners changed as a
	 *         result of invoking this method.
	 */
	public abstract void addStatusListener(IStatusListener listener);

	/**
	 * Unregisters a status listener.
	 * 
	 * @param listener
	 *            the status listener to remove from the set of registered
	 *            status listeners
	 * @return <code>true</code> if the set of status listeners is changed as a
	 *         result of invoking this method
	 */
	public abstract void removeStatusListener(IStatusListener listener);

	/**
	 * Returns the set of current status listeners.
	 * 
	 * @return the set of current status listeners
	 */
	public abstract List<IStatusListener> getStatusListeners();

}
