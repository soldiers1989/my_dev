package com.ddf.entity.capital.vo;

import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.member.dto.User;

/**
 * bond_order EntityVo
 * @author robot
 * @version 2018-01-22
 */
public class BondOrderVo extends BondOrder {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private String statusStr;

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
	
}