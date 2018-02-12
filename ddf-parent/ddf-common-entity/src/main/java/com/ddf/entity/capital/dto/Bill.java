package com.ddf.entity.capital.dto;

import java.math.BigDecimal;

import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * bill Entity
 * @author robot
 * @version 2018-01-22
 */
@ApiModel(description = "Bill")
public class Bill extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户id")
	private String userId;
	@ApiModelProperty(value = "订单业务类型，枚举类型")
	private BillOrderType orderType;
	@ApiModelProperty(value = "订单id")
	private String orderId;
	@ApiModelProperty(value = "订单id")
	private String orderNo;
	@ApiModelProperty(value = "账单金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "状态，枚举类型")
	private BillStatus status;
	
	public Bill() {
		super();
	}

	public Bill(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public BillOrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(BillOrderType orderType) {
		this.orderType = orderType;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}
	
}