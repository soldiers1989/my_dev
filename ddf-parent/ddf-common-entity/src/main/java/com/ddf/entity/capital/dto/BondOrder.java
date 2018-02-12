package com.ddf.entity.capital.dto;

import java.math.BigDecimal;
import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * bond_order Entity
 * @author robot
 * @version 2018-01-22
 */
@ApiModel(description = "BondOrder")
public class BondOrder extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房东id")
	private String landlordId;
	@ApiModelProperty(value = "no")
	private String no;
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "支付宝订单号")
	private String alipayTradeNo;
	@ApiModelProperty(value = "状态，枚举类型")
	private BondOrderStatus status;
	
	public BondOrder() {
		super();
	}

	public BondOrder(String id){
		super(id);
	}

	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getAlipayTradeNo() {
		return alipayTradeNo;
	}

	public void setAlipayTradeNo(String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}

	public BondOrderStatus getStatus() {
		return status;
	}

	public void setStatus(BondOrderStatus status) {
		this.status = status;
	}
	
}