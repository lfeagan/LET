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
package net.vectorcomputing.base.string.transform;

import net.vectorcomputing.base.Assert;

/**
 * A string transformer that encloses a input string in single quotes (').
 */
public class StringTransformEncloseInSingleQuotes implements IStringTransformer {

	/**
	 * @return a new string with the input string enclosed in single quotes
	 */
	@Override
	public String transform(final String input) {
		Assert.isNotNull(input, "input"); //$NON-NLS-1$
		final StringBuilder sb = new StringBuilder();
		sb.append('\'');
		sb.append(input);
		sb.append('\'');
		return sb.toString();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<"); //$NON-NLS-1$
		sb.append(StringTransformEncloseInSingleQuotes.class.getSimpleName());
		sb.append("/>"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
