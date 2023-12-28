package com.example.spimex_test.controller;

import com.example.spimex_test.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/bankAccountOfEMoney")
    public ResponseEntity<BigDecimal> getAmountOfBonuses(@RequestParam long accountId) {
        BigDecimal bonusValue = accountService.getBonusValue(accountId);
        return ResponseEntity.ok(bonusValue);
    }

    @GetMapping("/money")
    public ResponseEntity<BigDecimal> getAmountOfMoney(@RequestParam long accountId) {
        BigDecimal moneyValue = accountService.getMoneyValue(accountId);
        return ResponseEntity.ok(moneyValue);
    }
}
