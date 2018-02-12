package com.ddf.common.api;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.member.dto.User;
import com.ddf.entity.response.ApiResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "DateApi", tags = "统一时间API" )
@RestController
public class DateApi extends BaseApi{

	private Logger logger =Logger.getLogger(DateApi.class);
	
	@ApiOperation(value="获取当前时间")
	@RequestMapping(value = "/date/current/query",method = {RequestMethod.GET})
	public ApiResponse<Date> getCurrentDate(){
		return ApiResponse.success(new Date());
	}
}
