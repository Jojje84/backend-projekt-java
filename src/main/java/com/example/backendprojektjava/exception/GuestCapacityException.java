package com.example.backendprojektjava.exception;

public class GuestCapacityException extends RuntimeException {

    public GuestCapacityException(String message) {
        super(message);
    }
}