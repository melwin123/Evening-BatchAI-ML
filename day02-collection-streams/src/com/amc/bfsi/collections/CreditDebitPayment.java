package com.amc.bfsi.collections;

public class CreditDebitPayment  implements PaymentProcessor{

	@Override
	public boolean processPayment(double amount) {
		// TODO Auto-generated method stub
		if(amount ==2000) {
			 System.out.println("Processing $" + amount + " via Credit/Debit Card.");
			return true;
		}
		return true;
		
	}

}
