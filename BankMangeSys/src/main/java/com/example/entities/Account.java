package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Account {
    @Id
    private Integer accountNo;
    private String accountType;
    private Double accountBalance;
    private boolean isDeleted; // Add this field for soft delete

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    // Constructors, getters, setters, and toString() method

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

	public void setAccountNo(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Integer getAccountNo() {
		// TODO Auto-generated method stub
		return null;
	}
}
