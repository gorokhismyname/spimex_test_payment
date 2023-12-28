package com.example.spimex_test.state.implementation;

import com.example.spimex_test.model.Payment;
import com.example.spimex_test.service.PaymentContext;
import com.example.spimex_test.state.PaymentState;
import com.example.spimex_test.state.StateType;

public class InitialState implements PaymentState {
    private final StateType stateType;

    public InitialState() {
        this.stateType = StateType.INITIAL;
    }

    @Override
    public void processPaymentState(PaymentContext paymentContext) {

        Payment payment = paymentContext.getPayment();
        Double commissionRate = paymentContext.getCommissionService().getCommissionRate(payment);
        payment.applyCommissionRate(commissionRate);

        paymentContext.moveState();
        paymentContext.processPaymentState();
    }

    @Override
    public StateType getNextStateType() {
        return this.stateType.nextState();
    }

    @Override
    public StateType getCurrentState() {
        return this.stateType;
    }


}
