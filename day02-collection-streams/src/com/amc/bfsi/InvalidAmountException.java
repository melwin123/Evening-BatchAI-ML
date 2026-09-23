package com.amc.bfsi;

/** Day 2 - an unchecked custom exception. */
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
