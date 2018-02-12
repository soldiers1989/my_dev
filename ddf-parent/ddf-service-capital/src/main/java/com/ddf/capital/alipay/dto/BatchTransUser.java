package com.ddf.capital.alipay.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BatchTransUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//收款方账户
	private String account;
	//收款方真实姓名
	private String realName;
	//金额
	private BigDecimal amount;
	//备注
	private String remark;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
