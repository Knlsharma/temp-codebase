package com.trans.svc.command.service;

import com.trans.svc.command.model.Account;
import com.trans.svc.command.model.OperationType;
import com.trans.svc.command.model.Transaction;
import com.trans.svc.command.repository.AccountRepository;
import com.trans.svc.command.repository.OperationTypeRepository;
import com.trans.svc.command.repository.TransactionRepository;
import com.trans.svc.common.dto.TransactionDto;
import com.trans.svc.common.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final OperationTypeRepository operationRepo;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, OperationTypeRepository operationTypeRepository) {
        this.transactionRepo = transactionRepository;
        this.accountRepo = accountRepository;
        this.operationRepo = operationTypeRepository;
    }

    public Transaction create(TransactionDto dto) {
        return transactionRepo.findByIdempotencyKey(dto.getIdempotencyKey())
                .orElseGet(() -> {
                    Account account = accountRepo.findById(dto.getAccountId())
                            .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + dto.getAccountId()));

                    OperationType op = operationRepo.findById(dto.getOperationTypeId())
                            .orElseThrow(() -> new EntityNotFoundException("OperationType not found with id: " + dto.getOperationTypeId()));

                    BigDecimal signedAmount =
                            op.getDirection() == OperationType.Direction.DEBIT ?
                                    dto.getAmount().negate() : dto.getAmount();

                    Transaction txn = new Transaction();
                    txn.setAccount(account);
                    txn.setOperationType(op);
                    txn.setAmount(dto.getAmount());
                    txn.setSignedAmount(signedAmount);
                    txn.setIdempotencyKey(dto.getIdempotencyKey());
                    return transactionRepo.save(txn);
                });
    }
}