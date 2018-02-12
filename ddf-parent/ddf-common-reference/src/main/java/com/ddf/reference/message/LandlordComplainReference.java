package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LandlordComplain;
import com.ddf.entity.message.query.LandlordComplainQuery;
import com.ddf.entity.message.vo.LandlordComplainVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = LandlordComplainReferenceFallback.class*/)
public interface LandlordComplainReference {

	@ApiOperation(value="查询单个 举报房东 信息")
	@RequestMapping(value = "/landlordComplain/query",method = {RequestMethod.GET})
	public ApiResponse<LandlordComplain> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 举报房东 ")
	@RequestMapping(value = "/landlordComplain/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(LandlordComplain landlordComplain);

	@ApiOperation(value="修改 举报房东 信息")
	@RequestMapping(value = "/landlordComplain/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(LandlordComplain landlordComplain);

	@ApiOperation(value="删除 举报房东 信息")
	@RequestMapping(value = "/landlordComplain/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房东查询 举报房东 列表")
	@RequestMapping(value = "/landlordComplain/landlord/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LandlordComplainVo>> query4landlordId(
			@RequestParam(value = "landlordId") String landlordId,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);


	@ApiOperation(value="查询 举报房东 列表")
	@RequestMapping(value = "/landlordComplain/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LandlordComplainVo>> batchquery(LandlordComplainQuery landlordComplainQuery);
}
