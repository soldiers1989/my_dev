package com.ddf.entity.capital.dto;

import java.math.BigDecimal;

import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * capital_record Entity
 * @author robot
 * @version 2018-01-10
 */
@ApiModel(description = "CapitalRecord")
public class CapitalRecord extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "user_id")
	private String userId;
	@ApiModelProperty(value = "no")
	private String no;
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "类型")
	private String type;
	
	public CapitalRecord() {
		super();
	}

	public CapitalRecord(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}