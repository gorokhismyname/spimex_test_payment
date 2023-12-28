package com.example.spimex_test.controller;

import com.example.spimex_test.dto.PaymentResponseDto;
import com.example.spimex_test.service.PaymentService;
import com.example.spimex_test.util.PaymentTypeEnum;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
    Допущение:
    OpenAPI - не добавлен
    type принадлежит [SHOP, ONLINE] и не требует валидации
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/payment/{type}/{amount}")
    public ResponseEntity<PaymentResponseDto> makePayment(

            @PathVariable
            PaymentTypeEnum type,

            @PathVariable
            @Pattern(regexp = "^\\d+$", message = "must be numbers only")
            String amount,

            @RequestParam
            long accountId
    ) {

        PaymentResponseDto paymentResponseDto = paymentService.makePayment(type, amount, accountId);
        return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
    }
}
