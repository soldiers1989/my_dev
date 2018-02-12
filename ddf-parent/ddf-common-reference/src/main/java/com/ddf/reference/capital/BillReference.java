package com.ddf.reference.capital;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.response.ApiResponse;
@FeignClient(value = "service-capital"/*,fallback = BillReferenceFallback.class*/)
public interface BillReference {
	@RequestMapping(value = "/bill/pagequery",method = {RequestMethod.POST})
	ApiResponse<Page<BillVo>> pagequery(BillQuery billQuery);
}
