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

import com.ddf.capital.service.simple.BillService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.Bill;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * bill Api
 * @author robot
 * @version 2018-01-22
 */
@RestController
public class BillApi extends BaseApi{

	@Autowired
	private BillService billService;

	@ApiOperation(value="查询单个Bill")
	@RequestMapping(value = "/bill/query",method = {RequestMethod.GET})
	public ApiResponse<Bill> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(billService.query4id(id));
	}

	@ApiOperation(value="创建Bill")
	@RequestMapping(value = "/bill/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody Bill bill){
		return ApiResponse.success(billService.create(bill));
	}

	@ApiOperation(value="修改Bill信息")
	@RequestMapping(value = "/bill/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute Bill bill){
		return ApiResponse.success(billService.modify(bill));
	}

	@ApiOperation(value="删除Bill信息")
	@RequestMapping(value = "/bill/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(billService.remove(id));
	}
	
	@ApiOperation(value="分页查询bill")
	@RequestMapping(value = "/bill/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<BillVo>> pagequery(@RequestBody BillQuery billQuery) {
		try {
			Page<BillVo> page=billService.query4page(billQuery);
			return ApiResponse.success(page);
		} catch (Exception e) {
			logger.error("分页查询bill,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}