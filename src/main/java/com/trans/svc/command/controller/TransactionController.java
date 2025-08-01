package com.trans.svc.command.controller;

import com.trans.svc.command.model.Transaction;
import com.trans.svc.command.service.TransactionService;
import com.trans.svc.common.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService s) { this.service = s; }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
