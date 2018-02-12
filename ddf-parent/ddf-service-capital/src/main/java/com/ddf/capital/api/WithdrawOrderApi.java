/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.capital.service.simple.WithdrawOrderService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawOrder;
import com.ddf.entity.capital.query.WithdrawOrderQuery;
import com.ddf.entity.capital.vo.WithdrawOrderVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * withdraw_order Api
 * @author robot
 * @version 2018-01-22
 */
@RestController
public class WithdrawOrderApi extends BaseApi{

	@Autowired
	private WithdrawOrderService withdrawOrderService;
	
	@ApiOperation(value="查询单个WithdrawOrder")
	@RequestMapping(value = "/withdrawOrder/query",method = {RequestMethod.GET})
	public ApiResponse<WithdrawOrder> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(withdrawOrderService.query4id(id));
	}

	@ApiOperation(value="创建WithdrawOrder")
	@RequestMapping(value = "/withdrawOrder/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody WithdrawOrder withdrawOrder){
		try {
			withdrawOrderService.createOrder(withdrawOrder);
			return ApiResponse.success(true);
		} catch (Exception e) {
			logger.error("创建WithdrawOrder异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改WithdrawOrder信息")
	@RequestMapping(value = "/withdrawOrder/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute WithdrawOrder withdrawOrder){
		return ApiResponse.success(withdrawOrderService.modify(withdrawOrder));
	}

	@ApiOperation(value="删除WithdrawOrder信息")
	@RequestMapping(value = "/withdrawOrder/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(withdrawOrderService.remove(id));
	}

	@ApiOperation(value="分页查询withdrawOrder")
	@RequestMapping(value = "/withdrawOrder/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<WithdrawOrderVo>> batchquery4landlordId(@RequestBody WithdrawOrderQuery withdrawOrderQuery) {
		try {
			Page<WithdrawOrderVo> page=withdrawOrderService.query4page(withdrawOrderQuery);
			return ApiResponse.success(page);
		} catch (Exception e) {
			logger.error("分页查询withdrawOrder,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}