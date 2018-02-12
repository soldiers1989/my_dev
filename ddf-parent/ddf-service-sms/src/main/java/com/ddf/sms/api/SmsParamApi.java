/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.sms.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.entity.sms.dto.SmsParam;
import com.ddf.entity.sms.query.SmsParamQuery;
import com.ddf.entity.sms.vo.SmsParamVo;
import com.ddf.sms.service.simple.SmsParamService;

/**
 * sms_param Api
 * @author robot
 * @version 2018-01-30
 */
@Api(value = "SmsParamApi", tags = "短信系统参数配置" )
@RestController
public class SmsParamApi extends BaseApi{

	@Autowired
	private SmsParamService smsParamService;

	@ApiOperation(value="查询单个 系统参数配置")
	@RequestMapping(value = "/smsParam/query",method = {RequestMethod.GET})
	public ApiResponse<SmsParam> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(smsParamService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 系统参数配置，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 系统参数配置")
	@RequestMapping(value = "/smsParam/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody SmsParam smsParam){
		try{
			return ApiResponse.success(smsParamService.create(smsParam));
		}catch(Exception e){
			logger.error("创建 系统参数配置，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 系统参数配置 信息")
	@RequestMapping(value = "/smsParam/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody SmsParam smsParam){
		try{
			return ApiResponse.success(smsParamService.modify(smsParam));
		}catch(Exception e){
			logger.error("修改 系统参数配置 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 系统参数配置 信息")
	@RequestMapping(value = "/smsParam/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(smsParamService.remove(id));
		}catch(Exception e){
			logger.error("删除 系统参数配置 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	

	@ApiOperation(value="系统参数配置 列表")
	@RequestMapping(value = "/smsParam/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<SmsParamVo>> batchquery(
			@ApiParam(value = "短信记录对象",required = false) @RequestBody(required = false) SmsParamQuery smsParamQuery) {
		try{
			Page<SmsParamVo> page = smsParamService.query4page(smsParamQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("系统参数配置 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}