package com.example.service;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.AccountRepository;
import com.example.entities.Account;
import com.example.exception.ResourceNotFoundException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAllActive();
    }

    public Account getAccountById(Integer id) {
        return accountRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Integer id) {
        Account account = getAccountById(id);
        account.setDeleted(true); // Perform soft delete
        accountRepository.save(account);
    }
}
