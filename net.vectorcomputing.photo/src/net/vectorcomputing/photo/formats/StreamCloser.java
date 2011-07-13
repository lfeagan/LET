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
package net.vectorcomputing.photo.formats;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.vectorcomputing.photo.PhotoMessages;
import net.vectorcomputing.photo.PhotoPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public final class StreamCloser {

	private StreamCloser() { }
	
	public static final void closeWithoutExceptions(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// do nothing
			}			
		}
	}
	
	public static final void closeWithoutExceptions(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				// do nothing
			}
		}
	}	

	public static final void closeWithCoreException(InputStream is) throws CoreException {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, PhotoMessages.ExceptionWhileClosingInputStream, e));
			}
		}
	}

	public static final void closeWithCoreException(OutputStream os) throws CoreException {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, PhotoMessages.ExceptionWhileClosingOutputStream, e));
			}
		}
	}
	
}
