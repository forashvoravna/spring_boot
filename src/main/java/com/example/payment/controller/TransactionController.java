package com.example.payment.controller;

import com.example.payment.domen.Transaction;
import com.example.payment.security.JwtTokenProvider;
import com.example.payment.security.SecurityUtils;
import com.example.payment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TransactionController(TransactionService transactionService, JwtTokenProvider jwtTokenProvider) {
        this.transactionService = transactionService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/transaction")
    public ResponseEntity create(@RequestBody Transaction transaction) {
        Transaction result = transactionService.save(transaction);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/transaction")
    public ResponseEntity update(@RequestBody Transaction transaction) {
        Transaction result = transactionService.save(transaction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/transaction")
    public ResponseEntity getAll() {
        Optional<Object> currentUser = SecurityUtils.getCurrentUserName();

        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/transaction/get")
    public ResponseEntity getMsg() {
        return ResponseEntity.ok("Only for user");
    }

    @GetMapping("/transaction1/all")
    public ResponseEntity getMsg1() {
        return ResponseEntity.ok("For all members");
    }
}
