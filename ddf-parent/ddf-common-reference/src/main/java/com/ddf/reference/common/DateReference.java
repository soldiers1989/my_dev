package com.ddf.reference.common;

import com.ddf.entity.response.ApiResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;


@FeignClient(value = "service-common"/*,fallback = DateReferenceFallback.class*/)
public interface DateReference {
	
	//获取当前时间
	@RequestMapping(value="/date/current/query",method = {RequestMethod.GET})
	ApiResponse<Date> queryCurrentDate();
}
