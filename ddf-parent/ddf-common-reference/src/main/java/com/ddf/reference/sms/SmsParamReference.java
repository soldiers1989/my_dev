package com.ddf.reference.sms;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.sms.dto.SmsParam;
import com.ddf.entity.sms.query.SmsParamQuery;
import com.ddf.entity.sms.vo.SmsParamVo;

@FeignClient(value = "service-sms"/*,fallback = SmsParamReferenceFallback.class*/)
public interface SmsParamReference {

	@ApiOperation(value="查询单个 系统参数配置")
	@RequestMapping(value = "/smsParam/query",method = {RequestMethod.GET})
	public ApiResponse<SmsParam> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 系统参数配置")
	@RequestMapping(value = "/smsParam/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(SmsParam smsParam);

	@ApiOperation(value="修改 系统参数配置 信息")
	@RequestMapping(value = "/smsParam/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(SmsParam smsParam);

	@ApiOperation(value="删除 系统参数配置 信息")
	@RequestMapping(value = "/smsParam/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	@ApiOperation(value="系统参数配置 列表")
	@RequestMapping(value = "/smsParam/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<SmsParamVo>> batchquery(SmsParamQuery smsParamQuery);
}
