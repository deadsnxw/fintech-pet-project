package com.example.fintech.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Not enough money on the card");
    }
}