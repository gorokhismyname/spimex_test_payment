package com.example.spimex_test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    Long id;

    @Column
    BigDecimal moneyBalance;

    @Column
    BigDecimal bonusBalance;

}
