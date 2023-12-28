package com.example.spimex_test.service;

import com.example.spimex_test.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/*
    Допущение:
    Коэффициент определяется элементарно
    При усложнении - применить strategy pattern
*/
@Service
public class BonusService {

    @Value("${bonus.max-border}")
    private double maxBonusBorderValue;
    @Value("${bonus.rate.max}")
    private double maxBonusCoefficient;
    @Value("${bonus.rate.shop}")
    private double shopBonusCoefficient;
    @Value("${bonus.rate.online}")
    private double onlineBonusCoefficient;


    public Double getBonusRate(Payment payment) {

        BigDecimal paymentAmountValue = payment.getAmountValue();

        return paymentAmountValue.compareTo(BigDecimal.valueOf(maxBonusBorderValue)) < 0 ?
                chooseCoefficient(payment) : maxBonusCoefficient;

    }

    private Double chooseCoefficient(Payment payment) {
        return
                switch (payment.getPaymentType()) {
                    case SHOP -> shopBonusCoefficient;
                    case ONLINE -> onlineBonusCoefficient;
                };
    }
}
