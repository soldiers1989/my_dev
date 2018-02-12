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
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.eo.ApartmentMatchStatus;
import com.ddf.entity.rent.eo.ApartmentStatus;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.rent.service.simple.ApartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * apartment Api
 * 
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class ApartmentApi extends BaseApi {

	@Autowired
	private ApartmentService apartmentService;

	@ApiOperation(value = "查询单个Apartment")
	@RequestMapping(value = "/apartment/query", method = { RequestMethod.GET })
	public ApiResponse<Apartment> query(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "创建Apartment")
	@RequestMapping(value = "/apartment/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(
			@ApiParam(value = "apartment", required = false) @RequestBody(required = false) Apartment apartment,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentService.createApartment(apartment));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "修改Apartment信息")
	@RequestMapping(value = "/apartment/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(
			@ApiParam(value = "apartment", required = false) @RequestBody(required = false) Apartment apartment,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentService.modifyApartment(apartment));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "删除Apartment信息")
	@RequestMapping(value = "/apartment/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentService.remove4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "我发布的整租房源")
	@RequestMapping(value = "/apartment/my/pagequery", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentVo>> pagequery4my(
			@ApiParam(value = "apartmentQuery", required = false) @RequestBody(required = false) ApartmentQuery apartmentQuery,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentService.pagequery4my(apartmentQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "招租/停租")
	@RequestMapping(value = "/apartment/matchStatus/{matchStatus}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> matchStatus(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "matchStatus", required = true) @PathVariable("matchStatus") ApartmentMatchStatus matchStatus) {
		try {
			return ApiResponse.success(apartmentService.matchStatus(id, matchStatus));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "整租房源列表")
	@RequestMapping(value = "/apartment/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentVo>> list(
			@ApiParam(value = "apartmentQuery", required = false) @RequestBody(required = false) ApartmentQuery apartmentQuery) {
		try {
			return ApiResponse.success(apartmentService.list(apartmentQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "整租房源审核通过/整租房源审核驳回")
	@RequestMapping(value = "/apartment/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "status", required = true) @PathVariable("status") ApartmentStatus status) {
		try {
			return ApiResponse.success(apartmentService.review(id, status));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}