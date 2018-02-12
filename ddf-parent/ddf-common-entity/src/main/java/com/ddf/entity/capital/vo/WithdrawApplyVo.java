package com.ddf.entity.capital.vo;

import java.math.BigDecimal;

import com.ddf.entity.capital.dto.WithdrawApply;
import com.ddf.entity.member.dto.User;

/**
 * withdraw_apply EntityVo
 * @author robot
 * @version 2018-02-02
 */
public class WithdrawApplyVo extends WithdrawApply {
	
	private User user;
	
	private String statusStr;
	
	private BigDecimal sumAmount;
	
	private static final long serialVersionUID = 1L;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatusStr() {
		if(this.getStatus()!=null) {
			return this.getStatus().getExplain();
		}
		return null;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
}