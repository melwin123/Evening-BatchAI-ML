package com.amc.bfsi.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//A collection to hold key value pair
public class MapExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Double> balances = new HashMap();
		balances.put("A123", 20000.0);
		balances.put("A124", 50000.0);
		balances.put("A125", 1000000.0);
		balances.put("A126", 1200000.0);
		balances.put("A127", 500000.0);
		balances.put("A128", 10000000.0);
		for (Entry<String, Double> m : balances.entrySet()) {
			System.out.println("Key  " + m.getKey() + " Value is " + m.getValue());

		}
		balances.put("A128", 10.0);
		System.out.println("A128  "+balances.get("A128"));

	}

}
