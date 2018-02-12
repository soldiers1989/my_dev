package com.ddf.entity.capital.vo;

import com.ddf.entity.capital.dto.WithdrawApplyBatch;

/**
 * withdraw_apply_batch EntityVo
 * @author robot
 * @version 2018-02-02
 */
public class WithdrawApplyBatchVo extends WithdrawApplyBatch {
	
	private String statusStr;
	
	private static final long serialVersionUID = 1L;

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