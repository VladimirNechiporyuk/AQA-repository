package com.aqa.lessons;

import com.aqa.lessons.executors.PaymentExecutor;

public class Main {

    public static void main(String[] args) {
        PaymentExecutor paymentExecutor = new PaymentExecutor();
        paymentExecutor.executeCases();
    }

}
