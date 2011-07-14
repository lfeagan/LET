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

import org.eclipse.core.runtime.Assert;

/**
 * A string splitter that splits an input string at any character from a
 * character class.
 */
public class StringSplitAtCharacterClass implements IStringSplitter {
	
	private final String characterClass;
	private final Pattern pattern;
	
	public StringSplitAtCharacterClass(final String characterClass) {
		Assert.isNotNull(characterClass, "characterClass"); //$NON-NLS-1$
		this.characterClass = characterClass;
		this.pattern = Pattern.compile("[" + characterClass + "]+"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public String getCharacterClass() {
		return characterClass;
	}
	
	@Override
	public String[] split(final String input) {
		Assert.isNotNull(input, "input"); //$NON-NLS-1$
		return pattern.split(input);
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<StringSplitAtCharacterClass characterClass=\""); //$NON-NLS-1$
		sb.append(characterClass);
		sb.append("\"/>"); //$NON-NLS-1$
		return sb.toString();
	}

}
