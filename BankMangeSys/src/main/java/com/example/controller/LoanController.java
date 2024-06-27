package com.example.controller;
import com.example.entities.Loan;
import com.example.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Integer id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Integer id, @RequestBody Loan loan) {
        Loan existingLoan = loanService.getLoanById(id);
        if (existingLoan != null) {
            loan.setLoanId(id);
            return loanService.saveLoan(loan);
        } else {
            return null; // Handle this case appropriately
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Integer id) {
        loanService.deleteLoan(id);
    }
}
