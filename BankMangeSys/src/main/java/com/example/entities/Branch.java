package com.example.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Branch {
    @Id
    private Integer branchId;
    private String name;
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_code")
    private Bank bank;

    @OneToMany(mappedBy = "branch")
    private List<Loan> loans;

    @OneToMany(mappedBy = "branch")
    private List<Account> accounts;

	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Branch(Integer branchId, String name, String address, Bank bank, List<Loan> loans, List<Account> accounts) {
		super();
		this.branchId = branchId;
		this.name = name;
		this.address = address;
		this.bank = bank;
		this.loans = loans;
		this.accounts = accounts;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
		
	}

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", name=" + name + ", address=" + address + ", bank=" + bank + "]";
	}

	 

	
	

 
}
