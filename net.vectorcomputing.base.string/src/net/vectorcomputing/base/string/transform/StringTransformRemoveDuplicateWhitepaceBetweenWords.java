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

import java.util.regex.Pattern;

/**
 * A string transformer that removes duplicated whitespace characters between
 * words.
 */
public class StringTransformRemoveDuplicateWhitepaceBetweenWords implements IStringTransformer {

	/**
	 * @see Pattern
	 */
	@Override
	public String transform(String input) {
		// "\\b" matches a word boundary
		return input.replaceAll("\\b\\s{2,}\\b", " "); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(StringTransformRemoveDuplicateWhitepaceBetweenWords.class.getSimpleName());
		sb.append("/>");
		return sb.toString();
	}
	
}
