package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LodgerOpinion;
import com.ddf.entity.message.query.LodgerOpinionQuery;
import com.ddf.entity.message.vo.LodgerOpinionVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = LodgerOpinionReferenceFallback.class*/)
public interface LodgerOpinionReference {

	@ApiOperation(value="查询单个 评论房客")
	@RequestMapping(value = "/lodgerOpinion/query",method = {RequestMethod.GET})
	public ApiResponse<LodgerOpinion> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 评论房客")
	@RequestMapping(value = "/lodgerOpinion/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(LodgerOpinion lodgerOpinion);

	@ApiOperation(value="修改 评论房客 信息")
	@RequestMapping(value = "/lodgerOpinion/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(LodgerOpinion lodgerOpinion);

	@ApiOperation(value="删除 评论房客 信息")
	@RequestMapping(value = "/lodgerOpinion/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房客查询 评论房客 列表")
	@RequestMapping(value = "/lodgerOpinion/lodger/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LodgerOpinionVo>> query4lodgerId(
			@RequestParam(value = "lodgerId") String lodgerId,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);

	
	@ApiOperation(value="查询 评论房客 列表")
	@RequestMapping(value = "/lodgerOpinion/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LodgerOpinionVo>> batchquery(LodgerOpinionQuery lodgerOpinionQuery);
}
