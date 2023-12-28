package com.example.spimex_test.repository;

import com.example.spimex_test.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findAccountById(Long accountId);
}
