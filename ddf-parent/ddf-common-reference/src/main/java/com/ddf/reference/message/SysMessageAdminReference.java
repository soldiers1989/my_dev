package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.ddf.entity.message.eo.SysMessageAdminStatus;
import com.ddf.entity.message.query.SysMessageAdminQuery;
import com.ddf.entity.message.vo.SysMessageAdminVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

@FeignClient(value = "service-message"/*,fallback = SysMessageAdminReferenceFallback.class*/)
public interface SysMessageAdminReference {

	@ApiOperation(value="查询单个消息")
	@RequestMapping(value = "/sysMessageAdmin/query",method = {RequestMethod.GET})
	public ApiResponse<SysMessageAdmin> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建消息")
	@RequestMapping(value = "/sysMessageAdmin/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(SysMessageAdmin sysMessageAdmin);

	@ApiOperation(value="修改消息信息")
	@RequestMapping(value = "/sysMessageAdmin/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(SysMessageAdmin sysMessageAdmin);
	
	@ApiOperation(value="删除消息信息")
	@RequestMapping(value = "/sysMessageAdmin/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	
	@ApiOperation(value="查询 消息列表")
	@RequestMapping(value = "/sysMessageAdmin/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<SysMessageAdminVo>> batchquery(SysMessageAdminQuery sysMessageAdminQuery);

	@ApiOperation(value="消息审核")
	@RequestMapping(value = "/sysMessageAdmin/review",method = {RequestMethod.GET})
	public ApiResponse<Boolean> reviewReject(@RequestParam(value = "id") String id,
								@RequestParam(value = "status") SysMessageAdminStatus status);

	@ApiOperation(value="发送消息")
	@RequestMapping(value = "/sysMessageAdmin/send",method = {RequestMethod.GET})
	public ApiResponse<Boolean> send(@RequestParam(value = "id") String id);
}
