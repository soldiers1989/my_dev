/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.capital.dto.UserWallet;
import com.ddf.capital.service.simple.UserWalletService;
import com.ddf.entity.response.ApiResponse;

/**
 * user_wallet Api
 * @author robot
 * @version 2018-01-22
 */
@RestController
public class UserWalletApi extends BaseApi{

	@Autowired
	private UserWalletService userWalletService;

	@ApiOperation(value="查询单个UserWallet")
	@RequestMapping(value = "/userWallet/query",method = {RequestMethod.GET})
	public ApiResponse<UserWallet> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(userWalletService.query4id(id));
	}

	@ApiOperation(value="创建UserWallet")
	@RequestMapping(value = "/userWallet/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody UserWallet userWallet){
		return ApiResponse.success(userWalletService.create(userWallet));
	}

	@ApiOperation(value="修改UserWallet信息")
	@RequestMapping(value = "/userWallet/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute UserWallet userWallet){
		return ApiResponse.success(userWalletService.modify(userWallet));
	}

	@ApiOperation(value="删除UserWallet信息")
	@RequestMapping(value = "/userWallet/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(userWalletService.remove(id));
	}

}