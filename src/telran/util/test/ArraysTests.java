package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;

class ArraysTests {
	Integer[] numbers = {100,-3,23,4,8,41,56,-7};
	String [] strings = {"abc","lmn","123",null,"a"};
	String [] stringMin = {"abc","lmn","123","y"};
	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}
	@Test
	void minTest() {
		assertEquals("y", Arrays.min(stringMin, new StringLengthComparator()));
		assertEquals("123", Arrays.min(stringMin, new StringsComparator()));
	}
}
