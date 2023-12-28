package com.example.spimex_test.service;

import com.example.spimex_test.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CommissionService {

    @Value("${commission.border}")
    private double commissionBorder;
    @Value("${commission.rate}")
    private double commissionRate;

    public Double getCommissionRate(Payment payment) {
        BigDecimal paymentAmountValue = payment.getAmountValue();
        return paymentAmountValue.compareTo(BigDecimal.valueOf(commissionBorder)) > 0 ? 0 : commissionRate;
    }
}
