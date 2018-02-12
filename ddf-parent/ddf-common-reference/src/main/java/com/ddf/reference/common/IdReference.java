package com.ddf.reference.common;

import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.response.ApiResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "service-common"/*,fallback = IdReferenceFallback.class*/)
public interface IdReference {
	
	//获取id
	@RequestMapping(value="/id/create",method = {RequestMethod.GET})
	ApiResponse<String> createId(@RequestParam(value = "tableName") TableName tableName);
}
