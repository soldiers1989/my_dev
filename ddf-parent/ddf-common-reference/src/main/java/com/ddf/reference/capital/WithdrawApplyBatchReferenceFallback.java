package com.ddf.reference.capital;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawApplyBatch;
import com.ddf.entity.capital.query.WithdrawApplyBatchQuery;
import com.ddf.entity.capital.vo.WithdrawApplyBatchVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class WithdrawApplyBatchReferenceFallback implements WithdrawApplyBatchReference {

	@Override
	public ApiResponse<Page<WithdrawApplyBatchVo>> pagequery(WithdrawApplyBatchQuery withdrawApplyBatchQuery) {
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(String batchName, String[] applyIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> transferCheck(String batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<String> transfer(String alipayBatchNo, String batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> active(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<WithdrawApplyBatch> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
