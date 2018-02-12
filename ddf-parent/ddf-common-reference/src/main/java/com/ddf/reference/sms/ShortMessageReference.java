package com.ddf.reference.sms;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.sms.dto.ShortMessage;
import com.ddf.entity.sms.query.ShortMessageQuery;
import com.ddf.entity.sms.vo.ShortMessageVo;

@FeignClient(value = "service-sms"/*,fallback = ShortMessageReferenceFallback.class*/)
public interface ShortMessageReference {

	@ApiOperation(value="查询单个 短信记录")
	@RequestMapping(value = "/shortMessage/query",method = {RequestMethod.GET})
	public ApiResponse<ShortMessage> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 短信记录")
	@RequestMapping(value = "/shortMessage/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(ShortMessage shortMessage);

	@ApiOperation(value="修改 短信记录 信息")
	@RequestMapping(value = "/shortMessage/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(ShortMessage shortMessage);

	@ApiOperation(value="删除 短信记录 信息")
	@RequestMapping(value = "/shortMessage/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	@ApiOperation(value="短信记录对象 查询  短信记录  列表")
	@RequestMapping(value = "/shortMessage/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<ShortMessageVo>> batchquery(ShortMessageQuery shortMessageQuery);
	
	@ApiOperation(value="判断是否需要图形验证码")
	@RequestMapping(value = "/shortMessage/imgCode/required/check",method = {RequestMethod.GET})
	public ApiResponse<Boolean> checkImgCodeRequired(
			@RequestParam(value = "mobile")String mobile,
			@RequestParam(value = "deviceId") String deviceId);

	@ApiOperation(value="发送短信验证码(四位数字)")
	@RequestMapping(value = "/shortMessage/code/send",method = {RequestMethod.GET})
	public ApiResponse<Boolean> sendCode(
			@RequestParam(value = "mobile")String mobile,
			@RequestParam(value = "txyzm") String txyzm,
			@RequestParam(value = "deviceId") String deviceId);
	
	
	@ApiOperation(value="验证短信验证码")
	@RequestMapping(value = "/shortMessage/code/check",method = {RequestMethod.GET})
	public ApiResponse<Boolean> checkCode( 
			@RequestParam(value = "mobile")String mobile, 
			@RequestParam(value = "code")String code);
}
