package com.example.controller;

import com.example.dto.BankDTO;
import com.example.entities.Bank;
import com.example.service.BankService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    public Bank getBankById(@PathVariable Integer id) {
        return bankService.getBankById(id);
    }

    @PostMapping
    public Bank createBank(@Valid @RequestBody BankDTO bankDTO) {
        return bankService.saveBank(bankDTO);
    }

    @PutMapping("/{id}")
    public Bank updateBank(@PathVariable Integer id, @Valid @RequestBody BankDTO bankDTO) {
        return bankService.updateBank(id, bankDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable Integer id) {
        bankService.deleteBank(id);
    }
}
