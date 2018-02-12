/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.dic.api;

import java.util.List;

import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Area;
import com.ddf.entity.dic.query.AreaQuery;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.dic.service.simple.AreaService;

/**
 * area Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "AreaApi", tags = "区域" )
@RestController
public class AreaApi extends BaseApi{

	@Autowired
	private AreaService areaService;

	@ApiOperation(value="查询单个Area")
	@RequestMapping(value = "/area/query",method = {RequestMethod.GET})
	public ApiResponse<Area> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(areaService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建Area")
	@RequestMapping(value = "/area/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute Area area){
		try {
			return ApiResponse.success(areaService.create(area));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改Area信息")
	@RequestMapping(value = "/area/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute Area area){
		try {
			return ApiResponse.success(areaService.modify(area));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除Area信息")
	@RequestMapping(value = "/area/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			return ApiResponse.success(areaService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	
	@ApiOperation(value="查询Area的父对象")
	@RequestMapping(value = "/area/parent/query",method = {RequestMethod.GET})
	public ApiResponse<Area> queryParent(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		Area area = null;
		try {
			area = areaService.queryParent(id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(area);
	}
	
	@ApiOperation(value="查询Area所有的上级对象,范围从大到小排列")
	@RequestMapping(value = "/area/parent/batchquery",method = {RequestMethod.GET})
	public ApiResponse<List<AreaVo>> queryParents(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		List<AreaVo> areaVos = null;
		try {
			areaVos = areaService.queryParents(id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(areaVos);
	}
	
	@ApiOperation(value="查询Area子列表")
	@RequestMapping(value = "/area/child/batchquery",method = {RequestMethod.GET})
	public ApiResponse<List<AreaVo>> queryChilds(@ApiParam(value = "parentId",required = true) @RequestParam(required = true) String parentId) {
		List<AreaVo> areaVos = null;
		try {
			areaVos = areaService.queryChilds(parentId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(areaVos);
	}
	@ApiOperation(value="按类型 查询Area列表")
	@RequestMapping(value = "/area/list/batchquery",method = {RequestMethod.GET})
	public ApiResponse<List<AreaVo>> queryListByType(@ApiParam(value = "地区类型  COUNTRY(国家), PROVINCE(省), CITY(城市), DISTRICT(大区), CIRCLE(板块)",required = true) @RequestParam(required = true) AreaType type) {
		List<AreaVo> areaVos = null;
		try {
			areaVos = areaService.queryListByType(type.getValue());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(areaVos);
	}
}