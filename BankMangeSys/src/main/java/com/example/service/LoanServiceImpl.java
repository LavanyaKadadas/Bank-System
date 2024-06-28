package com.example.service;

import com.example.dto.LoanDTO;
import com.example.entities.Loan;
import com.example.exception.ResourceNotFoundException;
import com.example.Repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAllByIsDeletedFalse();
        return loans.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public LoanDTO getLoanById(Integer id) {
        Loan loan = loanRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id " + id));
        return convertToDTO(loan);
    }

    @Override
    public LoanDTO saveLoan(LoanDTO loanDTO) {
        Loan loan = convertToEntity(loanDTO);
        Loan savedLoan = loanRepository.save(loan);
        return convertToDTO(savedLoan);
    }

    @Override
    public void softDeleteLoan(Integer id) {
        Loan loan = loanRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id " + id));
        loan.setDeleted(true);
        loanRepository.save(loan);
    }

    private LoanDTO convertToDTO(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanId(loan.getLoanId());
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setAmount(loan.getAmount());
        return loanDTO;
    }

    private Loan convertToEntity(LoanDTO loanDTO) {
        Loan loan = new Loan();
        loan.setLoanId(loanDTO.getLoanId());
        loan.setLoanType(loanDTO.getLoanType());
        loan.setAmount(loanDTO.getAmount());
        // Assume branch and customer are set elsewhere or through additional methods
        return loan;
    }
}
