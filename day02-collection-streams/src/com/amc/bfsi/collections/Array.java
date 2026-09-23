package com.amc.bfsi.collections;

public class Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String [] name = new String[3];
		name[0] = "Java";
		name[1] = "python";
		name[2] = "typescript";
		//name[3] = "javascript";
		for(int i=0; i<name.length;i++)
		{
			System.out.println(name[i]);
		}
		
		//echanced for loop
		for(String  i :name) {
			System.out.println(i);
		}

	}

}
