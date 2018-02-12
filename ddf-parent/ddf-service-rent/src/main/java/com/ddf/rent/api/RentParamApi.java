/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.rent.dto.RentParam;
import com.ddf.entity.response.ApiResponse;
import com.ddf.rent.service.simple.RentParamService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * rent_param Api
 * @author robot
 * @version 2018-01-16
 */
@RestController
public class RentParamApi extends BaseApi{

	@Autowired
	private RentParamService rentParamService;

	@ApiOperation(value="查询单个RentParam")
	@RequestMapping(value = "/rentParam/query",method = {RequestMethod.GET})
	public ApiResponse<RentParam> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(rentParamService.query4id(id));
	}

	@ApiOperation(value="创建RentParam")
	@RequestMapping(value = "/rentParam/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute RentParam rentParam){
		return ApiResponse.success(rentParamService.create(rentParam));
	}

	@ApiOperation(value="修改RentParam信息")
	@RequestMapping(value = "/rentParam/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute RentParam rentParam){
		return ApiResponse.success(rentParamService.modify(rentParam));
	}

	@ApiOperation(value="删除RentParam信息")
	@RequestMapping(value = "/rentParam/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(rentParamService.remove(id));
	}

}