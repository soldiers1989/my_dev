/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import com.ddf.entity.rent.match.query.ApartmentMatchRecordQuery;
import com.ddf.entity.rent.match.vo.ApartmentMatchRecordVo;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.MonthRentAmountDistrict;
import com.ddf.entity.rent.query.MonthRentAmountDistrictQuery;
import com.ddf.entity.rent.vo.MonthRentAmountDistrictVo;
import com.ddf.rent.service.simple.MonthRentAmountDistrictService;
import com.ddf.entity.response.ApiResponse;

/**
 * month_rent_amount_district Api
 * @author robot
 * @version 2018-01-18
 */
@RestController
public class MonthRentAmountDistrictApi extends BaseApi{

	@Autowired
	private MonthRentAmountDistrictService monthRentAmountDistrictService;

	@ApiOperation(value="查询单个MonthRentAmountDistrict")
	@RequestMapping(value = "/monthRentAmountDistrict/query",method = {RequestMethod.GET})
	public ApiResponse<MonthRentAmountDistrict> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(monthRentAmountDistrictService.query4id(id));
	}

	@ApiOperation(value="创建MonthRentAmountDistrict")
	@RequestMapping(value = "/monthRentAmountDistrict/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute MonthRentAmountDistrict monthRentAmountDistrict){
		return ApiResponse.success(monthRentAmountDistrictService.create(monthRentAmountDistrict));
	}

	@ApiOperation(value="修改MonthRentAmountDistrict信息")
	@RequestMapping(value = "/monthRentAmountDistrict/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute MonthRentAmountDistrict monthRentAmountDistrict){
		return ApiResponse.success(monthRentAmountDistrictService.modify(monthRentAmountDistrict));
	}

	@ApiOperation(value="删除MonthRentAmountDistrict信息")
	@RequestMapping(value = "/monthRentAmountDistrict/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(monthRentAmountDistrictService.remove(id));
	}
	@ApiOperation(value="查询MonthRentAmountDistrict分页信息")
	@RequestMapping(value = "/monthRentAmountDistrict/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<MonthRentAmountDistrictVo>> pagequery(@RequestBody MonthRentAmountDistrictQuery query) {
		try {
			return ApiResponse.success(monthRentAmountDistrictService.query4page(query));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
	
	@ApiOperation(value="查询MonthRentAmountDistrict当前月信息")
	@RequestMapping(value = "/monthRentAmountDistrict/thismonth/query",method = {RequestMethod.GET})
	public ApiResponse<MonthRentAmountDistrictVo> thismonthQuery(@ApiParam(value = "districtId",required = true) @RequestParam(required = true) String districtId) {
		try {
			return ApiResponse.success(monthRentAmountDistrictService.find4thisMonth(districtId));
		} catch (Exception e) {
			logger.error("查询MonthRentAmountDistrict当前月信息异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
}