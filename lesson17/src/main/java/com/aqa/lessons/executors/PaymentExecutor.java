package com.aqa.lessons.executors;

import com.aqa.lessons.data.Module;
import com.aqa.lessons.exceptions.BankProcessingFailedException;
import com.aqa.lessons.exceptions.InvalidClientException;
import com.aqa.lessons.exceptions.InvalidPaymentAmountException;
import com.aqa.lessons.exceptions.InvalidPaymentCurrencyException;

public class PaymentExecutor {

    public void executeCases() {
        caseSuccess();

        try {
            caseInvalidPaymentCurrency();
        } catch (InvalidPaymentCurrencyException e) {
            System.out.println(e.getMessage());
        }

        try {
            caseInvalidClient();
        } catch (InvalidClientException e) {
            System.out.println(e.getMessage());
        }

        try {
            caseInvalidPaymentAmount();
        } catch (InvalidPaymentAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void caseSuccess() {
        Module module = new Module();
        String s = "";
        try {
            s = module.processPayment(6, "USD", "qwe");
        } catch (InvalidPaymentCurrencyException | InvalidPaymentAmountException | BankProcessingFailedException |
                 InvalidClientException e) {
            System.out.println(e.getMessage());
        } finally {
            printFinallyResult("Success", s);
        }
    }

    private void caseInvalidPaymentCurrency() throws InvalidPaymentCurrencyException {
        Module module = new Module();
        String s = "";
        try {
            s = module.processPayment(2, "EsdfUR", "123");
        } catch (InvalidPaymentAmountException | BankProcessingFailedException | InvalidClientException e) {
            System.out.println(e.getMessage());
        } finally {
            printFinallyResult("InvalidPaymentCurrency", s);
        }
    }

    private void caseInvalidClient() throws InvalidClientException {
        Module module = new Module();
        String s = "";
        try {
            s = module.processPayment(4, "CHF", "kidasgf78");
        } catch (InvalidPaymentCurrencyException | InvalidPaymentAmountException | BankProcessingFailedException e) {
            System.out.println(e.getMessage());
        } finally {
            printFinallyResult("InvalidClient", s);
        }
    }

    private void caseInvalidPaymentAmount() throws InvalidPaymentAmountException {
        Module module = new Module();
        String s = "";
        try {
            s = module.processPayment(-27, "JPY", "123");
        } catch (InvalidPaymentCurrencyException | BankProcessingFailedException | InvalidClientException e) {
            System.out.println(e.getMessage());
        } finally {
            printFinallyResult("InvalidPaymentAmount", s);
        }
    }

    private void printFinallyResult(String caseName, String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Case ").append(String.format("'%s' started. ", caseName)).append(String.format("Result parameter is: '%s'", s));
        System.out.println(stringBuilder);
    }

}
