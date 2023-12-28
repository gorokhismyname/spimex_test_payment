package com.example.spimex_test.state.implementation;

import com.example.spimex_test.model.Payment;
import com.example.spimex_test.service.PaymentContext;
import com.example.spimex_test.service.BonusService;
import com.example.spimex_test.state.PaymentState;
import com.example.spimex_test.state.StateType;

public class AllowedState implements PaymentState {

    private final StateType stateType;

    public AllowedState() {
        this.stateType = StateType.ALLOWED;
    }

    @Override
    public void processPaymentState(PaymentContext paymentContext) {

        Payment payment = paymentContext.getPayment();
        BonusService bonusService = paymentContext.getBonusService();

        Double bonusCoefficient = bonusService.getBonusRate(payment);
        payment.applyBonusRate(bonusCoefficient);

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
