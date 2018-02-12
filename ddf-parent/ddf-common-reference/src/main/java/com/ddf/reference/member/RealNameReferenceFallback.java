package com.ddf.reference.member;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.member.dto.RealName;
import com.ddf.entity.member.query.RealNameQuery;
import com.ddf.entity.member.vo.RealNameVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class RealNameReferenceFallback implements RealNameReference {

	@Override
	public ApiResponse<RealName> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(RealName realName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(RealName realName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<RealNameVo>> batchqueryByRealNameQuery(
			RealNameQuery realNameQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<RealNameVo> query4user(String currentUserId) {
		// TODO Auto-generated method stub
		return null;
	}

}
