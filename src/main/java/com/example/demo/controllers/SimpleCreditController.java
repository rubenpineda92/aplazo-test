package com.example.demo.controllers;

import com.example.demo.domain.CreditPayment;
import com.example.demo.domain.Request;
import com.example.demo.dto.CreditPaymentDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.repo.CreditPaymentRepo;
import com.example.demo.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/credits/simple")
public class SimpleCreditController {

    @Autowired
    private CreditPaymentRepo creditPaymentRepo;

    @Autowired
    private RequestRepo requestRepo;

    @GetMapping("/simulate-payments")
    public ResponseDTO simulatePayments(
            @RequestParam Double amount,
            @RequestParam Integer terms,
            @RequestParam Double rate){
        ResponseDTO responseDTO = new ResponseDTO();

        if( amount == null || amount <= 0 ){
            responseDTO.setErrMsg("The credit amount must be greater than zero");
            return responseDTO;
        }

        if( terms == null || terms <= 0 ){
            responseDTO.setErrMsg("The terms must be greater than zero");
            return responseDTO;
        }

        if( rate == null || rate <= 0 ){
            responseDTO.setErrMsg("The rate must be greater than zero");
            return responseDTO;
        }

        //Saving request received
        getRequestRepo().save(new Request(0L, amount, rate, terms));

        List<CreditPayment> payments = generatePayments(amount, rate, terms);

        responseDTO.setPayments(convertPayments(payments));

        return responseDTO;
    }

    private List<CreditPaymentDTO> convertPayments(List<CreditPayment> payments) {
        return payments.stream().
                map(p -> new CreditPaymentDTO(p.getId(), p.getPaymentNumber(), p.getAmount(), p.getPaymentDate())).
                collect(Collectors.toList());
    }

    private List<CreditPayment> generatePayments(Double amount, Double rate, Integer terms) {
        Double totalPayment = amount * (1 + (rate / 100));
        Double paymentAmount = totalPayment / terms;

        Calendar cal = Calendar.getInstance();

        List<CreditPayment> listCreditPayment = new ArrayList<>();
        for(int i = 0; i < terms ; ++i) {
            cal.add(7, Calendar.DAY_OF_MONTH);
            listCreditPayment.add(new CreditPayment(0L,i + 1, paymentAmount, cal.getTime()));
        }

        listCreditPayment = getCreditPaymentRepo().saveAll(listCreditPayment);

        return listCreditPayment;
    }


    public CreditPaymentRepo getCreditPaymentRepo() {
        return creditPaymentRepo;
    }

    public void setCreditPaymentRepo(CreditPaymentRepo creditPaymentRepo) {
        this.creditPaymentRepo = creditPaymentRepo;
    }

    public RequestRepo getRequestRepo() {
        return requestRepo;
    }

    public void setRequestRepo(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }
}
