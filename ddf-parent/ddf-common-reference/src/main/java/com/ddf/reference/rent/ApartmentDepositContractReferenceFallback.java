package com.ddf.reference.rent;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.rent.eo.ApartmentDepositContractStatus;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

@Component
public class ApartmentDepositContractReferenceFallback implements ApartmentDepositContractReference{

	@Override
	public ApiResponse<ApartmentDepositContract> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(ApartmentDepositContract apartmentDepositContract, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(ApartmentDepositContract apartmentDepositContract, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify4status(String currentUserId, String id, ApartmentDepositContractStatus status) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<ApartmentDepositContractVo>> list(
			ApartmentDepositContractQuery apartmentDepositContractQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

}
