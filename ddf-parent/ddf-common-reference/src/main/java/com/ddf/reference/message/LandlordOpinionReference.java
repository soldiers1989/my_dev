package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LandlordOpinion;
import com.ddf.entity.message.query.LandlordOpinionQuery;
import com.ddf.entity.message.vo.LandlordOpinionVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = LandlordOpinionReferenceFallback.class*/)
public interface LandlordOpinionReference {
	@ApiOperation(value="查询单个 评论房东")
	@RequestMapping(value = "/landlordOpinion/query",method = {RequestMethod.GET})
	public ApiResponse<LandlordOpinion> query(@RequestParam(value="id") String id);

	@ApiOperation(value="创建 评论房东")
	@RequestMapping(value = "/landlordOpinion/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create( LandlordOpinion landlordOpinion);

	@ApiOperation(value="修改 评论房东 信息")
	@RequestMapping(value = "/landlordOpinion/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify( LandlordOpinion landlordOpinion);

	@ApiOperation(value="删除 评论房东 信息")
	@RequestMapping(value = "/landlordOpinion/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove( @RequestParam(value="id") String id);
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房东查询 评论房东 列表")
	@RequestMapping(value = "/landlordOpinion/landlord/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LandlordOpinionVo>> query4landlordId(
			@RequestParam(value="landlordId") String landlordId,
			@RequestParam(value="pageNum") int pageNum,
			@RequestParam(value="pageSize") int pageSize);
	
	@ApiOperation(value="查询 评论房东 列表")
	@RequestMapping(value = "/landlordOpinion/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LandlordOpinionVo>> batchquery(LandlordOpinionQuery landlordOpinionQuery);
}
