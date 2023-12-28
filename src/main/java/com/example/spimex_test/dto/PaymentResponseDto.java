package com.example.spimex_test.dto;

import com.example.spimex_test.model.Payment;
import com.example.spimex_test.state.StateType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PaymentResponseDto {

    StateType stateType;
    Payment payment;

    public static PaymentResponseDto of(StateType stateType, Payment payment) {
        return new PaymentResponseDto(stateType, payment);
    }
}
