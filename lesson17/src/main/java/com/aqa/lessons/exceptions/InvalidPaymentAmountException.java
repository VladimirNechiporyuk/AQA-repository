package com.aqa.lessons.exceptions;

public class InvalidPaymentAmountException extends Exception{
    public InvalidPaymentAmountException(String message) {
        super(message);
    }
}