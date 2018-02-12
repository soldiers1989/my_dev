package com.ddf.reference.rent;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
@Component
public class DemandReferenceFallback implements DemandReference{
    @Override
    public ApiResponse<RentDemand> query(String id) {
    	return ApiResponse.fail(ApiResponseResult.ERROR);
    }

	@Override
	public ApiResponse<Boolean> create(RentDemand rentDemand, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(RentDemand rentDemand, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<RentDemandVo>> list(RentDemandQuery rentDemandQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}
}
