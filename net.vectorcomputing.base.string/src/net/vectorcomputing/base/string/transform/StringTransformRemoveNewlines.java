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

/**
 * A string transformer that removes all newline characters from an input string.
 */
public class StringTransformRemoveNewlines implements IStringTransformer {

	/**
	 * @return a new string with all newline characters removed
	 */
	@Override
	public String transform(String input) {
		return input.replaceAll("^\\n", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<"); //$NON-NLS-1$
		sb.append(StringTransformRemoveNewlines.class.getSimpleName());
		sb.append("/>"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
