package com.ddf.reference.rent;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.eo.ApartmentMatchStatus;
import com.ddf.entity.rent.eo.ApartmentStatus;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
@Component
public class ApartmentReferenceFallback implements ApartmentReference {

	@Override
	public ApiResponse<Apartment> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(Apartment apartment, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(Apartment apartment, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> matchStatus(String id, ApartmentMatchStatus matchStatus) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<ApartmentVo>> list(ApartmentQuery apartmentQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> review(String id, ApartmentStatus status) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}
}
