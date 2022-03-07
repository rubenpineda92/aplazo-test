package com.example.demo.domain;

import com.example.demo.repo.CreditPaymentRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CreditPaymentTest {

    private final static Double TEST_AMOUNT = 1500d;
    private final static Date TEST_DATE = new Date();

    private static CreditPayment creditPayment;

    @Autowired
    private CreditPaymentRepo creditPaymentRepo;

    @BeforeAll
    public static void setup() {
        creditPayment = new CreditPayment(0L, 1, TEST_AMOUNT, TEST_DATE);
    }

    @Test
    public void saveCreditPayment() {
        creditPayment = creditPaymentRepo.save(creditPayment);

        assertNotNull(creditPayment.getId());
        assert creditPayment.getId() > 0;

    }
}