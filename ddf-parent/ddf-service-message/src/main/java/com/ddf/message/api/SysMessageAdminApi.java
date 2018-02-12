/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.message.api;

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
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.ddf.entity.message.eo.SysMessageAdminStatus;
import com.ddf.entity.message.query.SysMessageAdminQuery;
import com.ddf.entity.message.vo.SysMessageAdminVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.SysMessageAdminService;

/**
 * sys_message_admin Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "SysMessageAdminApi", tags = "消息" )
@RestController
public class SysMessageAdminApi extends BaseApi{
	
	@Autowired
	private SysMessageAdminService sysMessageAdminService;

	@ApiOperation(value="查询单个消息")
	@RequestMapping(value = "/sysMessageAdmin/query",method = {RequestMethod.GET})
	public ApiResponse<SysMessageAdmin> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(sysMessageAdminService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个消息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建消息")
	@RequestMapping(value = "/sysMessageAdmin/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody SysMessageAdmin sysMessageAdmin){
		try{
			return ApiResponse.success(sysMessageAdminService.create(sysMessageAdmin));
		}catch(Exception e){
			logger.error("创建消息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改消息信息")
	@RequestMapping(value = "/sysMessageAdmin/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody SysMessageAdmin sysMessageAdmin){
		try{
			return ApiResponse.success(sysMessageAdminService.modify(sysMessageAdmin));
		}catch(Exception e){
			logger.error("修改消息信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除消息信息")
	@RequestMapping(value = "/sysMessageAdmin/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(sysMessageAdminService.remove(id));
		}catch(Exception e){
			logger.error("删除消息信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	
	@ApiOperation(value="查询 消息列表")
	@RequestMapping(value = "/sysMessageAdmin/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<SysMessageAdminVo>> batchquery(
			@ApiParam(value = "SysMessageAdminQuery",required = true) @RequestBody(required=true) SysMessageAdminQuery sysMessageAdminQuery) {
		try{
			Page<SysMessageAdminVo> page = sysMessageAdminService.query4page(sysMessageAdminQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询 消息列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="消息审核")
	@RequestMapping(value = "/sysMessageAdmin/review",method = {RequestMethod.GET})
	public ApiResponse<Boolean> reviewReject(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id,
								@ApiParam(value = "status",required = true) @RequestParam(required = true) SysMessageAdminStatus status) {
		try{
			boolean bool= sysMessageAdminService.reviewReject(id,status);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("消息审核，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="发送消息")
	@RequestMapping(value = "/sysMessageAdmin/send",method = {RequestMethod.GET})
	public ApiResponse<Boolean> send(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			boolean bool= sysMessageAdminService.send(id);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("发送消息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}