package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.AccountDTO;
import com.example.Entities.Account;
import com.example.Entities.Branch;
import com.example.Entities.Customer;
import com.example.ExceptionHandling.ResourceNotFoundException;
import com.example.Repositories.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());

        account.setCustomer(new Customer(accountDTO.getCustomerId()));
        account.setBranch(new Branch(accountDTO.getBranchId()));

        account = accountRepository.save(account);

        accountDTO.setId(account.getId());
        return accountDTO;
    }

    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findByIdNotDeleted(id);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found");
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setCustomerId(account.getCustomer().getId());
        accountDTO.setBranchId(account.getBranch().getId());

        return accountDTO;
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAllNotDeleted().stream()
                .map(account -> {
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setId(account.getId());
                    accountDTO.setAccountNumber(account.getAccountNumber());
                    accountDTO.setAccountType(account.getAccountType());
                    accountDTO.setBalance(account.getBalance());
                    accountDTO.setCustomerId(account.getCustomer().getId());
                    accountDTO.setBranchId(account.getBranch().getId());
                    return accountDTO;
                })
                .collect(Collectors.toList());
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findByIdNotDeleted(id);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found");
        }

        account.setDeleted(true);
        accountRepository.save(account);
    }

    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account account = accountRepository.findByIdNotDeleted(id);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found");
        }

        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());
        account.setCustomer(new Customer(accountDTO.getCustomerId()));
        account.setBranch(new Branch(accountDTO.getBranchId()));

        account = accountRepository.save(account);

        accountDTO.setId(account.getId());
        return accountDTO;
    }
}
