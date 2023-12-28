package com.example.spimex_test.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex) {
        log.error("AccountNotFoundException: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    protected ResponseEntity<String> handleInsufficientFundsException(InsufficientFundsException ex) {
        log.error("InsufficientFundsException: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body("Invalid input value: " + e.getValue());

    }



}
