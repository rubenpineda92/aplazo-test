package com.example.demo.repo;

import com.example.demo.domain.CreditPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditPaymentRepo extends JpaRepository<CreditPayment, Long> {

}
