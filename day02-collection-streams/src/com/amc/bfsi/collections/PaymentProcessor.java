package com.amc.bfsi.collections;



/*public class CreditCardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Logic for credit card gateway
        System.out.println("Processing $" + amount + " via Credit Card.");
        return true;
    }
}*/

public interface PaymentProcessor {
	public boolean processPayment(double amount);

}
