package com.example.spimex_test.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(long accountId) {
        super(String.format("Account id [%s] is not found", accountId));
    }
}
