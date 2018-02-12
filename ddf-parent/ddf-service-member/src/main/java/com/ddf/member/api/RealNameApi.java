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

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.member.dto.RealName;
import com.ddf.entity.member.eo.RealNameStatus;
import com.ddf.entity.member.query.RealNameQuery;
import com.ddf.entity.member.vo.RealNameVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.member.service.simple.RealNameService;

/**
 * real_name Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "RealNameApi", tags = "实名认证" )
@RestController
public class RealNameApi extends BaseApi{

	@Autowired
	private RealNameService realNameService;

	@ApiOperation(value="查询单个 实名认证")
	@RequestMapping(value = "/realName/query",method = {RequestMethod.GET})
	public ApiResponse<RealName> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(realNameService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 实名认证，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 实名认证")
	@RequestMapping(value = "/realName/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody RealName realName){
		try{
			return ApiResponse.success(realNameService.create(realName));
		}catch(Exception e){
			logger.error("创建 实名认证，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 实名认证 信息")
	@RequestMapping(value = "/realName/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody RealName realName){
		try{
			return ApiResponse.success(realNameService.modify(realName));
		}catch(Exception e){
			logger.error("修改 实名认证 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 实名认证 信息")
	@RequestMapping(value = "/realName/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(realNameService.remove(id));
		}catch(Exception e){
			logger.error("删除 实名认证 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="我的 实名认证")
	@RequestMapping(value = "/realName/my/query",method = {RequestMethod.GET})
	public ApiResponse<RealNameVo> query4user(@ApiParam(value = "当前用户ID",required = true) @RequestParam(required = true) String currentUserId) {
		try{
			RealNameVo realNameVo = realNameService.query4user(currentUserId);
			return ApiResponse.success(realNameVo);
		}catch(Exception e){
			logger.error("我的 实名认证，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="实名认证 审核")
	@RequestMapping(value = "/realName/review",method = {RequestMethod.POST})
	public ApiResponse<Boolean> reviewPass(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id,
								@ApiParam(value = "status",required = true) @RequestParam(required = true) RealNameStatus status) {
		try{
			if(realNameService.query4id(id)==null){
				ApiResponse.fail(ApiResponseResult.REALNAME_NOTEXIST_ERROR);
			}
			Boolean bool = realNameService.updateStatus(id,status);
			if(bool==null){
				return ApiResponse.fail(ApiResponseResult.ERROR);
			}
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("实名认证 审核，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="实名认证列表")
	@RequestMapping(value = "/realName/realNameQuery/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<RealNameVo>> batchqueryByRealNameQuery(
			@ApiParam(value = "实名认证",required = false) @RequestBody(required=false) RealNameQuery realNameQuery) {
		try{
			Page<RealNameVo> page = realNameService.query4pageAndUser(realNameQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询  实名认证列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}