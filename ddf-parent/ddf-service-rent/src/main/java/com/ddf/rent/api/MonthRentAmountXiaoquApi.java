/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import com.ddf.entity.rent.query.MonthRentAmountDistrictQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCircleVo;
import com.ddf.entity.rent.vo.MonthRentAmountDistrictVo;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.MonthRentAmountXiaoqu;
import com.ddf.entity.rent.query.MonthRentAmountXiaoquQuery;
import com.ddf.entity.rent.vo.MonthRentAmountXiaoquVo;
import com.ddf.rent.service.simple.MonthRentAmountXiaoquService;
import com.ddf.entity.response.ApiResponse;

/**
 * month_rent_amount_xiaoqu Api
 * @author robot
 * @version 2018-01-18
 */
@RestController
public class MonthRentAmountXiaoquApi extends BaseApi{

	@Autowired
	private MonthRentAmountXiaoquService monthRentAmountXiaoquService;

	@ApiOperation(value="查询单个MonthRentAmountXiaoqu")
	@RequestMapping(value = "/monthRentAmountXiaoqu/query",method = {RequestMethod.GET})
	public ApiResponse<MonthRentAmountXiaoqu> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(monthRentAmountXiaoquService.query4id(id));
	}

	@ApiOperation(value="创建MonthRentAmountXiaoqu")
	@RequestMapping(value = "/monthRentAmountXiaoqu/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody MonthRentAmountXiaoqu monthRentAmountXiaoqu){
		return ApiResponse.success(monthRentAmountXiaoquService.create(monthRentAmountXiaoqu));
	}

	@ApiOperation(value="修改MonthRentAmountXiaoqu信息")
	@RequestMapping(value = "/monthRentAmountXiaoqu/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody MonthRentAmountXiaoqu monthRentAmountXiaoqu){
		return ApiResponse.success(monthRentAmountXiaoquService.modify(monthRentAmountXiaoqu));
	}

	@ApiOperation(value="删除MonthRentAmountXiaoqu信息")
	@RequestMapping(value = "/monthRentAmountXiaoqu/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(monthRentAmountXiaoquService.remove(id));
	}
	@ApiOperation(value="查询monthRentAmountXiaoqu分页信息")
	@RequestMapping(value = "/monthRentAmountXiaoqu/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<MonthRentAmountXiaoquVo>> pagequery(@RequestBody MonthRentAmountXiaoquQuery query) {
		try {
			return ApiResponse.success(monthRentAmountXiaoquService.query4page(query));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
	
	@ApiOperation(value="查询monthRentAmountXiaoqu当前月信息")
	@RequestMapping(value = "/monthRentAmountXiaoqu/thismonth/query",method = {RequestMethod.GET})
	public ApiResponse<MonthRentAmountXiaoquVo> thismonthQuery(@ApiParam(value = "xiaoquId",required = true) @RequestParam(required = true) String xiaoquId) {
		try {
			return ApiResponse.success(monthRentAmountXiaoquService.find4thisMonth(xiaoquId));
		} catch (Exception e) {
			logger.error("查询monthRentAmountXiaoqu当前月信息异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
}