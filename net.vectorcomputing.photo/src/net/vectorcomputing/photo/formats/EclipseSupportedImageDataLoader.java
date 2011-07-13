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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.MessageFormat;

import net.vectorcomputing.photo.PhotoMessages;
import net.vectorcomputing.photo.PhotoPlugin;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.ImageData;

public final class EclipseSupportedImageDataLoader {
	
	public static final ImageData read(IFileStore fileStore) throws CoreException {
		InputStream is = null;
		BufferedInputStream bis = null;
		try {
			is = fileStore.openInputStream(0, null);
			bis = new BufferedInputStream(is, 2<<13);
			return new ImageData(bis);
		} catch (Exception e) {
			final String message = MessageFormat.format(PhotoMessages.UnableToReadImageData, fileStore.getName());
			throw new CoreException(new Status(IStatus.ERROR, PhotoPlugin.PLUGIN_ID, message, e));
		} finally {
			StreamCloser.closeWithoutExceptions(bis);
			StreamCloser.closeWithoutExceptions(is);
		}
	}
	
}
