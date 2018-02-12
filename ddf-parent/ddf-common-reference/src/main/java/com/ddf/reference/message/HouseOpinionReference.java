package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.HouseOpinion;
import com.ddf.entity.message.query.HouseOpinionQuery;
import com.ddf.entity.message.vo.HouseOpinionVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = HouseOpinionReferenceFallback.class*/)
public interface HouseOpinionReference {

	@ApiOperation(value="查询单个 房客评价 房源")
	@RequestMapping(value = "/houseOpinion/query",method = {RequestMethod.GET})
	public ApiResponse<HouseOpinion> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 房客评价房源")
	@RequestMapping(value = "/houseOpinion/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(HouseOpinion houseOpinion);

	@ApiOperation(value="修改 房客评价房源 信息")
	@RequestMapping(value = "/houseOpinion/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(HouseOpinion houseOpinion);

	@ApiOperation(value="删除 房客评价房源 信息")
	@RequestMapping(value = "/houseOpinion/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房源查询  房客评价房源 列表")
	@RequestMapping(value = "/houseOpinion/house/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<HouseOpinionVo>> query4houseId(
			@RequestParam(value = "houseId") String houseId,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);

	
	@ApiOperation(value="查询  房客评价房源 列表")
	@RequestMapping(value = "/houseOpinion/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<HouseOpinionVo>> batchquery(HouseOpinionQuery houseOpinionQuery);
}
