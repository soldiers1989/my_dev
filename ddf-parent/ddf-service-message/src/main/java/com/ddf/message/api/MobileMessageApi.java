/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.message.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.MobileMessage;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.MobileMessageService;

/**
 * mobile_message Api
 * @author robot
 * @version 2018-01-10
 */
@RestController
public class MobileMessageApi extends BaseApi{

	@Autowired
	private MobileMessageService mobileMessageService;

	@ApiOperation(value="查询单个MobileMessage")
	@RequestMapping(value = "/mobileMessage/query",method = {RequestMethod.GET})
	public ApiResponse<MobileMessage> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(mobileMessageService.query4id(id));
	}

	@ApiOperation(value="创建MobileMessage")
	@RequestMapping(value = "/mobileMessage/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute MobileMessage mobileMessage){
		return ApiResponse.success(mobileMessageService.create(mobileMessage));
	}

	@ApiOperation(value="修改MobileMessage信息")
	@RequestMapping(value = "/mobileMessage/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute MobileMessage mobileMessage){
		return ApiResponse.success(mobileMessageService.modify(mobileMessage));
	}

	@ApiOperation(value="删除MobileMessage信息")
	@RequestMapping(value = "/mobileMessage/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(mobileMessageService.remove(id));
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按MobileMessage属性查询MobileMessage列表")
	@RequestMapping(value = "/mobileMessage/batchquery",method = {RequestMethod.GET})
	public Page<MobileMessage> queryList(@ModelAttribute MobileMessage mobileMessage) {
		return null;
	}

}