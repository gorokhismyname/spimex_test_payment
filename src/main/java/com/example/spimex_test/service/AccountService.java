package com.example.spimex_test.service;

import com.example.spimex_test.model.Payment;
import com.example.spimex_test.exception.AccountNotFoundException;
import com.example.spimex_test.model.Account;
import com.example.spimex_test.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    public Account getAccount(long accountId) {
        return accountRepo.findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    public void updateAccount(Account account, Payment payment) {
        BigDecimal currentMoneyBalance = account.getMoneyBalance();
        BigDecimal currentBonusBalance = account.getBonusBalance();
        BigDecimal totalPaymentAmount = payment.getAmountValue().add(payment.getCommissionValue());

        account.setMoneyBalance(currentMoneyBalance.subtract(totalPaymentAmount));
        account.setBonusBalance(currentBonusBalance.add(payment.getBonusValue()));

        accountRepo.saveAndFlush(account);
    }

    public boolean checkFundsSufficiency(Account account, Payment payment) {
        BigDecimal totalAmount = payment.getAmountValue().add(payment.getCommissionValue());
        return account.getMoneyBalance().compareTo(totalAmount) >= 0;
    }

    public BigDecimal getBonusValue(long accountId) {
        Account account = getAccount(accountId);
        return account.getBonusBalance();
    }

    public BigDecimal getMoneyValue(long accountId) {
        Account account = getAccount(accountId);
        return account.getMoneyBalance();
    }

}
