/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.sms.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.entity.sms.dto.ShortMessage;
import com.ddf.entity.sms.query.ShortMessageQuery;
import com.ddf.entity.sms.vo.ShortMessageVo;
import com.ddf.reference.cache.CacheReference;
import com.ddf.sms.Config;
import com.ddf.sms.service.simple.ShortMessageService;
import com.ddf.sms.service.simple.SmsParamService;
import com.ddf.util.StringUtil;

/**
 * short_message Api
 * @author robot
 * @version 2018-01-11
 */
@Api(value = "ShortMessageApi", tags = "短信记录" )
@RestController
public class ShortMessageApi extends BaseApi{
	
	@Autowired
	private ShortMessageService shortMessageService;
	
	@Autowired
	private CacheReference cacheReference;
	
	@Autowired
	private SmsParamService smsParamService;

	@ApiOperation(value="查询单个 短信记录")
	@RequestMapping(value = "/shortMessage/query",method = {RequestMethod.GET})
	public ApiResponse<ShortMessage> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(shortMessageService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 短信记录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 短信记录")
	@RequestMapping(value = "/shortMessage/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody ShortMessage shortMessage){
		try{
			return ApiResponse.success(shortMessageService.create(shortMessage));
		}catch(Exception e){
			logger.error("创建 短信记录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 短信记录 信息")
	@RequestMapping(value = "/shortMessage/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody ShortMessage shortMessage){
		try{
			return ApiResponse.success(shortMessageService.modify(shortMessage));
		}catch(Exception e){
			logger.error("修改 短信记录 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 短信记录 信息")
	@RequestMapping(value = "/shortMessage/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(shortMessageService.remove(id));
		}catch(Exception e){
			logger.error("删除 短信记录 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="短信记录对象 查询  短信记录  列表")
	@RequestMapping(value = "/shortMessage/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<ShortMessageVo>> batchquery(
			@ApiParam(value = "短信记录对象",required = false) @RequestBody(required = false) ShortMessageQuery shortMessageQuery) {
		try{
			Page<ShortMessageVo> page = shortMessageService.query4page(shortMessageQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("短信记录对象 查询  短信记录  列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="判断是否需要图形验证码")
	@RequestMapping(value = "/shortMessage/imgCode/required/check",method = {RequestMethod.GET})
	public ApiResponse<Boolean> checkImgCodeRequired(
			@ApiParam(value = "手机号码",required = true) @RequestParam(required=true)String mobile,
			@ApiParam(value = "设备号",required = true) @RequestParam(required = true) String deviceId){
		try{
			
			//查询缓存短信验证码 所有keys
			Set<String> keys = cacheReference.getKeys4begin(RedisKeyConstant.verify_).getData();
			//如果两分钟内短信缓存数量大于50,则需要图形验证码
			if(keys!=null && keys.size()>50){
				return ApiResponse.success(true);
			}
			
			//图形码显示控制  0-关闭图片验证 1-开启图形验证
			String value = smsParamService.queryValueByName(Config.smsParam_txyzm);
			if(StringUtil.isNotEmpty(value) && "1".equals(value)){
				return ApiResponse.success(true);
			}
			
			return ApiResponse.success(false);
		}catch(Exception e){
			logger.error("判断是否需要图形验证码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="发送短信验证码(四位数字)")
	@RequestMapping(value = "/shortMessage/code/send",method = {RequestMethod.GET})
	public ApiResponse<Boolean> sendCode( 
			@ApiParam(value = "手机号码",required = true) @RequestParam(required=true)String mobile,
			@ApiParam(value = "图形验证码",required = false) @RequestParam(required = false) String txyzm,
			@ApiParam(value = "设备号",required = true) @RequestParam(required = true) String deviceId
			){
		try{
			String txyzmLocal =cacheReference.get(RedisKeyConstant.txyzm_ + deviceId).getData();
			if (StringUtil.isNotEmpty(txyzmLocal) && !txyzm.equalsIgnoreCase(txyzmLocal)) {
				return ApiResponse.fail(ApiResponseResult.SMS_IMGCODE_ERROR);
			}
			ApiResponseResult result = shortMessageService.sendCode(mobile);
			if(result==ApiResponseResult.SUCCESS){
				return ApiResponse.success(true);
			}
			return ApiResponse.fail(result);
		}catch(Exception e){
			logger.error("发送短信验证码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	@ApiOperation(value="验证短信验证码")
	@RequestMapping(value = "/shortMessage/code/check",method = {RequestMethod.GET})
	public ApiResponse<Boolean> checkCode( 
			@ApiParam(value = "手机号码",required = true) @RequestParam(required=true)String mobile, 
			@ApiParam(value = "验证码",required = true) @RequestParam(required=true)String code){
		try{
			boolean bool = shortMessageService.checkCode(mobile, code);
			if(bool==true){
				return ApiResponse.success(bool);
			}else{
				return ApiResponse.fail(ApiResponseResult.SMS_CODE_ERROR);
			}
		}catch(Exception e){
			logger.error("发送短信验证码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	} 
	
	
	
//	@ApiOperation(value="发送短信验证码")
//	@RequestMapping(value = "/shortMessage/code/send2",method = {RequestMethod.GET})
//	public ApiResponse<Boolean> sendCode2( 
//			@ApiParam(value = "手机号码",required = true) @RequestParam(required=true)String mobile, 
//			@ApiParam(value = "验证码",required = true) @RequestParam(required=true)String code){
//		try{
//			boolean bool = shortMessageService.sendCode(mobile, code);
//			return ApiResponse.success(bool);
//		}catch(Exception e){
//			logger.error("发送短信验证码，异常："+e.getMessage(),e);
//			return ApiResponse.fail(ApiResponseResult.SMS_ERROR);
//		}
//	}	
//
//	@ApiOperation(value="发送自定义短信")
//	@RequestMapping(value = "/shortMessage/content/send",method = {RequestMethod.GET})
//	public ApiResponse<Boolean> sendContent( 
//			@ApiParam(value = "手机号码",required = true) @RequestParam(required=true)String mobile, 
//			@ApiParam(value = "短信内容",required = true) @RequestParam(required=true)String content){
//		try{
//			boolean bool = shortMessageService.sendContent(mobile, content);
//			if(bool==true){
//				return ApiResponse.success(bool);
//			}else{
//				return ApiResponse.fail(ApiResponseResult.SMS_ERROR);
//			}
//		}catch(Exception e){
//			logger.error("发送自定义短信，异常："+e.getMessage(),e);
//			return ApiResponse.fail(ApiResponseResult.ERROR);
//		}
//	}
}