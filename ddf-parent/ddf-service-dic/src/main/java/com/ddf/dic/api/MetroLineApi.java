/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.dic.api;

import java.util.List;

import com.ddf.entity.dic.query.MetroLineQuery;
import com.ddf.entity.dic.vo.MetroLineVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddf.dic.service.simple.MetroLineService;
import com.ddf.entity.dic.dto.MetroLine;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * metro_line Api
 * @author robot
 * @version 2018-01-08
 */
@Api(value = "MetroLineApi", tags = "地铁线" )
@RestController
public class MetroLineApi extends BaseApi{

	@Autowired
	private MetroLineService metroLineService;

	@ApiOperation(value="查询单个MetroLine")
	@RequestMapping(value = "/metroLine/query",method = {RequestMethod.GET})
	public ApiResponse<MetroLine> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(metroLineService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建MetroLine")
	@RequestMapping(value = "/metroLine/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody MetroLine metroLine){
		try {
			return ApiResponse.success(metroLineService.create(metroLine));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改MetroLine信息")
	@RequestMapping(value = "/metroLine/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody MetroLine metroLine){
		try {
			return ApiResponse.success(metroLineService.modify(metroLine));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除MetroLine信息")
	@RequestMapping(value = "/metroLine/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			return ApiResponse.success(metroLineService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按城市查询MetroLine")
	@RequestMapping(value = "/metroLine/city/metroLine/batchquery",method = {RequestMethod.GET})
	public ApiResponse<List<MetroLineVo>> query4cityId(@ApiParam(value = "cityId",required = true) @RequestParam("cityId") String cityId) {
		List<MetroLineVo> metroLines = null;
		try {
			metroLines = metroLineService.findList4cityId(cityId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(metroLines);
	}
}