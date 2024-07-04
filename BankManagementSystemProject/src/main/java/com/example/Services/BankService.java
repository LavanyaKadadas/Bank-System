package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.BankDTO;
import com.example.Entities.Bank;
import com.example.ExceptionHandling.ResourceNotFoundException;
import com.example.Repositories.BankRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setName(bankDTO.getName());
        bank.setCode(bankDTO.getCode());
        bank.setAddress(bankDTO.getAddress());

        bank = bankRepository.save(bank);

        bankDTO.setId(bank.getId());
        return bankDTO;
    }

    public BankDTO getBankById(Long id) {
        Bank bank = bankRepository.findByIdNotDeleted(id);
        if (bank == null) {
            throw new ResourceNotFoundException("Bank not found");
        }

        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setName(bank.getName());
        bankDTO.setCode(bank.getCode());
        bankDTO.setAddress(bank.getAddress());

        return bankDTO;
    }

    public List<BankDTO> getAllBanks() {
        return bankRepository.findAllNotDeleted().stream()
                .map(bank -> {
                    BankDTO bankDTO = new BankDTO();
                    bankDTO.setId(bank.getId());
                    bankDTO.setName(bank.getName());
                    bankDTO.setCode(bank.getCode());
                    bankDTO.setAddress(bank.getAddress());
                    return bankDTO;
                })
                .collect(Collectors.toList());
    }

    public void deleteBank(Long id) {
        Bank bank = bankRepository.findByIdNotDeleted(id);
        if (bank == null) {
            throw new ResourceNotFoundException("Bank not found");
        }

        bank.setDeleted(true);
        bankRepository.save(bank);
    }

    public BankDTO updateBank(Long id, BankDTO bankDTO) {
        Bank bank = bankRepository.findByIdNotDeleted(id);
        if (bank == null) {
            throw new ResourceNotFoundException("Bank not found");
        }

        bank.setName(bankDTO.getName());
        bank.setCode(bankDTO.getCode());
        bank.setAddress(bankDTO.getAddress());

        bank = bankRepository.save(bank);

        bankDTO.setId(bank.getId());
        return bankDTO;
    }
}
