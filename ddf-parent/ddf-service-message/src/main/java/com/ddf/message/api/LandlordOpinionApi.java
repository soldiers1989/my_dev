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
import com.ddf.entity.message.dto.LandlordOpinion;
import com.ddf.entity.message.query.LandlordOpinionQuery;
import com.ddf.entity.message.vo.LandlordOpinionVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.LandlordOpinionService;

/**
 * landlord_opinion Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "LandlordOpinionApi", tags = "房客评论房东" )
@RestController
public class LandlordOpinionApi extends BaseApi{

	@Autowired
	private LandlordOpinionService landlordOpinionService;

	@ApiOperation(value="查询单个 评论房东")
	@RequestMapping(value = "/landlordOpinion/query",method = {RequestMethod.GET})
	public ApiResponse<LandlordOpinion> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(landlordOpinionService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 评论房东，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 评论房东")
	@RequestMapping(value = "/landlordOpinion/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody LandlordOpinion landlordOpinion){
		try{
			return ApiResponse.success(landlordOpinionService.create(landlordOpinion));
		}catch(Exception e){
			logger.error("创建 评论房东，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 评论房东 信息")
	@RequestMapping(value = "/landlordOpinion/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody LandlordOpinion landlordOpinion){
		try{
			return ApiResponse.success(landlordOpinionService.modify(landlordOpinion));
		}catch(Exception e){
			logger.error("修改 评论房东 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 评论房东 信息")
	@RequestMapping(value = "/landlordOpinion/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(landlordOpinionService.remove(id));
		}catch(Exception e){
			logger.error("删除 评论房东 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房东查询 评论房东 列表")
	@RequestMapping(value = "/landlordOpinion/landlord/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LandlordOpinionVo>> query4landlordId(
			@ApiParam(value = "房东ID",required = true) @RequestParam(required=true) String landlordId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			Page<LandlordOpinionVo> page = landlordOpinionService.query4landlordId(landlordId, pageNum, pageSize);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按房东查询 评论房东 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="查询 评论房东 列表")
	@RequestMapping(value = "/landlordOpinion/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LandlordOpinionVo>> batchquery(
			@ApiParam(value = "评论房东对象",required = false) @RequestBody(required=false) LandlordOpinionQuery landlordOpinionQuery) {
		try{
			Page<LandlordOpinionVo> page = landlordOpinionService.query4page(landlordOpinionQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询 评论房东 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}