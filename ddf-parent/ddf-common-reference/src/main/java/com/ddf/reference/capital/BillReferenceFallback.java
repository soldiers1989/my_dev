package com.ddf.reference.capital;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class BillReferenceFallback implements BillReference{

	@Override
	public ApiResponse<Page<BillVo>> pagequery(BillQuery billQuery) {
		return null;
	}

}
