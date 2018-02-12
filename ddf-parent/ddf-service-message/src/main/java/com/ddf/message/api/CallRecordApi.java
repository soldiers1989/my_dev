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
import com.ddf.entity.message.dto.CallRecord;
import com.ddf.entity.message.query.CallRecordQuery;
import com.ddf.entity.message.vo.CallRecordVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.CallRecordService;

/**
 * call_record Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "CallRecordApi", tags = "通话记录" )
@RestController
public class CallRecordApi extends BaseApi{

	@Autowired
	private CallRecordService callRecordService;

	@ApiOperation(value="查询单个 通话记录 ")
	@RequestMapping(value = "/callRecord/query",method = {RequestMethod.GET})
	public ApiResponse<CallRecord> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(callRecordService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 通话记录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 通话记录")
	@RequestMapping(value = "/callRecord/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody CallRecord callRecord){
		try{
			return ApiResponse.success(callRecordService.create(callRecord));
		}catch(Exception e){
			logger.error("创建 通话记录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 通话记录 信息")
	@RequestMapping(value = "/callRecord/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody CallRecord callRecord){
		try{
			return ApiResponse.success(callRecordService.modify(callRecord));
		}catch(Exception e){
			logger.error("修改 通话记录 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 通话记录 信息")
	@RequestMapping(value = "/callRecord/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(callRecordService.remove(id));
		}catch(Exception e){
			logger.error("删除 通话记录 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按被叫用户查询  通话记录 列表")
	@RequestMapping(value = "/callRecord/dstUserId/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4dstUserId(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			CallRecordQuery callRecordQuery = new CallRecordQuery();
			callRecordQuery.getCallRecord().setDstUserId(currentUserId);
			callRecordQuery.setPageNum(pageNum);
			callRecordQuery.setPageSize(pageSize);
			Page<CallRecordVo> page = callRecordService.query4page(callRecordQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按被叫用户查询  通话记录 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="按主叫用户查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/srcUserId/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4srcUserId(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			CallRecordQuery callRecordQuery = new CallRecordQuery();
			callRecordQuery.getCallRecord().setSrcUserId(currentUserId);
			callRecordQuery.setPageNum(pageNum);
			callRecordQuery.setPageSize(pageSize);
			Page<CallRecordVo> page = callRecordService.query4page(callRecordQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按主叫用户查询  通话记录  列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="按被叫号码查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/dstMobile/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4dstMobile(
			@ApiParam(value = "手机号码",required = true) @RequestParam(required = true) String dstMobile,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			CallRecordQuery callRecordQuery = new CallRecordQuery();
			callRecordQuery.getCallRecord().setDstMobile(dstMobile);
			callRecordQuery.setPageNum(pageNum);
			callRecordQuery.setPageSize(pageSize);
			Page<CallRecordVo> page = callRecordService.query4page(callRecordQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按被叫号码查询  通话记录  列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="按主叫号码查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/srcMobile/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4srcMobile(
			@ApiParam(value = "手机号码",required = true) @RequestParam(required = true) String srcMobile,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			CallRecordQuery callRecordQuery = new CallRecordQuery();
			callRecordQuery.getCallRecord().setSrcMobile(srcMobile);
			callRecordQuery.setPageNum(pageNum);
			callRecordQuery.setPageSize(pageSize);
			Page<CallRecordVo> page = callRecordService.query4page(callRecordQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按主叫号码查询  通话记录  列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	@ApiOperation(value="通话记录对象 查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<CallRecordVo>> batchquery(
			@ApiParam(value = "通话记录对象",required = false) @RequestBody(required = false) CallRecordQuery callRecordQuery) {
		try{
			Page<CallRecordVo> page = callRecordService.query4page(callRecordQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("通话记录对象 查询  通话记录  列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}