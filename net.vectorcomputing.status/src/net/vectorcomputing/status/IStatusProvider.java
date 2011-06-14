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

import org.eclipse.core.runtime.IStatus;

/**
 * <p>
 * Interface for an object that provides status information.
 * </p>
 * <p>
 * When implemented in an MVC system, this interface is typically implemented by
 * all model classes. Controller classes will generally prefer to implement the
 * status publisher interface so that listeners can be sent status change
 * events. While view classes do not implement this interface, they depend on it
 * to query the model for status information.
 * </p>
 */
public interface IStatusProvider {

	/**
	 * Returns the current status. Priority should be ordered:
	 * <ol>
	 * <li>IStatus.ERROR</li>
	 * <li>IStatus.WARNING</li>
	 * <li>IStatus.INFO</li>
	 * <li>IStatus.OK</li>
	 * </ol>
	 * 
	 * @return the current status of this object
	 */
	public abstract IStatus getStatus();
	
}
