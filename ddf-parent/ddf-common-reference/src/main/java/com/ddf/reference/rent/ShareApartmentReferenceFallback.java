package com.ddf.reference.rent;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ShareApartmentMatchStatus;
import com.ddf.entity.rent.eo.ShareApartmentStatus;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
@Component
public class ShareApartmentReferenceFallback implements ShareApartmentReference {

	@Override
	public ApiResponse<ShareApartment> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(ShareApartment shareApartment, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(ShareApartment shareApartment, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<ShareApartmentVo>> list(ShareApartmentQuery shareApartmentQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> matchStatus(String id, ShareApartmentMatchStatus matchStatus) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> review(String id, ShareApartmentStatus status) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}


}
