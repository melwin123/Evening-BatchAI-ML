package com.amc.bfsi;

/** Day 2 - a checked custom exception. */
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
