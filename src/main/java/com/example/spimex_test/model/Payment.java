package com.example.spimex_test.model;

import com.example.spimex_test.util.PaymentTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


/*
    Допущение:
    Значение бонуса и значение коммиссии приняты за свойства сущности Payment
*/

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    PaymentTypeEnum paymentType;
    BigDecimal amountValue;
    BigDecimal commissionValue;
    BigDecimal bonusValue;
    Long accountId;


    public static Payment buildPayment(PaymentTypeEnum type, String amount, long accountId) {
        return Payment.builder()
                .paymentType(type)
                .accountId(accountId)
                .amountValue(new BigDecimal(amount))
                .commissionValue(BigDecimal.ZERO)
                .bonusValue(BigDecimal.ZERO)
                .build();
    }

    public void applyBonusRate(Double bonusRate) {
        this.bonusValue = this.bonusValue.add(this.amountValue.multiply(BigDecimal.valueOf(bonusRate)));
    }
    public void applyCommissionRate(Double commissionRate) {
        this.commissionValue = this.commissionValue.add(this.amountValue.multiply(BigDecimal.valueOf(commissionRate)));
    }


}
