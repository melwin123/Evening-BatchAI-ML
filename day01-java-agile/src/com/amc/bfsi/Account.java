package com.amc.bfsi;

/** Day 1 - abstract class: common state, one behaviour left to the child. */
public abstract class Account implements Bankable {

    protected String accountNumber;
    protected double balance;

    protected Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit must be positive");
            return;
        }
        balance = balance + amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return;
        }
        balance = balance - amount;
    }

    @Override
    public double getBalance() { return balance; }

    public String getAccountNumber() { return accountNumber; }

    /** Each account type earns interest differently - polymorphism. */
    public abstract double calculateInterest();
}
