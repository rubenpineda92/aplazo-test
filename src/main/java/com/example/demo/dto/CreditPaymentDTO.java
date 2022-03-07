package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CreditPaymentDTO {

    private Long id;

    private Integer payment_number;

    private Double amount;

    private Date payment_date;
}
