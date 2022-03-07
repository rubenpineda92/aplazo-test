package com.example.demo.controllers;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.repo.CreditPaymentRepo;
import com.example.demo.repo.RequestRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

class SimpleCreditControllerTest {

    private static SimpleCreditController simpleCreditController;

    private static CreditPaymentRepo creditPaymentRepo;

    private static RequestRepo requestRepo;


    @BeforeAll
    static void setUp() {
        creditPaymentRepo = Mockito.mock(CreditPaymentRepo.class);
        requestRepo = Mockito.mock(RequestRepo.class);
        simpleCreditController = new SimpleCreditController();

        when(creditPaymentRepo.saveAll(Mockito.anyList())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(requestRepo.save(Mockito.any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        simpleCreditController.setCreditPaymentRepo(creditPaymentRepo);
        simpleCreditController.setRequestRepo(requestRepo);
    }

    @Test
    void simulatePayments() {
        double amount = 150d;
        double rate = 3d;
        int terms = 5;

        ResponseDTO responseDTO = simpleCreditController.simulatePayments(amount, terms, rate);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertNotNull(responseDTO.getPayments());

        Double expectedTotalPayment = amount * (1 + (rate / 100));

        Double sumOfPayments = responseDTO.getPayments().stream().mapToDouble(p -> p.getAmount()).sum();

        Assertions.assertNull(responseDTO.getErrMsg());
        Assertions.assertEquals(expectedTotalPayment, sumOfPayments);
    }

    @Test
    void simulatePaymentsWhenAmountZero() {
        double amount = 0d;
        double rate = 3d;
        int terms = 5;

        ResponseDTO responseDTO = simpleCreditController.simulatePayments(amount, terms, rate);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertNotNull(responseDTO.getErrMsg());
        Assertions.assertEquals("The credit amount must be greater than zero", responseDTO.getErrMsg());
    }

    @Test
    void simulatePaymentsWhenRateZero() {
        double amount = 150d;
        double rate = 0d;
        int terms = 5;

        ResponseDTO responseDTO = simpleCreditController.simulatePayments(amount, terms, rate);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertNotNull(responseDTO.getErrMsg());
        Assertions.assertEquals("The rate must be greater than zero", responseDTO.getErrMsg());
    }

    @Test
    void simulatePaymentsWhenTermsZero() {
        double amount = 150d;
        double rate = 3d;
        int terms = 0;

        ResponseDTO responseDTO = simpleCreditController.simulatePayments(amount, terms, rate);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertNotNull(responseDTO.getErrMsg());
        Assertions.assertEquals("The terms must be greater than zero", responseDTO.getErrMsg());
    }
}