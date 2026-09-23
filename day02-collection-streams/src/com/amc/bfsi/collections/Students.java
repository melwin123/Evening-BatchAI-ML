package com.amc.bfsi.collections;
import java.util.*;

public class Students {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> students =new ArrayList();
		students.add("Vikram");
		students.add("Anita");
		students.add("Raghul");
		students.add("Sachin");
		//students.add(123);
		/*
		 * for(int i=0;i<students.size();i++) { String name = (String) students.get(i);
		 * System.out.println(name); }
		 */
		/*
		 * for(String name: students) { System.out.println(name); }
		 */
		//students.removeFirst();
		Collections.sort(students);

		  for(String name: students) { System.out.println(name); }
		 

	}

}
