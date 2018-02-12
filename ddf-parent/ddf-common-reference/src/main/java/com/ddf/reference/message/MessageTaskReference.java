package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.MessageTask;
import com.ddf.entity.message.query.MessageTaskQuery;
import com.ddf.entity.message.vo.MessageTaskVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = MessageTaskReferenceFallback.class*/)
public interface MessageTaskReference {
	
	@ApiOperation(value="查询单个 消息库任务")
	@RequestMapping(value = "/messageTask/query",method = {RequestMethod.GET})
	public ApiResponse<MessageTask> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 消息库任务")
	@RequestMapping(value = "/messageTask/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(MessageTask messageTask);

	@ApiOperation(value="修改 消息库任务 信息")
	@RequestMapping(value = "/messageTask/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(MessageTask messageTask);

	@ApiOperation(value="删除 消息库任务 信息")
	@RequestMapping(value = "/messageTask/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	
	@ApiOperation(value="查询 消息库任务 列表")
	@RequestMapping(value = "/messageTask/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<MessageTaskVo>> batchquery(MessageTaskQuery messageTaskQuery);
}
