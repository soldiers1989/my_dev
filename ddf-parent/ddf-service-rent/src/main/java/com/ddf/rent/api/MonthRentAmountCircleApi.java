/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import com.ddf.entity.rent.query.MonthRentAmountDistrictQuery;
import com.ddf.entity.rent.vo.MonthRentAmountDistrictVo;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.MonthRentAmountCircle;
import com.ddf.entity.rent.query.MonthRentAmountCircleQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCircleVo;
import com.ddf.rent.service.simple.MonthRentAmountCircleService;
import com.ddf.entity.response.ApiResponse;

/**
 * month_rent_amount_circle Api
 * @author robot
 * @version 2018-01-18
 */
@RestController
public class MonthRentAmountCircleApi extends BaseApi{

	@Autowired
	private MonthRentAmountCircleService monthRentAmountCircleService;

	@ApiOperation(value="查询单个MonthRentAmountCircle")
	@RequestMapping(value = "/monthRentAmountCircle/query",method = {RequestMethod.GET})
	public ApiResponse<MonthRentAmountCircle> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(monthRentAmountCircleService.query4id(id));
	}

	@ApiOperation(value="创建MonthRentAmountCircle")
	@RequestMapping(value = "/monthRentAmountCircle/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody MonthRentAmountCircle monthRentAmountCircle){
		return ApiResponse.success(monthRentAmountCircleService.create(monthRentAmountCircle));
	}

	@ApiOperation(value="修改MonthRentAmountCircle信息")
	@RequestMapping(value = "/monthRentAmountCircle/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody MonthRentAmountCircle monthRentAmountCircle){
		return ApiResponse.success(monthRentAmountCircleService.modify(monthRentAmountCircle));
	}

	@ApiOperation(value="删除MonthRentAmountCircle信息")
	@RequestMapping(value = "/monthRentAmountCircle/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(monthRentAmountCircleService.remove(id));
	}
	@ApiOperation(value="查询分页信息")
	@RequestMapping(value = "/monthRentAmountCircle/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<MonthRentAmountCircleVo>> pagequery(@RequestBody MonthRentAmountCircleQuery query) {
		try {
			return ApiResponse.success(monthRentAmountCircleService.query4page(query));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
	
	@ApiOperation(value="查询MonthRentAmountCircle当前月信息")
	@RequestMapping(value = "/monthRentAmountCircle/thismonth/query",method = {RequestMethod.GET})
	public ApiResponse<MonthRentAmountCircleVo> thismonthQuery(@ApiParam(value = "circleId",required = true) @RequestParam(required = true) String circleId) {
		try {
			return ApiResponse.success(monthRentAmountCircleService.find4thisMonth(circleId));
		} catch (Exception e) {
			logger.error("查询MonthRentAmountCircle当前月信息异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
}