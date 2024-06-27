package com.example.controller;

import com.example.dto.AccountDTO;
import com.example.entities.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.exception.ResourceNotFoundException;


import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Integer id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public Account createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        Account account = new Account();
        // map accountDTO to account entity
        return accountService.saveAccount(account);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Integer id, @Valid @RequestBody AccountDTO accountDTO) {
        Account existingAccount = accountService.getAccountById(id);
        if (existingAccount != null) {
            // map accountDTO to existingAccount entity
            existingAccount.setAccountNo(id);
            return accountService.saveAccount(existingAccount);
        } else {
            throw new ResourceNotFoundException("Account not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccount(id);
    }
}
