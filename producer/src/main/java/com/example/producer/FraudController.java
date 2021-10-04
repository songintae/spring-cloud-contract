package com.example.producer;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraudController {

    @PutMapping(value = "/fraudcheck", consumes = "application/json", produces = "application/json")
    public String check(@RequestBody LoanRequest loanRequest) {

        if (loanRequest.getLoanAmount() > 10000) {
            return "{fraudCheckStatus: FRAUD, rejectionReason: Amount too high}";
        } else {
            return "{fraudCheckStatus: OK, acceptanceReason: Amount OK}";
        }
    }
}
