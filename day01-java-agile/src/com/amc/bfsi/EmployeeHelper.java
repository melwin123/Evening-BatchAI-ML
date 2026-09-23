package com.amc.bfsi;

import java.util.*;

public class EmployeeHelper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static Optional<Employee> geSecondEmployeeHighestSalar(List<Employee> emp) {

		// Employee emp1 = emp.stream().
		// skip(1).;Compator.comparing(Eployee::getsalary).reverse()

		// emp.stream().sorted(Comparator.comparing(Employee::getsalary).reverse()).
		return emp.stream().sorted(Comparator.comparing(Employee::getSalary1)).skip(1).findFirst();

	}
}