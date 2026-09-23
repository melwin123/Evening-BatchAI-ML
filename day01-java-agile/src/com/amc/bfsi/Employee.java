package com.amc.bfsi;

import java.util.*;

public class Employee {
	 String name;
	 int empid=0;
 int salary=0;

	public Employee(String name, int empid, int salary) {
	this.name = name;
	this.salary = salary;
	this.empid = empid;
	}
	
		

	public int getSalary1() {
		return salary;
	}

}

