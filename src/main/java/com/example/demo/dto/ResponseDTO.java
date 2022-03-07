package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {

    private String errMsg;

    private List<CreditPaymentDTO> payments;
}
