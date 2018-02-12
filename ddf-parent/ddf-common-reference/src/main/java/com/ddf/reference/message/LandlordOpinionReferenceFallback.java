package com.ddf.reference.message;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LandlordOpinion;
import com.ddf.entity.message.query.LandlordOpinionQuery;
import com.ddf.entity.message.vo.LandlordOpinionVo;
import com.ddf.entity.response.ApiResponse;

public class LandlordOpinionReferenceFallback implements
		LandlordOpinionReference {

	@Override
	public ApiResponse<LandlordOpinion> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(LandlordOpinion landlordOpinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(LandlordOpinion landlordOpinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LandlordOpinionVo>> query4landlordId(
			String landlordId, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LandlordOpinionVo>> batchquery(
			LandlordOpinionQuery landlordOpinionQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
