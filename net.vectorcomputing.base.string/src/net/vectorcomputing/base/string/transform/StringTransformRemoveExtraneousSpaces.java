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
 * Removes all leading and trailing whitespace and reduces multiple spaces
 * between words to a single space.
 */
public class StringTransformRemoveExtraneousSpaces implements IStringTransformer {

	private static final StringTransformerPipeline pipeline = new StringTransformerPipeline(
			new StringTransformRemoveLeadingSpaces(), 
			new StringTransformRemoveDuplicateWhitepaceBetweenWords(), 
			new StringTransformRemoveTrailingSpaces());
	
	/**
	 * @see StringTransformerPipeline
	 */
	@Override
	public String transform(String input) {
		return pipeline.transform(input);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<"); //$NON-NLS-1$
		sb.append(StringTransformRemoveExtraneousSpaces.class.getSimpleName());
		sb.append("/>"); //$NON-NLS-1$
		return sb.toString();
	}

}
