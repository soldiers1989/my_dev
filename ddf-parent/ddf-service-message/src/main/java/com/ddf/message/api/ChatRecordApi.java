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
import com.ddf.entity.message.dto.ChatRecord;
import com.ddf.entity.message.query.CallRecordQuery;
import com.ddf.entity.message.vo.CallRecordVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.ChatRecordService;

/**
 * chat_record Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "ChatRecordApi", tags = "聊天记录" )
@RestController
public class ChatRecordApi extends BaseApi{

	@Autowired
	private ChatRecordService chatRecordService;

	@ApiOperation(value="查询单个聊天记录")
	@RequestMapping(value = "/chatRecord/query",method = {RequestMethod.GET})
	public ApiResponse<ChatRecord> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(chatRecordService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个聊天记录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建聊天记录")
	@RequestMapping(value = "/chatRecord/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute ChatRecord chatRecord){
		try{
			return ApiResponse.success(chatRecordService.create(chatRecord));
		}catch(Exception e){
			logger.error("创建聊天记录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改聊天记录信息")
	@RequestMapping(value = "/chatRecord/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute ChatRecord chatRecord){
		try{
			return ApiResponse.success(chatRecordService.modify(chatRecord));
		}catch(Exception e){
			logger.error("修改聊天记录信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除聊天记录信息")
	@RequestMapping(value = "/chatRecord/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(chatRecordService.remove(id));
		}catch(Exception e){
			logger.error("删除聊天记录信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}