package com.nagarro.test.module;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Statement")
public class Statement {
	
	public Statement() {
		super();
	}

	public Statement(Long iD, Long accountId, LocalDate datefield, String amount) {
		super();
		ID = iD;
		this.accountId = accountId;
		this.datefield = datefield;
		this.amount = amount;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name ="account_id")
	private Long accountId;
	
	@Column(name ="datefield")
	@Convert(converter = DateConverter.class)
	private LocalDate datefield;
	
	@Column(name ="amount")
	private String amount;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public LocalDate getDatefield() {
		return datefield;
	}

	public void setDatefield(LocalDate datefield) {
		this.datefield = datefield;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	
	public boolean isBetweenAmount(String start,String end) {
		boolean isValid=false;
		
		if(start!=null) {
			if(Double.parseDouble(this.getAmount())>=Double.parseDouble(start)) {
				isValid=true;
			}
		}
		if(end!=null) {
			if(Double.parseDouble(this.getAmount())<=Double.parseDouble(end)) {
				isValid=true;
			}else {
				isValid=false;
			}
		}
		
		if(end==null&&start==null)
			isValid=true;
		
		return isValid;
	}
	
	
}
