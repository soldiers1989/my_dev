/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.capital.dto.CapCapitalAccount;
import com.ddf.capital.service.simple.CapCapitalAccountService;

/**
 * cap_capital_account Api
 * @author robot
 * @version 2018-01-10
 */
@RestController
public class CapCapitalAccountApi extends BaseApi{

	@Autowired
	private CapCapitalAccountService capCapitalAccountService;

	@ApiOperation(value="查询单个CapCapitalAccount")
	@RequestMapping(value = "/capCapitalAccount/query",method = {RequestMethod.GET})
	public CapCapitalAccount query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return capCapitalAccountService.query4id(id);
	}

	@ApiOperation(value="创建CapCapitalAccount")
	@RequestMapping(value = "/capCapitalAccount/create",method = {RequestMethod.POST})
	public boolean create(@ModelAttribute CapCapitalAccount capCapitalAccount){
		return capCapitalAccountService.create(capCapitalAccount);
	}

	@ApiOperation(value="修改CapCapitalAccount信息")
	@RequestMapping(value = "/capCapitalAccount/modify",method = {RequestMethod.POST})
	public boolean modify(@ModelAttribute CapCapitalAccount capCapitalAccount){
		return capCapitalAccountService.modify(capCapitalAccount);
	}

	@ApiOperation(value="删除CapCapitalAccount信息")
	@RequestMapping(value = "/capCapitalAccount/remove",method = {RequestMethod.POST})
	public boolean remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return capCapitalAccountService.remove(id);
	}

}