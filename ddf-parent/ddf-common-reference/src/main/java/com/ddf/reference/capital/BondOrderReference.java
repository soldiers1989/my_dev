package com.ddf.reference.capital;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.capital.query.BondOrderQuery;
import com.ddf.entity.capital.vo.BondOrderVo;
import com.ddf.entity.response.ApiResponse;


@FeignClient(value = "service-capital"/*,fallback = BondOrderReferenceFallback.class*/)
public interface BondOrderReference {
	
	@RequestMapping(value = "/bondOrder/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<BondOrderVo>> pagequery( BondOrderQuery bondOrderQuery);
	
	@RequestMapping(value = "/bondOrder/query",method = {RequestMethod.GET})
	public ApiResponse<BondOrderVo> query(@RequestParam("id") String id);
	
	@RequestMapping(value = "/bondOrder/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody BondOrder bondOrder);
}
