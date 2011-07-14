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

import org.eclipse.core.runtime.Assert;

/**
 * A string splitter that attempts to automatically determine the delimiter in
 * use by searching for various common delimiters. At present it searches for
 * comma, colon, and then spaces, in that order.
 */
public class StringSplitAuto implements IStringSplitter {

	@Override
	public String[] split(final String input) {
		InputDelimiter delimiterType = determineDelimiterType(input);
		switch (delimiterType) {
		case COMMA:
		case COLON:
		case SPACE:
			return input.split(delimiterType.getDelimiter());
		case EMPTY_INPUT:
			return new String[0];
		case NONE:
		default:
			return new String[] { input };
		}
	}

	private enum InputDelimiter {
		COMMA(","), //$NON-NLS-1$
		COLON(":"), //$NON-NLS-1$
		SPACE(" "), //$NON-NLS-1$
		NONE(""), //$NON-NLS-1$
		EMPTY_INPUT(""); //$NON-NLS-1$
		
		private final String delimiter;
		private InputDelimiter(String delimiter) {
			this.delimiter = delimiter;
		}
		
		public final String getDelimiter() {
			return delimiter;
		}
	}

	private static InputDelimiter determineDelimiterType(final String input) {
		if (input == null || input.equals(InputDelimiter.EMPTY_INPUT)) {
			return InputDelimiter.EMPTY_INPUT;
		} else if(input.contains(InputDelimiter.COMMA.getDelimiter())){
			return InputDelimiter.COMMA;
		} else if(input.contains(InputDelimiter.COLON.getDelimiter())){
			return InputDelimiter.COLON;
		} else if(input.contains(InputDelimiter.SPACE.getDelimiter())){
			return InputDelimiter.SPACE;
		} else {
			return InputDelimiter.NONE;
		}
	}
	
	@Override
	public String toString() {
		return "<StringSplitAuto/>"; //$NON-NLS-1$
	}

}
