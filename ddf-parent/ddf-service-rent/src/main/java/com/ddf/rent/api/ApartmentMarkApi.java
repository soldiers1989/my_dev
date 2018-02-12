/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.rent.service.simple.ApartmentMarkService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * apartment_mark Api
 * 
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class ApartmentMarkApi extends BaseApi {

	@Autowired
	private ApartmentMarkService apartmentMarkService;

	@ApiOperation(value = "查询单个ApartmentMark")
	@RequestMapping(value = "/apartmentMark/query", method = { RequestMethod.GET })
	public ApiResponse<ApartmentMark> query(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentMarkService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "创建ApartmentMark")
	@RequestMapping(value = "/apartmentMark/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(
			@ApiParam(value = "apartmentMark", required = false) @RequestBody(required = false) ApartmentMark apartmentMark,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentMarkService.createApartmentMark(apartmentMark));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "修改ApartmentMark信息")
	@RequestMapping(value = "/apartmentMark/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(
			@ApiParam(value = "apartmentMark", required = false) @RequestBody(required = false) ApartmentMark apartmentMark,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentMarkService.modify(apartmentMark));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "删除ApartmentMark信息")
	@RequestMapping(value = "/apartmentMark/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentMarkService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	@ApiOperation(value = "我的收藏")
	@RequestMapping(value = "/apartmentMark/my/pagequery", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentMarkVo>> pagequery4my(
			@ApiParam(value = "apartmentMarkQuery", required = false) @RequestBody(required = false) ApartmentMarkQuery apartmentMarkQuery,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentMarkService.pagequery4my(apartmentMarkQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value = "收藏列表")
	@RequestMapping(value = "/apartmentMark/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentMarkVo>> list(
			@ApiParam(value = "apartmentMarkQuery", required = false) @RequestBody(required = false) ApartmentMarkQuery apartmentMarkQuery) {
		try {
			return ApiResponse.success(apartmentMarkService.list(apartmentMarkQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}