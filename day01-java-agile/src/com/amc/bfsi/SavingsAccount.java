package com.amc.bfsi;

public class SavingsAccount extends Account {

    private static final double RATE = 0.04;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * RATE;
    }
}
