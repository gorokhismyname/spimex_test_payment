package com.example.spimex_test.service;

import com.example.spimex_test.model.Account;
import com.example.spimex_test.model.Payment;
import com.example.spimex_test.state.PaymentState;
import com.example.spimex_test.state.StateFactory;
import com.example.spimex_test.state.StateType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


/*
    Допущение:
    Пропущены все методы и состояния связанные с интеграцией с платежными системами и процессами возврата денег
 */

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentContext {

    Payment payment;
    Account account;
    BonusService bonusService;
    AccountService accountService;
    CommissionService commissionService;
    PaymentState currentPaymentState;

    public Payment startPaymentProcess() {
        this.processPaymentState();
        return this.getPayment();
    }


    public void declinePayment() {
        this.currentPaymentState = StateFactory.createState(StateType.DECLINED);
    }

    public void moveState() {
        StateType nextStateType = this.currentPaymentState.getNextStateType();

        log.info("{} --> {}", this.currentPaymentState.getCurrentState(), nextStateType);

        if (nextStateType != null) {
            this.currentPaymentState = StateFactory.createState(nextStateType);
        }
    }

    public void processPaymentState() {
        this.currentPaymentState.processPaymentState(this);
    }

    public static PaymentContext buildPaymentContext(Account account, Payment payment, AccountService accountService, CommissionService commissionService, BonusService bonusService) {
        return PaymentContext.builder()
                .account(account)
                .payment(payment)
                .accountService(accountService)
                .bonusService(bonusService)
                .commissionService(commissionService)
                .currentPaymentState(StateFactory.createState(StateType.INITIAL))
                .build();
    }
}
