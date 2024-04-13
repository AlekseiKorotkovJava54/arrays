package telran.util.test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;


class ArraysTests {
Integer[] numbers = {100, -3, 23, 4, 8, 41, 56, -7};
String[] strings = {"abc", "lmn", "123", null, "a"};
String[] stringsMin = {"abc", "lmn", "123",  "y"};
	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}
	@Test
	void minValueTest() {
		Comparator<String> compLength = (s1, s2) -> s1.length() - s2.length();
		assertEquals("y", Arrays.min(stringsMin,
				compLength));
		Comparator<String> compNative = (s1, s2) -> s1.compareTo(s2);
		assertEquals("123", Arrays.min(stringsMin, compNative));
	}
	@Test
	void bubbleSortTest() {
		Integer [] expected = {4, 8, 56, 100, 41, 23, -3, -7};
		Integer [] numbersCopy = Arrays.copy(numbers);
         /* lambda expression example */
     	// Comparator<Integer> evenOddComp = (num1, num2) ->
        // evenOddComparator(num1, num2);
		/*************************************/
		/* method reference */
		Comparator<Integer> evenOddComp = ArraysTests::evenOddComparator;
		/*********************************************/
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);
	}
	
	@Test
	void bubbleSort1Test() {
		Integer [] expected1 = {4, 8, 56, 100, 41, 23, -3, -7};
		MyNumber [] expected = new MyNumber[numbers.length];
		for (int i = 0; i < expected1.length; i++) {
			expected[i] = new MyNumber(expected1[i]); 
		}
		MyNumber [] numbersCopy = new MyNumber[numbers.length];
		for (int i = 0; i < numbersCopy.length; i++) {
			numbersCopy[i] = new MyNumber(numbers[i]); 
		}
		Arrays.bubbleSort1(numbersCopy);
		assertArrayEquals(expected, numbersCopy);
	}
	
	static int evenOddComparator(Integer num1, Integer num2) {
		//first even numbers sorted in the ascending order
		//last odd numbers sorted in the descending order
		boolean isNum1Even = num1 % 2 == 0;
		boolean isNum2Even = num2 % 2 == 0;
		int res = 1;
		if (isNum1Even && isNum2Even) {
			res = Integer.compare(num1, num2);
		} else if (!isNum1Even && !isNum2Even) {
			res = Integer.compare(num2, num1);
		} else if (isNum1Even) {
			res = -1;
		}
		return res;
	}
	@Test
	void searchTest() {
		Integer[] expectedEven = {100, 4, 8,  56};
		assertArrayEquals(expectedEven, Arrays.search(numbers,
				a -> a % 2 == 0));
		Integer[] expectedNegative = {-3,-7};
		assertArrayEquals(expectedNegative, Arrays.search(numbers,
				a -> a < 0));
		
	}
	@Test
	void binarySearchTest() {
		Integer [] numbersSorted = {-7,-3, 4, 8, 23, 41, 56, 100};
		Integer [] numbersSorted1 = {-7,-3, 4, 8, 23, 41, 56, 100, 120};
		Comparator<Integer> compNative = (s1, s2) -> s1.compareTo(s2);
		assertEquals(2, Arrays.binarySearch(numbersSorted, 4, compNative));
		assertEquals(0, Arrays.binarySearch(numbersSorted, -7, compNative));
		assertEquals(7, Arrays.binarySearch(numbersSorted, 100, compNative));
		assertEquals(-5, Arrays.binarySearch(numbersSorted, 9, compNative));
		assertEquals(-4, Arrays.binarySearch(numbersSorted, 5, compNative));
		assertEquals(-5, Arrays.binarySearch(numbersSorted1, 9, compNative));
		assertEquals(-4, Arrays.binarySearch(numbersSorted1, 5, compNative));
	}
	 @Test
	 void removeIfTest() {
		 Integer[] expectedAr = {100, -3, 4, 8, -7};
		 assertArrayEquals(expectedAr, Arrays.removeIf(numbers,a -> (Integer.toString(a).matches("\\d\\d"))));
	 }
	 @Test
	 void addTest () {
		 Integer[] expected = {100, -3, 23, 4, 8, 41, 56, -7,150};
		 Integer[] actual = Arrays.add(numbers, 150);
		 assertArrayEquals(expected, actual);
	 }
	 @Test
	 void insertSortedTest() {
		 Integer [] numbersSorted = {-7,-3, 4, 8, 23, 41, 56, 100, 120};
		 Integer[] expected1 = {-10,-7,-3, 4, 8, 23, 41, 56, 100, 120};
		 Integer[] expected2 = {-7,-3, 4, 8, 23, 25, 41, 56, 100, 120};
		 Integer[] expected3 = {-7,-3, 4, 8, 23, 41, 56, 100, 120,150};
		 assertArrayEquals(expected1, Arrays.insertSorted(numbersSorted, -10, (a,b) -> a.compareTo(b)));
		 assertArrayEquals(expected2, Arrays.insertSorted(numbersSorted, 25, (a,b) -> a.compareTo(b)));
		 assertArrayEquals(expected3, Arrays.insertSorted(numbersSorted, 150, (a,b) -> a.compareTo(b)));
	 }
	 @Test
	 void personsSortTest () {
		 Person prs1 = new Person(123,1985);
		 Person prs2 = new Person(120,2000);
		 Person prs3 = new Person(128,1999);
		 Person [] persons = {prs1,prs2,prs3};
		 java.util.Arrays.sort(persons);
		 Person [] expected = {new Person(120,2000),new Person(123,1985),new Person(128,1999)};
		 assertArrayEquals(expected, persons);
		 Person [] expectedAge = {new Person(120,2000),new Person(128,1999),new Person(123,1985)};
		 java.util.Arrays.sort(persons, (p1,p2) -> Integer.compare(p2.birthYear, p1.birthYear));
		 assertArrayEquals(expectedAge, persons);
	 }
}
class Person implements Comparable<Person>{
	long id;
	int birthYear;
	public Person(long id, int birthYear) {
		super();
		this.id = id;
		this.birthYear = birthYear;
	}
	@Override
	public int compareTo(Person o) {
		return Long.compare(id, o.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthYear, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return birthYear == other.birthYear && id == other.id;
	}	
}
class MyNumber implements Comparable<MyNumber> {
    Integer value;

    public MyNumber(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(MyNumber o) {
    	boolean isNum1Even = value % 2 == 0;
        boolean isNum2Even = o.value % 2 == 0;
        int res = 1;
        if (isNum1Even && isNum2Even) {
            res = Integer.compare(value, o.value);
        } else if (!isNum1Even && !isNum2Even) {
            res = Integer.compare(o.value, value);
        } else if (isNum1Even) {
            res = -1;
        }
        return res;
    }

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyNumber other = (MyNumber) obj;
		return value == other.value;
	}
}
