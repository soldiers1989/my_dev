/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.SpiderHouse;
import com.ddf.entity.rent.query.SpiderHouseQuery;
import com.ddf.entity.rent.vo.SpiderHouseVo;
import com.ddf.rent.service.simple.SpiderHouseService;
import com.ddf.entity.response.ApiResponse;

/**
 * spider_house Api
 * @author robot
 * @version 2018-01-19
 */
@RestController
public class SpiderHouseApi {

	@Autowired
	private SpiderHouseService spiderHouseService;

	@ApiOperation(value="查询单个SpiderHouse")
	@RequestMapping(value = "/spiderHouse/query",method = {RequestMethod.GET})
	public ApiResponse<SpiderHouse> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(spiderHouseService.query4id(id));
	}

	@ApiOperation(value="创建SpiderHouse")
	@RequestMapping(value = "/spiderHouse/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute SpiderHouse spiderHouse){
		return ApiResponse.success(spiderHouseService.create(spiderHouse));
	}

	@ApiOperation(value="修改SpiderHouse信息")
	@RequestMapping(value = "/spiderHouse/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute SpiderHouse spiderHouse){
		return ApiResponse.success(spiderHouseService.modify(spiderHouse));
	}

	@ApiOperation(value="删除SpiderHouse信息")
	@RequestMapping(value = "/spiderHouse/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(spiderHouseService.remove(id));
	}

}