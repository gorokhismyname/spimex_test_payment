package com.example.spimex_test.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(long accountId) {
        super(String.format("Account id [%s] has no sufficient finds", accountId));
    }
}
