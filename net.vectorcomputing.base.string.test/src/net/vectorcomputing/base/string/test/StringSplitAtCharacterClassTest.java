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
