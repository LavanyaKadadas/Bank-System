package com.example.dto;

public class LoanDTO {
    private Integer loanId;
    private String loanType;
    private Double amount;
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
	public LoanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Constructors, getters, setters
    
}
