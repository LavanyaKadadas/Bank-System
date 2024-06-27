package com.example.service;

import com.example.dto.BankDTO;
import com.example.entities.Bank;
import com.example.exception.ResourceNotFoundException;
import com.example.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank getBankById(Integer id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: " + id));
    }

    public Bank saveBank(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setCode(bankDTO.getCode());
        bank.setName(bankDTO.getName());
        bank.setAddress(bankDTO.getAddress());
        return bankRepository.save(bank);
    }

    public Bank updateBank(Integer id, BankDTO bankDTO) {
        Bank existingBank = getBankById(id);
        existingBank.setCode(bankDTO.getCode());
        existingBank.setName(bankDTO.getName());
        existingBank.setAddress(bankDTO.getAddress());
        return bankRepository.save(existingBank);
    }

    public void deleteBank(Integer id) {
        Bank bank = getBankById(id);
        bank.setDeleted(true); // Soft delete by marking as deleted
        bankRepository.save(bank);
    }
}
