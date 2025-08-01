package com.trans.svc.command.service;

import com.trans.svc.command.model.Account;
import com.trans.svc.command.repository.AccountRepository;
import com.trans.svc.common.dto.AccountDto;
import com.trans.svc.common.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository repo) { this.accountRepository = repo; }

    public Account create(AccountDto dto) {
        Account account = new Account();
        account.setDocumentNumber(dto.getDocumentNumber());
        return accountRepository.save(account);
    }

    public Account get(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }
}
