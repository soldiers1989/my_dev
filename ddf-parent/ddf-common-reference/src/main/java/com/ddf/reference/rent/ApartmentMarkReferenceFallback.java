package com.ddf.reference.rent;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

@Component
public class ApartmentMarkReferenceFallback implements ApartmentMarkReference {

	@Override
	public ApiResponse<ApartmentMark> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(ApartmentMark apartmentMark, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(ApartmentMark apartmentMark, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<ApartmentMarkVo>> list(ApartmentMarkQuery apartmentMarkQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

}
