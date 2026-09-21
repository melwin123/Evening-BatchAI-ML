package com.amc.bfsi;


public class Day01Demo {

    public static void main(String[] args) {

        // variables, data types, operators
        int branchCode = 1102;
        double openingBalance = 25000.50;
        boolean kycDone = true;
        System.out.println("Branch " + branchCode + " | KYC done: " + kycDone);

        // if / else
        if (openingBalance >= 10000) {
            System.out.println("Eligible for a savings account");
        } else {
            System.out.println("Minimum balance not met");
        }

        // objects
        Customer c1 = new Customer(101, "Anita Rao", "anita@example.com", "ABCDE1234F");
        System.out.println(c1);

        // polymorphism: one reference type, two behaviours
        Account[] accounts = {
            new SavingsAccount("SB1001", 25000),
            new CurrentAccount("CA2001", 50000, 20000)
        };

        for (Account a : accounts) {
            a.deposit(5000);
            a.withdraw(2000);
            System.out.printf("%s -> balance %.2f, interest %.2f%n",
                    a.getAccountNumber(), a.getBalance(), a.calculateInterest());
        }

        // a method + loop
        System.out.println("Total holding: " + totalBalance(accounts));
    }

    /** method with parameters and a return value */
    static double totalBalance(Account[] accounts) {
        double total = 0;
        for (int i = 0; i < accounts.length; i++) {
            total += accounts[i].getBalance();
        }
        return total;
    }
}
