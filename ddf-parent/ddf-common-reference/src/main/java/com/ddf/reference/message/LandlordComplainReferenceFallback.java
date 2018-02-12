package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LandlordComplain;
import com.ddf.entity.message.query.LandlordComplainQuery;
import com.ddf.entity.message.vo.LandlordComplainVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class LandlordComplainReferenceFallback implements
		LandlordComplainReference {

	@Override
	public ApiResponse<LandlordComplain> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(LandlordComplain landlordComplain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(LandlordComplain landlordComplain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LandlordComplainVo>> query4landlordId(
			String landlordId, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LandlordComplainVo>> batchquery(
			LandlordComplainQuery landlordComplainQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
