package com.example.spimex_test.controller;

import com.example.spimex_test.model.Account;
import com.example.spimex_test.repository.AccountRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    Допущение:
    Рассмотрен только позитивный сценарий
    Юнит тесты - пропущены
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepo accountRepo;


    @BeforeAll
    void beforeAll() {
        long accountId = 1L;

        Account account = new Account();
        account.setId(accountId);
        account.setMoneyBalance(BigDecimal.valueOf(5000));
        account.setBonusBalance(BigDecimal.valueOf(0));

        accountRepo.save(account);
    }


    @ParameterizedTest
    @MethodSource("provideUrlParams")
    void shouldProcessPaymentSuccessfully(String paymentType, int amount) throws Exception {
        //Given
        long accountId = 1L;

        //When
        ResultActions resultActions = mockMvc.perform(get(String.format("/api/payment/%s/%d", paymentType, amount))
                                                        .param("accountId", String.valueOf(accountId))
                                                        .contentType(MediaType.APPLICATION_JSON));

        //Then
        resultActions.andExpect(status().isOk());

        String response = resultActions.andReturn().getResponse().getContentAsString();
        System.out.printf("HTTP RESPONSE PAYLOAD:\n%s%n",response);
        Account accountById = accountRepo.findAccountById(accountId).get();
        System.out.printf("ACCOUNT STATE AFTER PAYMENT:\n%s%n", accountById);
    }

    private static Stream<Arguments> provideUrlParams() {
        return Stream.of(
                Arguments.of("ONLINE", 100),
                Arguments.of("SHOP", 120),
                Arguments.of("ONLINE", 301),
                Arguments.of("ONLINE", 17),
                Arguments.of("SHOP", 1000),
                Arguments.of("ONLINE", 21),
                Arguments.of("SHOP", 570),
                Arguments.of("ONLINE", 700)
        );
    }
}