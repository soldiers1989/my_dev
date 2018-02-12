package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.WithdrawApplyBatch;

/**
 * withdraw_apply_batch EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class WithdrawApplyBatchQuery extends Query {

	private static final long serialVersionUID = 1L;
	
	

	public WithdrawApplyBatchQuery(){
		this.withdrawApplyBatch = new WithdrawApplyBatch();
	}
	
	private WithdrawApplyBatch withdrawApplyBatch;

	public WithdrawApplyBatch getWithdrawApplyBatch() {
		return withdrawApplyBatch;
	}

	public void setWithdrawApplyBatch(WithdrawApplyBatch withdrawApplyBatch) {
		this.withdrawApplyBatch = withdrawApplyBatch;
	}

	
}