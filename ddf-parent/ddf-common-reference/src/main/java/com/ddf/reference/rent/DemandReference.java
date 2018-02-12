package com.ddf.reference.rent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.entity.response.ApiResponse;
/**
 * 需求
 * @author qwe
 *
 */
@FeignClient(value = "service-rent"/*, fallback = DemandReferenceFallback.class*/)
public interface DemandReference {
    
	// 查询单个RentDemand
	@RequestMapping(value = "/rentDemand/query", method = { RequestMethod.GET })
	public ApiResponse<RentDemand> query(@RequestParam(value = "id") String id);

	//创建RentDemand
	@RequestMapping(value = "/rentDemand/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create( RentDemand rentDemand, @RequestParam(value = "currentUserId") String currentUserId);

	//修改RentDemand信息
	@RequestMapping(value = "/rentDemand/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(RentDemand rentDemand, @RequestParam(value = "currentUserId") String currentUserId);

	//删除RentDemand信息
	@RequestMapping(value = "/rentDemand/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

	//需求列表
	@RequestMapping(value = "/rentDemand/list", method = { RequestMethod.POST })
	public ApiResponse<Page<RentDemandVo>> list(RentDemandQuery rentDemandQuery);

}
