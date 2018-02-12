package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.SysMessage;
import com.ddf.entity.message.query.SysMessageQuery;
import com.ddf.entity.message.vo.SysMessageVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = SysMessageReferenceFallback.class*/)
public interface SysMessageReference {
	@ApiOperation(value="查询单个用户消息")
	@RequestMapping(value = "/sysMessage/query",method = {RequestMethod.GET})
	public ApiResponse<SysMessage> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建用户消息")
	@RequestMapping(value = "/sysMessage/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(SysMessage sysMessage);

	@ApiOperation(value="修改用户消息")
	@RequestMapping(value = "/sysMessage/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(SysMessage sysMessage);

	@ApiOperation(value="删除用户消息")
	@RequestMapping(value = "/sysMessage/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="我的消息列表")
	@RequestMapping(value = "/sysMessage/my/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<SysMessageVo>> query4user(
			@RequestParam(value = "currentUserId") String currentUserId,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);
	
	@ApiOperation(value="查询 用户消息列表")
	@RequestMapping(value = "/sysMessage/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<SysMessageVo>> batchquery(SysMessageQuery sysMessageQuery);
	
	@ApiOperation(value="我的消息总行数")
	@RequestMapping(value = "/sysMessage/my/count/query",method = {RequestMethod.GET})
	public ApiResponse<Long> queryAllCount4user(@RequestParam(value = "id") String currentUserId);
	
	@ApiOperation(value="我的消息未读行数")
	@RequestMapping(value = "/sysMessage/my/unread/count/query",method = {RequestMethod.GET})
	public ApiResponse<Long> queryUnreadCount4user(@RequestParam(value = "id") String currentUserId);
	
	@ApiOperation(value="设置用户消息已读")
	@RequestMapping(value = "/sysMessage/read/set",method = {RequestMethod.POST})
	public ApiResponse<Boolean> setRead(@RequestParam(value = "id")String id);
}
