package com.example.service;

import com.example.dto.LoanDTO;
import com.example.entities.Loan;
import com.example.exception.ResourceNotFoundException;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getAllLoans();
    LoanDTO getLoanById(Integer id);
    LoanDTO saveLoan(LoanDTO loanDTO);
    void softDeleteLoan(Integer id);
}
