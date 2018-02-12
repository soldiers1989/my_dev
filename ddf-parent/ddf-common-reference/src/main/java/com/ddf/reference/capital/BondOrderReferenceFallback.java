package com.ddf.reference.capital;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.capital.query.BondOrderQuery;
import com.ddf.entity.capital.vo.BondOrderVo;
import com.ddf.entity.response.ApiResponse;

public class BondOrderReferenceFallback implements BondOrderReference{

	@Override
	public ApiResponse<Page<BondOrderVo>> pagequery(BondOrderQuery bondOrderQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<BondOrderVo> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(BondOrder bondOrder) {
		// TODO Auto-generated method stub
		return null;
	}

}
