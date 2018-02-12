/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.member.dto.AlipayZhimaCredit;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.member.service.simple.AlipayZhimaCreditService;

/**
 * alipay_zhima_credit Api
 * @author robot
 * @version 2018-01-15
 */
@Api(value = "AlipayZhimaCreditApi", tags = "芝麻信用" )
@RestController
public class AlipayZhimaCreditApi extends BaseApi{

	@Autowired
	private AlipayZhimaCreditService alipayZhimaCreditService;

	@ApiOperation(value="查询单个 芝麻信用")
	@RequestMapping(value = "/alipayZhimaCredit/query",method = {RequestMethod.GET})
	public ApiResponse<AlipayZhimaCredit> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(alipayZhimaCreditService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 芝麻信用，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 芝麻信用")
	@RequestMapping(value = "/alipayZhimaCredit/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody AlipayZhimaCredit alipayZhimaCredit){
		try{
			return ApiResponse.success(alipayZhimaCreditService.create(alipayZhimaCredit));
		}catch(Exception e){
			logger.error("创建 芝麻信用，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 芝麻信用 信息")
	@RequestMapping(value = "/alipayZhimaCredit/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody AlipayZhimaCredit alipayZhimaCredit){
		try{
			return ApiResponse.success(alipayZhimaCreditService.modify(alipayZhimaCredit));
		}catch(Exception e){
			logger.error("修改 芝麻信用 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 芝麻信用 信息")
	@RequestMapping(value = "/alipayZhimaCredit/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(alipayZhimaCreditService.remove(id));
		}catch(Exception e){
			logger.error("删除 芝麻信用 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}