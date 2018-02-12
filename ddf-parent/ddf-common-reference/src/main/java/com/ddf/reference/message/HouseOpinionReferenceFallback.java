package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.HouseOpinion;
import com.ddf.entity.message.query.HouseOpinionQuery;
import com.ddf.entity.message.vo.HouseOpinionVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class HouseOpinionReferenceFallback implements HouseOpinionReference {

	@Override
	public ApiResponse<HouseOpinion> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(HouseOpinion houseOpinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(HouseOpinion houseOpinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<HouseOpinionVo>> query4houseId(String houseId,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<HouseOpinionVo>> batchquery(
			HouseOpinionQuery houseOpinionQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
