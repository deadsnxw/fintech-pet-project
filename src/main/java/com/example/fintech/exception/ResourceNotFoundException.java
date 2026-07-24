package com.example.fintech.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName) {
        super(entityName + " not found");
    }
}