package com.ddf.entity.capital.dto;

import java.math.BigDecimal;

import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * cap_capital_account Entity
 * @author robot
 * @version 2018-01-10
 */
@ApiModel(description = "CapCapitalAccount")
public class CapCapitalAccount extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "balance_amount")
	private BigDecimal balanceAmount;
	@ApiModelProperty(value = "user_id")
	private String userId;
	
	public CapCapitalAccount() {
		super();
	}

	public CapCapitalAccount(String id){
		super(id);
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}