package com.amc.bfsi;

import java.util.Objects;

public class Customer implements Comparable<Customer> {

    private int customerId;
    private String name;
    private String city;
    private double balance;

    public Customer(int customerId, String name, String city, double balance) {
        this.customerId = customerId;
        this.name = name;
        this.city = city;
        this.balance = balance;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public double getBalance() { return balance; }

    /** natural order - by id */
    @Override
    public int compareTo(Customer other) {
        return Integer.compare(this.customerId, other.customerId);
    }

    /** needed so HashSet can detect duplicates */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer that = (Customer) o;
        return customerId == that.customerId;
    }

    @Override
    public int hashCode() { return Objects.hash(customerId); }

    @Override
    public String toString() {
        return customerId + " - " + name + " (" + city + ") Rs." + balance;
    }
}
