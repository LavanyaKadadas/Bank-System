package com.example.controller;

import com.example.entities.Transaction;
import com.example.entities.Account;
import com.example.service.TransactionService;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        // Fetch the account entity based on the account number provided in the request
        Account account = accountService.getAccountById(transaction.getAccount().getAccountNo());
        if (account != null) {
            // Set the account entity in the transaction
            transaction.setAccount(account);
            return transactionService.saveTransaction(transaction);
        } else {
            // Handle the case where the account does not exist
            throw new RuntimeException("Account does not exist");
        }
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        Transaction existingTransaction = transactionService.getTransactionById(id);
        if (existingTransaction != null) {
            transaction.setTransId(id);
            return transactionService.saveTransaction(transaction);
        } else {
            return null; // Handle this case appropriately
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
    }
}
