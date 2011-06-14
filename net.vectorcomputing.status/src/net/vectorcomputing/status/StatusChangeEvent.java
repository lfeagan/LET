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

public class StatusChangeEvent implements IStatusChangeEvent<IStatus> {
	
	private final IStatus status;
	private final Object source;
	
	public StatusChangeEvent(IStatus status) {
		this.status = status;
		this.source = Thread.currentThread().getStackTrace()[1];
	}
	
	public StatusChangeEvent(IStatus status, Object source) {
		this.status = status;
		this.source = source;
	}
	
	@Override
	public IStatus getStatus() {
		return status;
	}

	@Override
	public Object getSource() {
		return source;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getClass().getName());
		sb.append(" [source=");; //$NON-NLS-1$
		sb.append(this.source);
		sb.append("]"); //$NON-NLS-1$
		return sb.toString();
	}

}
