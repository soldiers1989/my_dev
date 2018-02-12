/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.member.dto.BankCard;
import com.ddf.entity.member.vo.BankCardVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.member.service.simple.BankCardService;

/**
 * bank_card Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "BankCardApi", tags = " 用户银行卡" )
@RestController
public class BankCardApi extends BaseApi{

	@Autowired
	private BankCardService bankCardService;

	@ApiOperation(value="查询单个 用户银行卡")
	@RequestMapping(value = "/bankCard/query",method = {RequestMethod.GET})
	public ApiResponse<BankCard> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(bankCardService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 用户银行卡，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 用户银行卡")
	@RequestMapping(value = "/bankCard/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody BankCard bankCard){
		try{
			return ApiResponse.success(bankCardService.create(bankCard));
		}catch(Exception e){
			logger.error("创建 用户银行卡，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 用户银行卡 信息")
	@RequestMapping(value = "/bankCard/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody BankCard bankCard){
		try{
			return ApiResponse.success(bankCardService.modify(bankCard));
		}catch(Exception e){
			logger.error("修改 用户银行卡 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 用户银行卡 信息")
	@RequestMapping(value = "/bankCard/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(bankCardService.remove(id));
		}catch(Exception e){
			logger.error("删除 用户银行卡 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="我的 用户银行卡")
	@RequestMapping(value = "/bankCard/my/query",method = {RequestMethod.GET})
	public ApiResponse<List<BankCardVo>> query4user(@ApiParam(value = "当前用户ID",required = true) @RequestParam(required = true) String currentUserId) {
		try{
			List<BankCardVo> list = bankCardService.query4user(currentUserId);
			return ApiResponse.success(list);
		}catch(Exception e){
			logger.error("我的 用户银行卡，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
}