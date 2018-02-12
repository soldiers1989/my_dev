/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.message.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.SysMessage;
import com.ddf.entity.message.eo.SysMessageReadStatus;
import com.ddf.entity.message.query.SysMessageQuery;
import com.ddf.entity.message.vo.SysMessageVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.SysMessageService;

/**
 * sys_message Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "SysMessageApi", tags = "用户消息" )
@RestController
public class SysMessageApi extends BaseApi{
	
	@Autowired
	private SysMessageService sysMessageService;

	@ApiOperation(value="查询单个用户消息")
	@RequestMapping(value = "/sysMessage/query",method = {RequestMethod.GET})
	public ApiResponse<SysMessage> query(@ApiParam(value = "用户消息id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(sysMessageService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个用户消息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建用户消息")
	@RequestMapping(value = "/sysMessage/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody SysMessage sysMessage){
		try{
			return ApiResponse.success(sysMessageService.create(sysMessage));
		}catch(Exception e){
			logger.error("创建用户消息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改用户消息")
	@RequestMapping(value = "/sysMessage/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody SysMessage sysMessage){
		try{
			return ApiResponse.success(sysMessageService.modify(sysMessage));
		}catch(Exception e){
			logger.error("修改用户消息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除用户消息")
	@RequestMapping(value = "/sysMessage/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "用户消息id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(sysMessageService.remove(id));
		}catch(Exception e){
			logger.error("删除用户消息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="我的消息列表")
	@RequestMapping(value = "/sysMessage/my/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<SysMessageVo>> query4user(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize
			) {
		try{
			Page<SysMessageVo> page = sysMessageService.query4userPage(currentUserId, pageNum, pageSize);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("我的消息列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="查询 用户消息列表")
	@RequestMapping(value = "/sysMessage/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<SysMessageVo>> batchquery(
			@ApiParam(value = "SysMessageQuery",required = true) @RequestBody(required=true) SysMessageQuery sysMessageQuery) {
		try{
			Page<SysMessageVo> page = sysMessageService.query4page(sysMessageQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询 用户消息列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="我的消息总行数")
	@RequestMapping(value = "/sysMessage/my/count/query",method = {RequestMethod.GET})
	public ApiResponse<Long> queryAllCount4user(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId) {
		try{
			long num= sysMessageService.queryAllCount4user(currentUserId);
			return ApiResponse.success(num);
		}catch(Exception e){
			logger.error("我的消息总行数，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="我的消息未读行数")
	@RequestMapping(value = "/sysMessage/my/unread/count/query",method = {RequestMethod.GET})
	public ApiResponse<Long> queryUnreadCount4user(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId) {
		try{
			long num= sysMessageService.queryAllCount4user(currentUserId,SysMessageReadStatus.UNREAD);
			return ApiResponse.success(num);
		}catch(Exception e){
			logger.error("我的消息总行数，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="设置用户消息已读")
	@RequestMapping(value = "/sysMessage/read/set",method = {RequestMethod.POST})
	public ApiResponse<Boolean> setRead(
			@ApiParam(value = "用户消息ID",required = true) @RequestParam(required=true)String id){
		try{
			boolean bool= sysMessageService.setRead(id);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("我的消息总行数，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}