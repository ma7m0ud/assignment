package com.nagarro.test.module;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


public class StatementSearch {

	public StatementSearch() {
		super();
	}
	public StatementSearch(@NotBlank String accountType, @NotBlank String accountNumber, LocalDate fromDate,
			LocalDate toDate, Double fromAmount, Double toAmount) {
		super();
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromAmount = fromAmount;
		this.toAmount = toAmount;
	}
	@NotBlank
	private String accountType;
	
	@NotBlank
	private String accountNumber;
	
	private LocalDate fromDate;
	
	private LocalDate toDate;
	
	private Double fromAmount;
	
	private Double toAmount;
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public Double getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(Double fromAmount) {
		this.fromAmount = fromAmount;
	}
	public Double getToAmount() {
		return toAmount;
	}
	public void setToAmount(Double toAmount) {
		this.toAmount = toAmount;
	}
	
	
}
