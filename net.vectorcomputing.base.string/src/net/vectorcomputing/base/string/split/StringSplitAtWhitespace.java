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
package net.vectorcomputing.base.string.split;

import java.util.regex.Pattern;

/**
 * A string splitter that splits an input string at any contiguous block of one
 * or more whitespace characters.
 */
public class StringSplitAtWhitespace implements IStringSplitter {

	private final Pattern pattern;
	
	public StringSplitAtWhitespace() {
		pattern = Pattern.compile("\\s{1,}+"); //$NON-NLS-1$
	}
	
	@Override
	public String[] split(String input) {
		return pattern.split(input);
	}

	@Override
	public String toString() {
		return "<StringSplitAtWhitespace/>";
	}
	
}
