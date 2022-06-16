package com.aqa.lessons.exceptions;

public class InvalidPaymentCurrencyException extends Exception {
    public InvalidPaymentCurrencyException(String message) {
        super(message);
    }
}
