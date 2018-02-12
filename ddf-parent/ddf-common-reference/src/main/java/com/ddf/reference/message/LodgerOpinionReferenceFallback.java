package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LodgerOpinion;
import com.ddf.entity.message.query.LodgerOpinionQuery;
import com.ddf.entity.message.vo.LodgerOpinionVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class LodgerOpinionReferenceFallback implements LodgerOpinionReference {

	@Override
	public ApiResponse<LodgerOpinion> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(LodgerOpinion lodgerOpinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(LodgerOpinion lodgerOpinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LodgerOpinionVo>> query4lodgerId(String lodgerId,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LodgerOpinionVo>> batchquery(
			LodgerOpinionQuery lodgerOpinionQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
