package com.ddf.entity.capital.dto;

import java.math.BigDecimal;
import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * withdraw_apply Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "WithdrawApply")
public class WithdrawApply extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "申请人id")
	private String userId;
	@ApiModelProperty(value = "alipay_account")
	private String alipayAccount;
	@ApiModelProperty(value = "申请提现金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "提现手续费")
	private BigDecimal userFee;
	@ApiModelProperty(value = "申请状态枚举类型")
	private WithdrawApplyStatus status;
	@ApiModelProperty(value = "批次id")
	private String batchId;
	
	public WithdrawApply() {
		super();
	}

	public WithdrawApply(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getUserFee() {
		return userFee;
	}

	public void setUserFee(BigDecimal userFee) {
		this.userFee = userFee;
	}
	
	public WithdrawApplyStatus getStatus() {
		return status;
	}

	public void setStatus(WithdrawApplyStatus status) {
		this.status = status;
	}
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
}