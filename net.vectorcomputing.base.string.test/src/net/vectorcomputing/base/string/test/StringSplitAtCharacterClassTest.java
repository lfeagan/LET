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
package net.vectorcomputing.base.string.test;

import static org.junit.Assert.assertArrayEquals;
import net.vectorcomputing.base.string.split.IStringSplitter;
import net.vectorcomputing.base.string.split.StringSplitAtCharacterClass;

import org.junit.Test;

public class StringSplitAtCharacterClassTest {

	@Test
	public void parseKernelVersion() {
		final String kernelVersion = "2.6.24-24-generic";
		IStringSplitter splitter = new StringSplitAtCharacterClass(".-");
		final String[] reference = new String[] { "2", "6", "24", "24", "generic" };
		assertArrayEquals(reference, splitter.split(kernelVersion));
	}

}
