package com.simplePicPay.controller;

import com.simplePicPay.domain.transactions.TransactionRequest;
import com.simplePicPay.domain.transactions.Transactions;
import com.simplePicPay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionRequest data) throws Exception {
      Transactions transaction =  transactionService.createTransaction(data);

      return  new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
