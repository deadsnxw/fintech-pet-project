package com.example.fintech.exception;

public class SameCardTransferException extends RuntimeException {
    public SameCardTransferException() {
        super("Cannot transfer to the same card");
    }
}