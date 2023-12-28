package com.example.spimex_test.state.implementation;

import com.example.spimex_test.service.PaymentContext;
import com.example.spimex_test.state.PaymentState;
import com.example.spimex_test.state.StateType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinishedState implements PaymentState {
    private final StateType stateType;

    public FinishedState() {
        this.stateType = StateType.FINISHED;
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
