package com.ddf.reference.capital;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawApply;
import com.ddf.entity.capital.query.WithdrawApplyBatchQuery;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.vo.WithdrawApplyBatchVo;
import com.ddf.entity.capital.vo.WithdrawApplyVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class WithdrawApplyReferenceFallback implements WithdrawApplyReference {

	@Override
	public ApiResponse<Page<WithdrawApplyVo>> pagequery(WithdrawApplyQuery withdrawApplyQuery) {
		return null;
	}

	@Override
	public ApiResponse<WithdrawApplyVo> query(String id) {
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(WithdrawApply withdrawApply) {
		return null;
	}

	@Override
	public ApiResponse<List<WithdrawApplyVo>> batchIdIsNull(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<List<WithdrawApplyVo>> querySumUser(String batchId, String mobile) {
		// TODO Auto-generated method stub
		return null;
	}


}
