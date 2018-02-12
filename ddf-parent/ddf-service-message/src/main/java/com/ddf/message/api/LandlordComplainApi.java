/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.message.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LandlordComplain;
import com.ddf.entity.message.query.LandlordComplainQuery;
import com.ddf.entity.message.vo.LandlordComplainVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.LandlordComplainService;

/**
 * landlord_complain Api
 * @author robot
 * @version 2018-01-10
 */

@Api(value = "LandlordComplainApi", tags = "房客举报房东" )
@RestController
public class LandlordComplainApi extends BaseApi{

	@Autowired
	private LandlordComplainService landlordComplainService;

	@ApiOperation(value="查询单个 举报房东 信息")
	@RequestMapping(value = "/landlordComplain/query",method = {RequestMethod.GET})
	public ApiResponse<LandlordComplain> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(landlordComplainService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 举报房东 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 举报房东 ")
	@RequestMapping(value = "/landlordComplain/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody LandlordComplain landlordComplain){
		try{
			return ApiResponse.success(landlordComplainService.create(landlordComplain));
		}catch(Exception e){
			logger.error("创建 举报房东，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 举报房东 信息")
	@RequestMapping(value = "/landlordComplain/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody LandlordComplain landlordComplain){
		try{
			return ApiResponse.success(landlordComplainService.modify(landlordComplain));
		}catch(Exception e){
			logger.error("修改 举报房东 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 举报房东 信息")
	@RequestMapping(value = "/landlordComplain/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(landlordComplainService.remove(id));
		}catch(Exception e){
			logger.error("删除 举报房东 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房东查询 举报房东 列表")
	@RequestMapping(value = "/landlordComplain/landlord/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LandlordComplainVo>> query4landlordId(
			@ApiParam(value = "房东ID",required = true) @RequestParam(required=true) String landlordId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			Page<LandlordComplainVo> page = landlordComplainService.query4landlordId(landlordId, pageNum, pageSize);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按房东查询 举报房东 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}


	@ApiOperation(value="查询 举报房东 列表")
	@RequestMapping(value = "/landlordComplain/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LandlordComplainVo>> batchquery(
			@ApiParam(value = "举报房东对象",required = false) @RequestBody(required=false) LandlordComplainQuery landlordComplainQuery) {
		try{
			Page<LandlordComplainVo> page = landlordComplainService.query4page(landlordComplainQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询 举报房东 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}