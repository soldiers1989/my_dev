package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.WithdrawApply;

/**
 * withdraw_apply EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class WithdrawApplyQuery extends Query {

	private static final long serialVersionUID = 1L;
	
	public WithdrawApplyQuery(){
		this.withdrawApply = new WithdrawApply();
	}
	
	private WithdrawApply withdrawApply;

	public WithdrawApply getWithdrawApply() {
		return withdrawApply;
	}

	public void setWithdrawApply(WithdrawApply withdrawApply) {
		this.withdrawApply = withdrawApply;
	}

}