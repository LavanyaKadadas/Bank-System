package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Loan {
    @Id
    private Integer loanId;
    private String loanType;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
   @JoinColumn(name = "cust_id")
    private Customer customer;

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(Integer loanId, String loanType, Double amount, Branch branch, Customer customer) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.amount = amount;
		this.branch = branch;
		this.customer = customer;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanType=" + loanType + ", amount=" + amount + ", branch=" + branch
				+ ", customer=" + customer + "]";
	}

    
}
