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
package net.vectorcomputing.base.string;

import java.util.ArrayList;

/**
 * An indent string cache provides a way of caching indent strings at various
 * levels (depths). An indent string cache is typically used when formatting
 * output for presentation to a user to help understand a data structure with
 * parent-child relationships
 * 
 * The string for a level is built only once and the cached value is used for
 * all subsequent requests to get that level's indent string.
 */
public class IndentStringCache {
	
	private final String indentString;
	private final ArrayList<String> cache = new ArrayList<String>();
	
	public IndentStringCache(String indentString) {
		this.indentString = indentString;
	}

	public String getIndentString(int level) {
		if (level+1 > cache.size()) {
			increaseCachedLevels(level);
		}
		return cache.get(level);
	}
	
	private String createLevel(int level) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; ++i) {
			sb.append(indentString);
		}

		return sb.toString();
	}
	
	private void increaseCachedLevels(int desiredLevel) {
		int desiredSize = desiredLevel+1;
		for (int i=cache.size(); i < desiredSize; ++i) {
			cache.add(createLevel(i));
		}
	}

}
