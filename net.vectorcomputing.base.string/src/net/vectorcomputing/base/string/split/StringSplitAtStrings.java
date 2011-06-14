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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A string splitter that makes multiple passes over an input string with an
 * order list of delimiters and splits it up at each of the delimiters.
 */
public class StringSplitAtStrings implements IStringSplitter {

	private final String[] delimiters;
	
	/**
	 * 
	 * @param strings the strings to split an input string at
	 */
	public StringSplitAtStrings(String...strings) {
		this.delimiters = strings;
	}

	@Override
	public String[] split(String input) {
		List<String> result = Arrays.asList(new String[] {input});
		for (String delimiter : delimiters) {
			result = split(result, delimiter);
		}
		return result.toArray(new String[result.size()]);
	}

	private static final List<String> split(List<String> unsplitStrings, String currentDelimiter) {
		final List<String> splitStrings = new ArrayList<String>(unsplitStrings.size());
		for (String unsplitString : unsplitStrings) {
			final String[] ssa = unsplitString.split(currentDelimiter);
			splitStrings.addAll(Arrays.asList(ssa));
		}
		return splitStrings;
	}

}
