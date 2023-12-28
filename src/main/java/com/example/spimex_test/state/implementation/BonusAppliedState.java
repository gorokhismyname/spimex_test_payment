package com.example.spimex_test.state.implementation;

import com.example.spimex_test.model.Payment;
import com.example.spimex_test.model.Account;
import com.example.spimex_test.service.PaymentContext;
import com.example.spimex_test.state.PaymentState;
import com.example.spimex_test.state.StateType;

public class BonusAppliedState implements PaymentState {
    private final StateType stateType;

    public BonusAppliedState() {
        this.stateType = StateType.BONUS_APPLIED;
    }

    @Override
    public void processPaymentState(PaymentContext paymentContext) {
        Payment payment = paymentContext.getPayment();
        Account account = paymentContext.getAccount();

        paymentContext.getAccountService().updateAccount(account, payment);

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
