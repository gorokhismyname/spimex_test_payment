package com.example.spimex_test.state;

import com.example.spimex_test.service.PaymentContext;

public interface PaymentState {
    void processPaymentState(PaymentContext paymentContext);
    StateType getNextStateType();
    StateType getCurrentState();

}
