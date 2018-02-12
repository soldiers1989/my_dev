/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.message.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LodgerOpinion;
import com.ddf.entity.message.query.LodgerOpinionQuery;
import com.ddf.entity.message.vo.LodgerOpinionVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.LodgerOpinionService;

/**
 * lodger_opinion Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "LodgerOpinionApi", tags = "房东评论房客" )
@RestController
public class LodgerOpinionApi extends BaseApi{

	@Autowired
	private LodgerOpinionService lodgerOpinionService;

	@ApiOperation(value="查询单个 评论房客")
	@RequestMapping(value = "/lodgerOpinion/query",method = {RequestMethod.GET})
	public ApiResponse<LodgerOpinion> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(lodgerOpinionService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 评论房客，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 评论房客")
	@RequestMapping(value = "/lodgerOpinion/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody LodgerOpinion lodgerOpinion){
		try{
			return ApiResponse.success(lodgerOpinionService.create(lodgerOpinion));
		}catch(Exception e){
			logger.error("创建 评论房客，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 评论房客 信息")
	@RequestMapping(value = "/lodgerOpinion/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody LodgerOpinion lodgerOpinion){
		try{
			return ApiResponse.success(lodgerOpinionService.modify(lodgerOpinion));
		}catch(Exception e){
			logger.error("修改 评论房客 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 评论房客 信息")
	@RequestMapping(value = "/lodgerOpinion/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(lodgerOpinionService.remove(id));
		}catch(Exception e){
			logger.error("删除 评论房客 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房客查询 评论房客 列表")
	@RequestMapping(value = "/lodgerOpinion/lodger/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LodgerOpinionVo>> query4lodgerId(
			@ApiParam(value = "房客ID",required = true) @RequestParam(required=true) String lodgerId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			Page<LodgerOpinionVo> page = lodgerOpinionService.query4lodgerId(lodgerId,pageNum,pageSize);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按房客查询 评论房客 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	
	@ApiOperation(value="查询 评论房客 列表")
	@RequestMapping(value = "/lodgerOpinion/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LodgerOpinionVo>> batchquery(
			@ApiParam(value = "评论房客对象",required = true) @RequestBody(required=true) LodgerOpinionQuery lodgerOpinionQuery) {
		try{
			Page<LodgerOpinionVo> page = lodgerOpinionService.query4page(lodgerOpinionQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询 评论房客 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}