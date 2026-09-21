package com.amc.bfsi;

/** Day 1 - abstraction through an interface. */
public interface Bankable {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}
