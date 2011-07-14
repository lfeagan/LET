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

import org.eclipse.core.runtime.Assert;

/**
 * Chains string transformers together to create a transformation pipeline.
 */
public class StringTransformerPipeline implements IStringTransformer {

	private final IStringTransformer[] stringTransformers;
	
	public StringTransformerPipeline(final IStringTransformer...stringTransformers) {
		Assert.isNotNull(stringTransformers, "stringTransformers"); //$NON-NLS-1$
		this.stringTransformers = stringTransformers;
	}
	
	@Override
	public String transform(final String input) {
		Assert.isNotNull(input, "input"); //$NON-NLS-1$
		String target = input;
		for (IStringTransformer stringTransformer : stringTransformers) {
			target = stringTransformer.transform(target);
		}
		return target;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<StringTransformerPipeline>"); //$NON-NLS-1$
		for (IStringTransformer transformer : stringTransformers) {
			sb.append(transformer.toString());
		}
		sb.append("</StringTransformerPipeline>"); //$NON-NLS-1$
		return sb.toString();
	}

}
