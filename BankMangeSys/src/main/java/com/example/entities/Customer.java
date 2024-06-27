package com.example.entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
    @Id
    private Integer custId;
    private String name;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Loan> loans;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer custId, String name, String phone, String address, List<Loan> loans,
			List<Account> accounts) {
		super();
		this.custId = custId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.loans = loans;
		this.accounts = accounts;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "Customer [custId=" + custId + ", name=" + name + ", phone=" + phone + ", address=" + address + "]";
	}

    
}
