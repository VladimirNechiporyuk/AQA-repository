package com.aqa.lessons.data;

import com.aqa.lessons.exceptions.BankProcessingFailedException;
import com.aqa.lessons.exceptions.InvalidClientException;
import com.aqa.lessons.exceptions.InvalidPaymentAmountException;
import com.aqa.lessons.exceptions.InvalidPaymentCurrencyException;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Module {

    private final Set<String> clientIds = Set.of("qwe", "123", "asd", "zxc");

    public static List<String> SUPPORTED_CURRENCIES = List.of("USD", "EUR", "JPY", "CHF");

    public String processPayment(int paymentAmount, String currency, String clientID)
            throws InvalidPaymentCurrencyException, InvalidPaymentAmountException, BankProcessingFailedException, InvalidClientException {

        if (!SUPPORTED_CURRENCIES.contains(currency)) {
            throw new InvalidPaymentCurrencyException(String.format("Currency %s not supported", currency));
        }

        if (clientID == null || clientID.isEmpty() || !clientIds.contains(clientID)) {
            throw new InvalidClientException(String.format("Client id: '%s' . It's not valid", clientID));
        }

        if (paymentAmount <= 0) {
            throw new InvalidPaymentAmountException("Negative or zero payment amount");
        }

        return requestBankProcessing(paymentAmount);

    }

    private String requestBankProcessing(int paymentAmount) throws BankProcessingFailedException {
        // Some bank communication magic here
        Random random = new Random();
        int statusCode = random.nextInt(10);
        if (statusCode > 5) {
            throw new BankProcessingFailedException(String.format("Bank returned result code %s", statusCode));
        }
        return "trx_4knfsf4gs412355";
    }
}