package com.amc.bfsi;

/**
 * Day 1 - encapsulation.
 * Fields are private; the outside world uses getters and setters.
 */
public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String panNumber;

    public Customer() {
    }

    public Customer(int customerId, String name, String email, String panNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.panNumber = panNumber;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    @Override
    public String toString() {
        return "Customer{id=" + customerId + ", name='" + name + "', email='" + email + "'}";
    }
}
