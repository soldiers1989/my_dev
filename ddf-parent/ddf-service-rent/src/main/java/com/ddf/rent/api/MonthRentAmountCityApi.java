/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import com.ddf.entity.rent.query.MonthRentAmountCircleQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCircleVo;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.MonthRentAmountCity;
import com.ddf.entity.rent.query.MonthRentAmountCityQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCityVo;
import com.ddf.rent.service.simple.MonthRentAmountCityService;
import com.ddf.entity.response.ApiResponse;

/**
 * month_rent_amount_city Api
 * @author robot
 * @version 2018-01-18
 */
@RestController
public class MonthRentAmountCityApi extends BaseApi{

	@Autowired
	private MonthRentAmountCityService monthRentAmountCityService;

	@ApiOperation(value="查询单个MonthRentAmountCity")
	@RequestMapping(value = "/monthRentAmountCity/query",method = {RequestMethod.GET})
	public ApiResponse<MonthRentAmountCity> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(monthRentAmountCityService.query4id(id));
	}

	@ApiOperation(value="创建MonthRentAmountCity")
	@RequestMapping(value = "/monthRentAmountCity/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute MonthRentAmountCity monthRentAmountCity){
		return ApiResponse.success(monthRentAmountCityService.create(monthRentAmountCity));
	}

	@ApiOperation(value="修改MonthRentAmountCity信息")
	@RequestMapping(value = "/monthRentAmountCity/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute MonthRentAmountCity monthRentAmountCity){
		return ApiResponse.success(monthRentAmountCityService.modify(monthRentAmountCity));
	}

	@ApiOperation(value="删除MonthRentAmountCity信息")
	@RequestMapping(value = "/monthRentAmountCity/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(monthRentAmountCityService.remove(id));
	}
	@ApiOperation(value="查询分页信息")
	@RequestMapping(value = "/monthRentAmountCity/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<MonthRentAmountCityVo>> pagequery(@RequestBody MonthRentAmountCityQuery query) {
		try {
			return ApiResponse.success(monthRentAmountCityService.query4page(query));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
}