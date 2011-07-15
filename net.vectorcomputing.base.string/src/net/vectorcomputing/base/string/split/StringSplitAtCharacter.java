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

import net.vectorcomputing.base.string.Assert;

/**
 * A string splitter that splits up an input string at a particular character.
 */
public class StringSplitAtCharacter implements IStringSplitter {

	private final char characterToSplitAt;
	private final Pattern pattern;
	
	public StringSplitAtCharacter(final char characterToSplitAt) {
		Assert.isNotNull(characterToSplitAt, "characterToSplitAt"); //$NON-NLS-1$
		this.characterToSplitAt = characterToSplitAt;
		pattern = Pattern.compile("[" + characterToSplitAt + "]+"); //$NON-NLS-1$ //$NON-NLS-2$
		// reference Pattern.compile("[,\\s]+");
	}
	
	public char getCharacterToSplitAt() {
		return characterToSplitAt;
	}
	
	@Override
	public String[] split(final String input) {
		if (input == null) {
			return new String[0];
		}
		return pattern.split(input);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<StringSplitAtCharacter character=\""); //$NON-NLS-1$
		sb.append(characterToSplitAt);
		sb.append("\"/>"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
