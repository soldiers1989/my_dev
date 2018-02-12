package com.ddf.cache.service.simple;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	private ValueOperations<String, String> valOpsStr;


	/**
	 * 根据指定key获取String
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return valOpsStr.get(key);
	}
	
	/**
	 * 查看以key开头所有key
	 * @param patten
	 */
	public Set<String> getKeys4begin(String patten) {
		return stringRedisTemplate.keys(patten+"*");
	}

	/**
	 * 设置Str缓存
	 * 
	 * @param key
	 * @param val
	 */
	public void put(String key, String val) {
		valOpsStr.set(key, val);
	}
	
	/**
	 * 设置Str缓存
	 * 
	 * @param key
	 * @param val
	 */
	public void put(String key, String val,int seconds) {
		valOpsStr.set(key, val, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 删除指定key
	 * 
	 * @param key
	 */
	public void remove(String key) {
		stringRedisTemplate.delete(key);
	}
	
	public void remove4begin(String patten) {
		Set<String> keys=stringRedisTemplate.keys(patten+"*");
		if(keys!=null&&keys.size()>0){
			stringRedisTemplate.delete(keys);
		}
	
	}

	public void remove4end(String patten) {
		Set<String> keys=stringRedisTemplate.keys("*"+patten);
		if(keys!=null&&keys.size()>0){
			stringRedisTemplate.delete(keys);
		}
	}

	public void remove4like(String patten) {
		Set<String> keys=stringRedisTemplate.keys("*"+patten+"*");
		if(keys!=null&&keys.size()>0){
			stringRedisTemplate.delete(keys);
		}
	}

}
