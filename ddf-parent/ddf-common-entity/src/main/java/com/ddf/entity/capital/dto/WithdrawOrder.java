package com.ddf.entity.capital.dto;

import java.math.BigDecimal;
import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * withdraw_order Entity
 * @author robot
 * @version 2018-01-22
 */
@ApiModel(description = "WithdrawOrder")
public class WithdrawOrder extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "提款人id")
	private String userId;
	@ApiModelProperty(value = "alipay_account")
	private String alipayAccount;
	@ApiModelProperty(value = "提款金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "手续费")
	private BigDecimal userFee;
	@ApiModelProperty(value = "提款订单状态枚举类型")
	private WithdrawOrderStatus status;
	@ApiModelProperty(value = "提现申请id")
	private String applyId;
	
	public WithdrawOrder() {
		super();
	}

	public WithdrawOrder(String id){
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
	
	public WithdrawOrderStatus getStatus() {
		return status;
	}

	public void setStatus(WithdrawOrderStatus status) {
		this.status = status;
	}
	
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
}