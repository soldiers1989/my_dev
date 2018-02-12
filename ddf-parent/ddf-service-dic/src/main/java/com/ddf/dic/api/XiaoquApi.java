/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.dic.api;

import com.ddf.entity.member.query.UserQuery;
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
import com.ddf.entity.dic.dto.Xiaoqu;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.dic.service.simple.XiaoquService;

import java.util.List;

/**
 * xiaoqu Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "XiaoquApi", tags = "小区" )
@RestController
public class XiaoquApi extends BaseApi{

	@Autowired
	private XiaoquService xiaoquService;

	@ApiOperation(value="查询单个Xiaoqu")
	@RequestMapping(value = "/xiaoqu/query",method = {RequestMethod.GET})
	public ApiResponse<Xiaoqu> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(xiaoquService.query4id(id));
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}

	@ApiOperation(value="创建Xiaoqu")
	@RequestMapping(value = "/xiaoqu/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody Xiaoqu xiaoqu){
		try {
			return ApiResponse.success(xiaoquService.create(xiaoqu));
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}

	@ApiOperation(value="修改Xiaoqu信息")
	@RequestMapping(value = "/xiaoqu/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody Xiaoqu xiaoqu){
		try {
			return ApiResponse.success(xiaoquService.modify(xiaoqu));
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除Xiaoqu信息")
	@RequestMapping(value = "/xiaoqu/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			return ApiResponse.success(xiaoquService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按条件 查询Xiaoqu信息")
	@RequestMapping(value = "/xiaoqu/pageQuery",method = {RequestMethod.POST})
	public ApiResponse<Page<XiaoquVo>> pageQuery(@RequestBody XiaoquQuery xiaoquQuery){
		try {
			return ApiResponse.success(xiaoquService.query4pagehelper(xiaoquQuery));
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
	@ApiOperation(value="模糊查询 分页小区")
	@RequestMapping(value = "/xiaoqu/pageLikeQuery",method = {RequestMethod.POST})
	public ApiResponse<List<XiaoquVo>> query4likewhere(@RequestBody XiaoquQuery xiaoquQuery){
		List<XiaoquVo> list=null;
		try {
			/*if (xiaoquQuery !=null && xiaoquQuery.getPageNum() ==null || xiaoquQuery.getPageSize() == null){
				return ApiResponse.fail(ApiResponseResult.COMMON_PARAM_ERROR);
			}*/
			list = xiaoquService.findList4likeNameOrAddress(xiaoquQuery);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(list);
	}
	@ApiOperation(value="通过经纬度 查到分页小区")
	@RequestMapping(value = "/xiaoqu/queryXiaoquByLngLat",method = {RequestMethod.GET})
	public ApiResponse<List<XiaoquVo>> queryXiaoquByLngLat(@ApiParam(value = "pageNum",required = false) @RequestParam(required=true) Integer pageNum,
												   @ApiParam(value = "pageSize",required = false) @RequestParam(required=true) Integer pageSize,
												   @ApiParam(value = "lng",required = false) @RequestParam(required=false)String lng,
												   @ApiParam(value = "lat",required = false) @RequestParam(required=false)String lat){
		List<XiaoquVo> list=null;
		try {
			list = xiaoquService.findXiaoquByLngLat(pageNum,pageSize,lng,lat);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(list);
	}
	@ApiOperation(value="查询小区总数")
	@RequestMapping(value = "/xiaoqu/queryXiaoquNum",method = {RequestMethod.GET})
	public ApiResponse<Long> queryXiaoquNum(){
		try {
			return ApiResponse.success(xiaoquService.findXiaoquNum());
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}

	}
}