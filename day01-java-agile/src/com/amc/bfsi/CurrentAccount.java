package com.amc.bfsi;

public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    /** Overrides the parent rule - a current account may go below zero. */
    @Override
    public void withdraw(double amount) {
        if (amount > balance + overdraftLimit) {
            System.out.println("Overdraft limit crossed");
            return;
        }
        balance = balance - amount;
    }

    @Override
    public double calculateInterest() {
        return 0;
    }
}
