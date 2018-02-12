package com.ddf.entity.capital.dto;

import java.math.BigDecimal;
import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * user_wallet Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "UserWallet")
public class UserWallet extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户id")
	private String userId;
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "冻结金额")
	private BigDecimal freezeAmount;
	@ApiModelProperty(value = "状态枚举类型")
	private UserWalletStatus status;
	
	public UserWallet() {
		super();
	}

	public UserWallet(String id){
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
	
	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	
	public UserWalletStatus getStatus() {
		return status;
	}

	public void setStatus(UserWalletStatus status) {
		this.status = status;
	}
	
}