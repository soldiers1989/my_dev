/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.capital.dto.CapTradeOrder;
import com.ddf.capital.service.simple.CapTradeOrderService;

/**
 * cap_trade_order Api
 * @author robot
 * @version 2018-01-10
 */
@RestController
public class CapTradeOrderApi extends BaseApi{

	@Autowired
	private CapTradeOrderService capTradeOrderService;

	@ApiOperation(value="查询单个CapTradeOrder")
	@RequestMapping(value = "/capTradeOrder/query",method = {RequestMethod.GET})
	public CapTradeOrder query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return capTradeOrderService.query4id(id);
	}

	@ApiOperation(value="创建CapTradeOrder")
	@RequestMapping(value = "/capTradeOrder/create",method = {RequestMethod.POST})
	public boolean create(@ModelAttribute CapTradeOrder capTradeOrder){
		return capTradeOrderService.create(capTradeOrder);
	}

	@ApiOperation(value="修改CapTradeOrder信息")
	@RequestMapping(value = "/capTradeOrder/modify",method = {RequestMethod.POST})
	public boolean modify(@ModelAttribute CapTradeOrder capTradeOrder){
		return capTradeOrderService.modify(capTradeOrder);
	}

	@ApiOperation(value="删除CapTradeOrder信息")
	@RequestMapping(value = "/capTradeOrder/remove",method = {RequestMethod.POST})
	public boolean remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return capTradeOrderService.remove(id);
	}

}