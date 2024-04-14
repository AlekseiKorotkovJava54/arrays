package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.*;

class CompanyTest {
private static final long ID1 = 123;
private static final int SALARY1 = 1000;
private static final String DEPARTMENT1 = "Development";
private static final long ID2 = 120;
private static final int SALARY2 = 2000;
private static final long ID3 = 125;
private static final int SALARY3 = 3000;
private static final String DEPARTMENT2 = "QA";
private static final long ID4 = 122;
private static final int SALARY4 = 1500;
private static final String DEPARTMENT4 = "Sales";
//FIXME there should be at least one object for all classes (WageEmployee, Manager, SalesPerson)
Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
Company company;
Employee empl4 = new Employee(ID4, SALARY4, DEPARTMENT4);
@BeforeEach
void setCompany() {
	//before each test there will be created new object company 
	// with array of the given employee objects
	company = new Company(new Employee[] {empl1, empl2, empl3});
}
	@Test
	void testAddEmployee() {
		assertThrowsExactly(IllegalStateException.class,() -> company.addEmployee(empl1));
		empl4 = new Employee(ID4, SALARY4, DEPARTMENT4);
		company.addEmployee(empl4);
		Employee [] expected = new Employee []{empl2, empl4, empl1, empl3};
		Employee [] employees1 = new Employee [4];
		Iterator<Employee> it = company.iterator();
		int index = 0;
		while(it.hasNext()) employees1[index++] = it.next();		
		assertArrayEquals(employees1, expected);
	}

	@Test
	void testGetEmployee() {
		assertEquals(empl1, company.getEmployee(ID1));
		assertNull(company.getEmployee(ID4));
		}
	@Test
	void testRemoveEmployee() {
		assertEquals(empl1, company.removeEmployee(ID1));
		assertThrowsExactly(NoSuchElementException.class,() -> company.removeEmployee(ID1));
	}

	@Test
	void testGetDepartmentBudget() {
		//FIXME there should be another value for budget's of DEPARTMENT1
		assertEquals(SALARY1+SALARY2, company.getDepartmentBudget(DEPARTMENT1));
		assertEquals(0, company.getDepartmentBudget(DEPARTMENT4));
	}

	@Test
	void testIterator() {
		Employee [] expected = new Employee []{empl2, empl1, empl3};
		Iterator<Employee> it = company.iterator();
		int index = 0;
		while(it.hasNext()) assertEquals(it.next(), expected[index++]);
		assertEquals(index, expected.length);
		assertThrowsExactly(NoSuchElementException.class,() -> it.next());
	}
	@Test
	void testGetDepartments() {
		//TODO
	}
}