package com.ddf.cache.api;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.cache.service.simple.RedisService;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/cache")
public class CacheApi extends BaseApi{
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value="/put",method = {RequestMethod.POST})
	@ApiOperation(value="存入redis")
	public ApiResponse<Boolean> put(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key,
			@ApiParam(value = "value",required = true) @RequestParam(required = true) String value) {
		try {
			redisService.put(key, value);
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_PUT_ERROR);
		}
		return ApiResponse.success(Boolean.TRUE);
	}
	
	@RequestMapping(value="/time/put",method = {RequestMethod.POST})
	@ApiOperation(value="存入redis加时间")
	public ApiResponse<Boolean> put4time(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key,
			@ApiParam(value = "value",required = true) @RequestParam(required = true) String value,
			@ApiParam(value = "seconds",required = true) @RequestParam(required = true) int seconds) {
		try {
			redisService.put(key, value,seconds);
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_PUT_ERROR);
		}
		return ApiResponse.success(Boolean.TRUE);
	}
	@ApiOperation(value="通过key获取值")
	@RequestMapping(value="/get",method = {RequestMethod.GET})
	public ApiResponse<String> get(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key) {
		try {
			return ApiResponse.success(redisService.get(key));
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_GET_ERROR);
		}
	}
	
	@ApiOperation(value="查看以key开头所有key")
	@RequestMapping(value="/begin/keys/get",method = {RequestMethod.GET})
	public ApiResponse<Set<String>> getKeys4begin(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key) {
		try {
			return ApiResponse.success(redisService.getKeys4begin(key));
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_GET_ERROR);
		}
	}
	
	@ApiOperation(value="通过key删除值")
	@RequestMapping(value="/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key) {
		try {
			redisService.remove(key);
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
		}
		return ApiResponse.success(Boolean.TRUE);
	}
	
	
	@ApiOperation(value="删除以key开头的值")
	@RequestMapping(value="/begin/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove4begin(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key) {
		try {
			redisService.remove4begin(key);
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
		}
		return ApiResponse.success(Boolean.TRUE);
	}
	
	
	@ApiOperation(value="删除以key结尾的值")
	@RequestMapping(value="/end/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove4end(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key) {
		try {
			redisService.remove4end(key);
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
		}
		return ApiResponse.success(Boolean.TRUE);
	}
	
	
	@ApiOperation(value="删除包含key的值")
	@RequestMapping(value="/like/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove4like(@ApiParam(value = "key",required = true) @RequestParam(required = true) String key) {
		try {
			redisService.remove4like(key);
		}catch (Exception e) {
			return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
		}
		return ApiResponse.success(Boolean.TRUE);
	}
}
