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
import com.ddf.entity.message.dto.HouseOpinion;
import com.ddf.entity.message.query.HouseOpinionQuery;
import com.ddf.entity.message.vo.HouseOpinionVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.HouseOpinionService;

/**
 * house_opinion Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "HouseOpinionApi", tags = "房客评价房源" )
@RestController
public class HouseOpinionApi extends BaseApi{

	@Autowired
	private HouseOpinionService houseOpinionService;

	@ApiOperation(value="查询单个 房客评价 房源")
	@RequestMapping(value = "/houseOpinion/query",method = {RequestMethod.GET})
	public ApiResponse<HouseOpinion> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(houseOpinionService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 房客评价 房源，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 房客评价房源")
	@RequestMapping(value = "/houseOpinion/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody HouseOpinion houseOpinion){
		try{
			return ApiResponse.success(houseOpinionService.create(houseOpinion));
		}catch(Exception e){
			logger.error("创建 房客评价房源，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 房客评价房源 信息")
	@RequestMapping(value = "/houseOpinion/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody HouseOpinion houseOpinion){
		try{
			return ApiResponse.success(houseOpinionService.modify(houseOpinion));
		}catch(Exception e){
			logger.error("修改 房客评价房源 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 房客评价房源 信息")
	@RequestMapping(value = "/houseOpinion/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(houseOpinionService.remove(id));
		}catch(Exception e){
			logger.error("删除 房客评价房源 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房源查询  房客评价房源 列表")
	@RequestMapping(value = "/houseOpinion/house/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<HouseOpinionVo>> query4houseId(
			@ApiParam(value = "房源ID",required = true) @RequestParam(required=true) String houseId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			Page<HouseOpinionVo> page = houseOpinionService.query4houseId(houseId, pageNum, pageSize);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按房源查询  房客评价房源 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	
	@ApiOperation(value="查询  房客评价房源 列表")
	@RequestMapping(value = "/houseOpinion/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<HouseOpinionVo>> batchquery(
			@ApiParam(value = "房客评价房源对象",required = false) @RequestBody(required=false) HouseOpinionQuery houseOpinionQuery) {
		try{
			Page<HouseOpinionVo> page = houseOpinionService.query4page(houseOpinionQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询  房客评价房源 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}