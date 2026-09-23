package com.amc.bfsi.collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaymentProcessor pymtproc;
		pymtproc = new CreditDebitPayment();
		CheckoutService cc = new CheckoutService(pymtproc);
		cc.checkout("123", 2000.0);
		pymtproc = new Googlepay();
		CheckoutService cc1 = new CheckoutService(pymtproc);
		cc1.checkout("124", 2000.0);
	}

}
