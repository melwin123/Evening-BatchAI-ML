package com.amc.bfsi.collections;

public class Googlepay implements PaymentProcessor{

	@Override
	public boolean processPayment(double amount) {
		// TODO Auto-generated method stub
		 System.out.println("Processing $" + amount + " via Google Pay.");
		return false;
	}

}
