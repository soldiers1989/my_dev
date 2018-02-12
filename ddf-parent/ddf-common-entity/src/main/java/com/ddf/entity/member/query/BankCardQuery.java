package com.ddf.entity.member.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.member.dto.BankCard;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * bank_card EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class BankCardQuery extends Query {

	private static final long serialVersionUID = 1L;

	public BankCardQuery(){
		this.bankCard = new BankCard();
	}
	
	private BankCard bankCard;

	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}
	
	private Date startCreateDate;
	private Date endCreateDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}


}