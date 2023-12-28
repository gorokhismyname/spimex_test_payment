package com.example.spimex_test.state;


/*
    Допущение:
    в данном процессе применяются только линейные переходы
    с возможностью присвоить состояние DECLINED из любого состояния через соответствующий метод в StateContext.class
 */
public enum StateType {

    INITIAL, COMMISSION_APPLIED, ALLOWED, BONUS_APPLIED, FINISHED, DECLINED;

    public StateType nextState() {

        return
                switch (this) {
                    case INITIAL -> COMMISSION_APPLIED;
                    case COMMISSION_APPLIED -> ALLOWED;
                    case ALLOWED -> BONUS_APPLIED;
                    case BONUS_APPLIED -> FINISHED;
                    case FINISHED -> null;
                    case DECLINED -> null;
                };

    }


}
