package com.amc.bfsi.collections;

public class CheckoutService {
	
	private final PaymentProcessor pymtpro;
	
	public CheckoutService(PaymentProcessor p) {
		this.pymtpro = p;
	}
	public void checkout(String string, double amount) {
		boolean isSuccess = pymtpro.processPayment(amount);
		if(isSuccess)
			System.out.println("Order placed successfully");
		else
			System.out.println("Order Failed Please place order again");
	}

}
