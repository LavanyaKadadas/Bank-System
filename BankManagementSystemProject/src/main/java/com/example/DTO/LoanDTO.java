package com.example.DTO;

import com.example.Entities.Loan;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class LoanDTO {

    private Long id;

    @NotBlank(message = "Loan type is required")
    private String loanType;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    private Long accountId; // New field for account_id

    // Constructors
    public LoanDTO() {}

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.loanType = loan.getLoanType();
        this.amount = loan.getAmount();
        this.customerId = loan.getCustomer().getId();
        if (loan.getAccount() != null) {
            this.accountId = loan.getAccount().getId();
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
