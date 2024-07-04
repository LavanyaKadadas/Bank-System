package com.example.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class TransactionDTO {
    private Long id;

    @NotNull(message = "Account ID cannot be null")
    private Long accountId;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    @NotNull(message = "Type cannot be null")
    @Size(min = 1, max = 100, message = "Type must be between 1 and 100 characters")
    private String type;

    @NotNull(message = "Transaction date cannot be null")
    private LocalDateTime transactionDate;

    // Constructors
    public TransactionDTO() {
        // Default constructor (required for frameworks like Spring)
    }

    public TransactionDTO(Long id, Long accountId, Double amount, String type, LocalDateTime transactionDate) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionDTO that = (TransactionDTO) o;

        if (!id.equals(that.id)) return false;
        if (!accountId.equals(that.accountId)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!type.equals(that.type)) return false;
        return transactionDate.equals(that.transactionDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + accountId.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + transactionDate.hashCode();
        return result;
    }

    // toString method
    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
