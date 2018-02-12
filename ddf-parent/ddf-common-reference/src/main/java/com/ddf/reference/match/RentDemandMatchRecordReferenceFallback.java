package com.ddf.reference.match;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;
import com.ddf.entity.rent.match.query.RentDemandMatchRecordQuery;
import com.ddf.entity.rent.match.vo.RentDemandMatchRecordVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.stereotype.Component;

@Component
public class RentDemandMatchRecordReferenceFallback implements RentDemandMatchRecordReference {

	@Override
	public ApiResponse<RentDemandMatchRecord> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(RentDemandMatchRecord rentDemandMatchRecord) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(RentDemandMatchRecord rentDemandMatchRecord) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<RentDemandMatchRecordVo>> pagequery(RentDemandMatchRecordQuery query) {
		return null;
	}

	@Override
	public ApiResponse<Boolean> hide(String id) {
		return null;
	}

	@Override
	public ApiResponse<Page<RentDemandMatchRecordVo>> query4houseId4groupbylodgerId(int pageNum, int pageSize, String houseId) {
		return null;
	}

	@Override
	public ApiResponse<Boolean> hide4lodgerId(String lodgerId) {
		return null;
	}
}
