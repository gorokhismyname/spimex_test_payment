package com.example.spimex_test.service;

import com.example.spimex_test.dto.PaymentResponseDto;
import com.example.spimex_test.model.Account;
import com.example.spimex_test.model.Payment;
import com.example.spimex_test.util.PaymentTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BonusService bonusService;
    private final CommissionService commissionService;
    private final AccountService accountService;

    public PaymentResponseDto makePayment(PaymentTypeEnum type, String amount, long accountId) {

        Payment payment = Payment.buildPayment(type, amount, accountId);
        Account account = accountService.getAccount(accountId);
        PaymentContext paymentContext = PaymentContext.buildPaymentContext(account, payment, accountService, commissionService, bonusService);

        Payment processedPayment = paymentContext.startPaymentProcess();

        return PaymentResponseDto.of(paymentContext.getCurrentPaymentState().getCurrentState(), processedPayment);
    }

}
