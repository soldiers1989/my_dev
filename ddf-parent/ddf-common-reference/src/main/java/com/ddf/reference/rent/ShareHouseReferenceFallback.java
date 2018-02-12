package com.ddf.reference.rent;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareHouse;
import com.ddf.entity.rent.eo.ShareHouseStatus;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
@Component
public class ShareHouseReferenceFallback implements ShareHouseReference {
	@Override
	public ApiResponse<ShareHouse> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(ShareHouse shareHouse, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(ShareHouse shareHouse, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<ShareHouseVo>> list(ShareHouseQuery shareHouseQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> review(String id, ShareHouseStatus status) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

}
