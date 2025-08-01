package com.trans.svc.command.controller;

import com.trans.svc.command.model.Account;
import com.trans.svc.command.service.AccountService;
import com.trans.svc.common.dto.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService s) { this.service = s; }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody AccountDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }
}
