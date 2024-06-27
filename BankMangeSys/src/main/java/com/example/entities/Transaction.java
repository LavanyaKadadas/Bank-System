package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    private Integer transId;
    private Double amount;
    private String transType;

    @ManyToOne
    @JoinColumn(name = "account_no")
    private Account account;

    // Constructors, getters, and setters
    public Transaction() {}

    public Transaction(Integer transId, Double amount, String transType, Account account) {
        this.transId = transId;
        this.amount = amount;
        this.transType = transType;
        this.account = account;
    }

    // Getters and Setters
    
    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction [transId=" + transId + ", amount=" + amount + ", transType=" + transType + ", account=" + account + "]";
    }

}

