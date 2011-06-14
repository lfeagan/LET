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

import net.vectorcomputing.base.string.constraint.IStringConstraint;
import net.vectorcomputing.status.IStatusProvider;
import net.vectorcomputing.status.StatusChangeEvent;
import net.vectorcomputing.status.ui.StatusUiPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

public class ValidatedText extends AbstractTextStatusPublisher implements IStatusProvider {

	private final IStringConstraint constraint;
	
	private static final IStatus OK_STATUS = new Status(IStatus.OK, StatusUiPlugin.PLUGIN_ID, SWT.OK, "text satisfies constraint", null);
	
	public ValidatedText(Composite parent, int style, IStringConstraint constraint) {
		super(parent, style);
		this.constraint = constraint;
		addModifyListener(new ModifyListener() {	
			@Override
			public void modifyText(ModifyEvent e) {
				listeners.publishStatusChangeEvent(new StatusChangeEvent(getStatus(), this));
			}
		});
	}
	
	@Override
	public IStatus getStatus() {
		if (constraint.satisfiedBy(getText())) {
			return OK_STATUS;
		} else {
			return new Status(IStatus.ERROR, StatusUiPlugin.PLUGIN_ID, SWT.ERROR, "text does not satisfy constraint", null);
		}
	}
	
	public IStringConstraint getConstraint() {
		return constraint;
	}
	
}
