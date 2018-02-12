package com.ddf.reference.cache;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Set;

import com.ddf.entity.response.ApiResponse;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "service-cache"/*,fallback = CacheReferenceFallback.class*/)
public interface CacheReference {
	
	//存入redis
	@RequestMapping(value="/cache/put",method = {RequestMethod.POST})
	ApiResponse<Boolean> put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value);


	//存入redis加时间
	@RequestMapping(value="/cache/time/put",method = {RequestMethod.POST})
	ApiResponse<Boolean> put4time(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "seconds") int seconds);

	//通过key获取值
	@RequestMapping(value="/cache/get",method = {RequestMethod.GET})
	ApiResponse<String> get(@RequestParam(value = "key") String key);
	
	//查看以key开头所有keys
	@RequestMapping(value="/cache/begin/keys/get",method = {RequestMethod.GET})
	public ApiResponse<Set<String>> getKeys4begin(@RequestParam(value = "key") String key);


	//通过key删除值
	@RequestMapping(value="/cache/remove",method = {RequestMethod.POST})
	ApiResponse<Boolean> remove(@RequestParam(value = "key") String key);

	//删除以key开头的值
	@RequestMapping(value="/cache/begin/remove",method = {RequestMethod.POST})
	ApiResponse<Boolean> remove4begin(@RequestParam(value = "key") String key);

	//删除以key结尾的值
	@RequestMapping(value="/cache/end/remove",method = {RequestMethod.POST})
	ApiResponse<Boolean> remove4end(@RequestParam(value = "key") String key);


	//删除包含key的值
	@RequestMapping(value="/cache/like/remove",method = {RequestMethod.POST})
	ApiResponse<Boolean> remove4like(@RequestParam(value = "key") String key);
}
