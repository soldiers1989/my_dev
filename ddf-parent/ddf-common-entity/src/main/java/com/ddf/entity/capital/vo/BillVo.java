package com.ddf.entity.capital.vo;

import com.ddf.entity.capital.dto.Bill;

/**
 * bill EntityVo
 * @author robot
 * @version 2018-01-22
 */
public class BillVo extends Bill {
	
	private static final long serialVersionUID = 1L;
	
	private String orderTypeStr;
	
	private String statusStr;
	

	public String getOrderTypeStr() {
		if(this.getOrderType()!=null) {
			return this.getOrderType().getExplain();
		}
		return null;
	}

	public void setOrderTypeStr(String orderTypeStr) {
		this.orderTypeStr = orderTypeStr;
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