package telran.employees;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Company implements Iterable{
	private Employee[] employees;
	
	public void addEmployee(Employee empl) {
		long index = empl.getId();
		if(getEmployee(index) != null) throw new IllegalStateException();
		employees = Arrays.insertSorted(employees, empl, (s1, s2) -> s1.compareTo(s2));
	}
	
	public Employee getEmployee(long id) {
		int index = Arrays.binarySearch(employees, new Employee(id, 0, null), (s1, s2) -> s1.compareTo(s2));
		return index<0 ? null:employees[index];
	}
	
	public Employee removeEmployee(long id) {
		
		Employee emplForRemove = getEmployee(id);
		if(emplForRemove == null) throw new NoSuchElementException();
		
		employees = Arrays.removeIf(employees, (p) -> p.getId() == id);
		
		return emplForRemove;
	}
	public int getDepartmentBudget(String department) {
		
		int sum = 0;
		Iterator<Employee> it = iterator() ;
		while(it.hasNext()){
			Employee emplFromDep = it.next();
			if(emplFromDep.getDepartment().equals(department)) sum+=emplFromDep.getBasicSalary();
		}
		return sum;
	}
	public Company(Employee[] employees) {
		this.employees = Arrays.copy(employees);
		Arrays.bubbleSort1(this.employees);
	}
	@Override
	public Iterator<Employee> iterator() {
		
		return new CompanyIterator();
	}
	private class CompanyIterator implements Iterator<Employee> {
        int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			return currentIndex <= employees.length-1;
		}

		@Override
		public Employee next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return employees[currentIndex++];
		}
		
	}
}