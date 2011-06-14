package net.vectorcomputing.base.string.test;

import static org.junit.Assert.assertEquals;
import net.vectorcomputing.base.string.transform.IStringTransformer;
import net.vectorcomputing.base.string.transform.StringTransformContiguousWhitespaceToSingleSpace;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringTransformContiguousWhitespaceToSingleSpaceTest {

	private static IStringTransformer transformer;
	private static String reference;
	
	@BeforeClass
	public static void init() {
		transformer = new StringTransformContiguousWhitespaceToSingleSpace();
		reference = "A B C D E";
	}
	
	@AfterClass
	public static void deinit() {
		transformer = null;
	}

	@Test
	public void noTransformationNecessary() {
		assertEquals(reference, transformer.transform("A B C D E"));
	}
	
	@Test
	public void doubleSpaces() {
		assertEquals(reference, transformer.transform("A  B  C  D  E"));
	}
	
	@Test
	public void singleTabs() {
		assertEquals(reference, transformer.transform("A\tB\tC\tD\tE"));
	}

	@Test
	public void singleCarriageReturns() {
		assertEquals(reference, transformer.transform("A\rB\rC\rD\rE"));
	}

	@Test
	public void multipleVaried() {
		assertEquals(reference, transformer.transform("A \tB\r\nC\fD\r E"));
	}

}
