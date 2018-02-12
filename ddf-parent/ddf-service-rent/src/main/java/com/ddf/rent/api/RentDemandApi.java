/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.eo.RentDemandMatchStatus;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.rent.service.simple.RentDemandService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * rent_demand Api
 * 
 * @author robot
 * @version 2018-01-17
 */
@RestController
public class RentDemandApi extends BaseApi {

	@Autowired
	private RentDemandService rentDemandService;

	private static Logger logger = LoggerFactory.getLogger(RentDemandApi.class);

	@ApiOperation(value = "查询单个RentDemand")
	@RequestMapping(value = "/rentDemand/query", method = { RequestMethod.GET })
	public ApiResponse<RentDemand> query(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(rentDemandService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "创建RentDemand")
	@RequestMapping(value = "/rentDemand/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(
			@ApiParam(value = "rentDemand", required = false) @RequestBody(required = false) RentDemand rentDemand,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(rentDemandService.createRentDemand(rentDemand));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "修改RentDemand信息")
	@RequestMapping(value = "/rentDemand/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(
			@ApiParam(value = "rentDemand", required = false) @RequestBody(required = false) RentDemand rentDemand,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(rentDemandService.modifyRentDemand(rentDemand));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "删除RentDemand信息")
	@RequestMapping(value = "/rentDemand/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(rentDemandService.remove4id(id) > 0 ? true : false);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "订阅/停阅")
	@RequestMapping(value = "/rentDemand/matchStatus/{matchStatus}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> matchStatus(
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId,
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "matchStatus", required = true) @PathVariable("matchStatus") RentDemandMatchStatus matchStatus) {
		try {
			return ApiResponse.success(rentDemandService.matchStatus(id, matchStatus));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "我的需求")
	@RequestMapping(value = "/rentDemand/my/pagequery", method = { RequestMethod.POST })
	public ApiResponse<Page<RentDemandVo>> pagequery(
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId,
			@ApiParam(value = "rentDemandQuery", required = false) @RequestBody(required = false) RentDemandQuery rentDemandQuery) {
		try {
			return ApiResponse.success(rentDemandService.pagequery4userId(rentDemandQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "需求列表")
	@RequestMapping(value = "/rentDemand/list", method = { RequestMethod.POST })
	public ApiResponse<Page<RentDemandVo>> list(
			@ApiParam(value = "rentDemandQuery", required = false) @RequestBody(required = false) RentDemandQuery rentDemandQuery) {
		try {
			return ApiResponse.success(rentDemandService.list(rentDemandQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}