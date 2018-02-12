package com.ddf.entity.capital.dto;

import java.math.BigDecimal;

import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * cap_trade_order Entity
 * @author robot
 * @version 2018-01-10
 */
@ApiModel(description = "CapTradeOrder")
public class CapTradeOrder extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "self_user_id")
	private Long selfUserId;
	@ApiModelProperty(value = "opposite_user_id")
	private Long oppositeUserId;
	@ApiModelProperty(value = "merchant_order_no")
	private String merchantOrderNo;
	@ApiModelProperty(value = "amount")
	private BigDecimal amount;
	@ApiModelProperty(value = "status")
	private String status;
	@ApiModelProperty(value = "version")
	private String version;
	
	public CapTradeOrder() {
		super();
	}

	public CapTradeOrder(String id){
		super(id);
	}

	public Long getSelfUserId() {
		return selfUserId;
	}

	public void setSelfUserId(Long selfUserId) {
		this.selfUserId = selfUserId;
	}
	
	public Long getOppositeUserId() {
		return oppositeUserId;
	}

	public void setOppositeUserId(Long oppositeUserId) {
		this.oppositeUserId = oppositeUserId;
	}
	
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}