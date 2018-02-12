/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ShareApartmentMatchStatus;
import com.ddf.entity.rent.eo.ShareApartmentStatus;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.rent.service.simple.ShareApartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * share_apartment Api
 * 
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class ShareApartmentApi extends BaseApi {

	@Autowired
	private ShareApartmentService shareApartmentService;

	@ApiOperation(value = "查询单个ShareApartment")
	@RequestMapping(value = "/shareApartment/query", method = { RequestMethod.GET })
	public ApiResponse<ShareApartment> query(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(shareApartmentService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "创建ShareApartment")
	@RequestMapping(value = "/shareApartment/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(
			@ApiParam(value = "shareApartment", required = false) @RequestBody(required = false) ShareApartment shareApartment,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(shareApartmentService.createShareApartment(shareApartment));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "修改ShareApartment信息")
	@RequestMapping(value = "/shareApartment/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(
			@ApiParam(value = "shareApartment", required = false) @RequestBody(required = false) ShareApartment shareApartment,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(shareApartmentService.modifyShareApartment(shareApartment));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "删除ShareApartment信息")
	@RequestMapping(value = "/shareApartment/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(shareApartmentService.remove4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value = "合租房间list")
	@RequestMapping(value = "/shareApartment/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ShareApartmentVo>> list(
			@ApiParam(value = "shareApartmentQuery", required = false) @RequestBody(required = false) ShareApartmentQuery shareApartmentQuery) {
		try {
			return ApiResponse.success(shareApartmentService.list(shareApartmentQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value = "合租房间招租/合租房间停租")
	@RequestMapping(value = "/shareApartment/matchStatus/{matchStatus}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> matchStatus(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "matchStatus", required = true) @PathVariable("matchStatus") ShareApartmentMatchStatus matchStatus) {
		try {
			return ApiResponse.success(shareApartmentService.matchStatus(id, matchStatus));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}	

	@ApiOperation(value = "合租房间审核通过/合租房间审核驳回")
	@RequestMapping(value = "/shareApartment/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "status", required = true) @PathVariable("status") ShareApartmentStatus status) {
		try {
			return ApiResponse.success(shareApartmentService.review(id, status));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}