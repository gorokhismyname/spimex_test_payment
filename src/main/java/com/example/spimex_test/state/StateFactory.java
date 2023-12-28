package com.example.spimex_test.state;

import com.example.spimex_test.state.implementation.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StateFactory {

    public static PaymentState createState(StateType stateType) {

        return
                switch (stateType) {
                    case INITIAL -> new InitialState();
                    case COMMISSION_APPLIED -> new CommissionAppliedState();
                    case ALLOWED -> new AllowedState();
                    case BONUS_APPLIED -> new BonusAppliedState();
                    case FINISHED -> new FinishedState();
                    case DECLINED -> new DeclinedState();
                };
    }
}

