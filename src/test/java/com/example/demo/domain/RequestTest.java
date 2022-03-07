package com.example.demo.domain;

import com.example.demo.repo.RequestRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RequestTest {

    private final static Double TEST_AMOUNT = 1500d;
    private final static Double TEST_RATE = 3d;
    private final static Integer TEST_TERMS = 5;

    private static Request request;

    @Autowired
    private RequestRepo requestRepo;

    @BeforeAll
    public static void setup() {
        request = new Request(0L, TEST_AMOUNT, TEST_RATE, TEST_TERMS);
    }

    @Test
    public void saveCreditPayment() {
        request = requestRepo.save(request);

        assertNotNull(request.getId());
        assert request.getId() > 0;

    }

}