package com.ddf.entity.capital.dto;

import java.math.BigDecimal;
import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * recharge_order Entity
 * @author robot
 * @version 2018-01-22
 */
@ApiModel(description = "RechargeOrder")
public class RechargeOrder extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "充值人id")
	private String userId;
	@ApiModelProperty(value = "充值金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "充值状态枚举类型")
	private RechargeOrderStatus status;
	@ApiModelProperty(value = "支付宝订单号")
	private String alipayTradeNo;
	
	public RechargeOrder() {
		super();
	}

	public RechargeOrder(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public RechargeOrderStatus getStatus() {
		return status;
	}

	public void setStatus(RechargeOrderStatus status) {
		this.status = status;
	}
	
	public String getAlipayTradeNo() {
		return alipayTradeNo;
	}

	public void setAlipayTradeNo(String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}
	
}