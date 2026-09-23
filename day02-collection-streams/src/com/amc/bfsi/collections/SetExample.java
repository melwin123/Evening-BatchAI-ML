package com.amc.bfsi.collections;

import java.util.HashSet;
import java.util.*;

public class SetExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> st_id = new HashSet<>();
		String [] ids = {"C123","C124","C125","C126","C127","C128","C128","C123","C124"};
		for(String id: ids) {
			if(!st_id.add(id)) {
			  System.out.println("Duplicate id found "+id);}
		}

	}

}
