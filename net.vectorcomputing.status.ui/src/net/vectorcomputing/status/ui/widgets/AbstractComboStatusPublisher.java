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

import net.vectorcomputing.status.IStatusListener;
import net.vectorcomputing.status.IStatusProvider;
import net.vectorcomputing.status.IStatusPublisher;
import net.vectorcomputing.status.listeners.IStatusListenerGroup;
import net.vectorcomputing.status.listeners.StatusListenerGroup;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractComboStatusPublisher extends Combo implements IStatusPublisher, IStatusProvider {

	protected final IStatusListenerGroup listeners = new StatusListenerGroup();
	
	public AbstractComboStatusPublisher(Composite parent, int style) {
		super(parent, style);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents sub-classing of SWT components
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
