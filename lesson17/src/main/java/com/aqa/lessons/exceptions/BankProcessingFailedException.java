package com.aqa.lessons.exceptions;

public class BankProcessingFailedException extends Exception {
    public BankProcessingFailedException(String message) {
        super(message);
    }
}