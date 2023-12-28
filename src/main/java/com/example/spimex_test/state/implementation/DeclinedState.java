package com.example.spimex_test.state.implementation;

import com.example.spimex_test.service.PaymentContext;
import com.example.spimex_test.state.PaymentState;
import com.example.spimex_test.state.StateType;

public class DeclinedState implements PaymentState {

    private final StateType stateType;

    public DeclinedState() {
        this.stateType = StateType.DECLINED;
    }

    @Override
    public void processPaymentState(PaymentContext paymentContext) {
        //терминальное состояние
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
