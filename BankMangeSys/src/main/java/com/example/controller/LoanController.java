package com.example.controller;

import com.example.dto.LoanDTO;
import com.example.service.LoanService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/loans")
@Validated
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public LoanDTO getLoanById(@PathVariable Integer id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public LoanDTO createLoan(@Valid @RequestBody LoanDTO loanDTO) {
        return loanService.saveLoan(loanDTO);
    }

    @PutMapping("/{id}")
    public LoanDTO updateLoan(@PathVariable Integer id, @Valid @RequestBody LoanDTO loanDTO) {
        // Ensure the loanDTO has the loanId set appropriately
        loanDTO.setLoanId(id);
        return loanService.saveLoan(loanDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Integer id) {
        loanService.softDeleteLoan(id);
    }
}
