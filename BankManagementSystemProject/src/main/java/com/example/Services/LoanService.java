package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.LoanDTO;
import com.example.Entities.Customer;
import com.example.Entities.Loan;
import com.example.ExceptionHandling.ResourceNotFoundException;
import com.example.Repositories.CustomerRepository;
import com.example.Repositories.LoanRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public LoanDTO createLoan(LoanDTO loanDTO) {
        Customer customer = customerRepository.findById(loanDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Loan loan = new Loan();
        loan.setLoanType(loanDTO.getLoanType());
        loan.setAmount(loanDTO.getAmount());
        loan.setCustomer(customer);

        loan = loanRepository.save(loan);

        return new LoanDTO(loan);
    }

    public LoanDTO getLoanById(Long id) {
        Loan loan = loanRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        return new LoanDTO(loan);
    }

    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAllNotDeleted().stream()
                .map(LoanDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        loan.setDeleted(true);
        loanRepository.save(loan);
    }

    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id " + id));

        Customer customer = customerRepository.findById(loanDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        loan.setLoanType(loanDTO.getLoanType());
        loan.setAmount(loanDTO.getAmount());
        loan.setCustomer(customer);

        Loan updatedLoan = loanRepository.save(loan);
        return new LoanDTO(updatedLoan);
    }
}
