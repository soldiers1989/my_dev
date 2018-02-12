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
import com.ddf.entity.message.dto.MessageTask;
import com.ddf.entity.message.query.MessageTaskQuery;
import com.ddf.entity.message.vo.LodgerOpinionVo;
import com.ddf.entity.message.vo.MessageTaskVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.MessageTaskService;

/**
 * message_task Api
 * @author robot
 * @version 2018-01-12
 */
@Api(value = "MessageTaskApi", tags = "消息库任务" )
@RestController
public class MessageTaskApi extends BaseApi{

	@Autowired
	private MessageTaskService messageTaskService;

	@ApiOperation(value="查询单个 消息库任务")
	@RequestMapping(value = "/messageTask/query",method = {RequestMethod.GET})
	public ApiResponse<MessageTask> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(messageTaskService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 消息库任务，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 消息库任务")
	@RequestMapping(value = "/messageTask/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody MessageTask messageTask){
		try{
			return ApiResponse.success(messageTaskService.create(messageTask));
		}catch(Exception e){
			logger.error("创建 消息库任务，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 消息库任务 信息")
	@RequestMapping(value = "/messageTask/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody MessageTask messageTask){
		try{
			return ApiResponse.success(messageTaskService.modify(messageTask));
		}catch(Exception e){
			logger.error("修改 消息库任务 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 消息库任务 信息")
	@RequestMapping(value = "/messageTask/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(messageTaskService.remove(id));
		}catch(Exception e){
			logger.error("删除 消息库任务 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	@ApiOperation(value="查询 消息库任务 列表")
	@RequestMapping(value = "/messageTask/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<MessageTaskVo>> batchquery(
			@ApiParam(value = "消息库任务对象",required = true) @RequestBody(required=true) MessageTaskQuery messageTaskQuery) {
		try{
			Page<MessageTaskVo> page = messageTaskService.query4page(messageTaskQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询 消息库任务 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}