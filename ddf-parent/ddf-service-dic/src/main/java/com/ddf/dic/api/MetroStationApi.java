/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.dic.api;

import java.util.List;

import com.ddf.entity.dic.query.MetroLineQuery;
import com.ddf.entity.dic.query.MetroStationQuery;
import com.ddf.entity.dic.vo.MetroLineVo;
import com.ddf.entity.dic.vo.MetroStationVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddf.dic.service.simple.MetroStationService;
import com.ddf.entity.dic.dto.MetroLine;
import com.ddf.entity.dic.dto.MetroStation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * metro_station Api
 * @author robot
 * @version 2018-01-08
 */
@Api(value = "MetroStationApi", tags = "地铁站" )
@RestController
public class MetroStationApi extends BaseApi{

	@Autowired
	private MetroStationService metroStationService;

	@ApiOperation(value="查询单个MetroStation")
	@RequestMapping(value = "/metroStation/query",method = {RequestMethod.GET})
	public ApiResponse<MetroStation> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(metroStationService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建MetroStation")
	@RequestMapping(value = "/metroStation/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody MetroStation metroStation){
		try {
			return ApiResponse.success(metroStationService.create(metroStation));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改MetroStation信息")
	@RequestMapping(value = "/metroStation/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody MetroStation metroStation){
		try {
			return ApiResponse.success(metroStationService.modify(metroStation));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除MetroStation信息")
	@RequestMapping(value = "/metroStation/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			return ApiResponse.success(metroStationService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按地铁线查询MetroStation")
	@RequestMapping(value = "/metroStation/metroLine/{metroLineId}/batchquery",method = {RequestMethod.GET})
	public ApiResponse<List<MetroStationVo>> query4metroLineId(@ApiParam(value = "metroLineId",required = true) @PathVariable("metroLineId") String metroLineId) {
		try {
			return ApiResponse.success(metroStationService.findList4metroLineId(metroLineId));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
	
}
