package com.example.payment.controller;

import com.example.payment.domen.Transaction;
import com.example.payment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity create(@RequestBody Transaction transaction){
        Transaction result = transactionService.save(transaction);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/transaction")
    public ResponseEntity update(@RequestBody Transaction transaction){
        Transaction result = transactionService.save(transaction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/transaction")
    public ResponseEntity getAll(){
        return  ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/transaction/get")
    public ResponseEntity getMsg(){
        return  ResponseEntity.ok("Only for user");
    }
    @GetMapping("/transaction1/all")
    public ResponseEntity getMsg1(){
        return  ResponseEntity.ok("For all members");
    }
}
