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
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.eo.ApartmentAppointmentStatus;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.rent.service.simple.ApartmentAppointmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * apartment_appointment Api
 * 
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class ApartmentAppointmentApi extends BaseApi {

	@Autowired
	private ApartmentAppointmentService apartmentAppointmentService;

	@ApiOperation(value = "查询单个ApartmentAppointment")
	@RequestMapping(value = "/apartmentAppointment/query", method = { RequestMethod.GET })
	public ApiResponse<ApartmentAppointment> query(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentAppointmentService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "创建ApartmentAppointment")
	@RequestMapping(value = "/apartmentAppointment/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(
			@ApiParam(value = "apartmentAppointment", required = false) @RequestBody(required = false) ApartmentAppointment apartmentAppointment,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentAppointmentService.createApartmentAppointment(apartmentAppointment));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "修改ApartmentAppointment信息")
	@RequestMapping(value = "/apartmentAppointment/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(
			@ApiParam(value = "apartmentAppointment", required = false) @RequestBody(required = false) ApartmentAppointment apartmentAppointment,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentAppointmentService.modifyApartmentAppointment(apartmentAppointment));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "删除ApartmentAppointment信息")
	@RequestMapping(value = "/apartmentAppointment/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentAppointmentService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "取消预约/拒绝预约")
	@RequestMapping(value = "/apartmentAppointment/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId,
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "status", required = true) @PathVariable(value = "status") ApartmentAppointmentStatus status) {
		try {
			return ApiResponse.success(apartmentAppointmentService.review(id, status));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "我的预约")
	@RequestMapping(value = "/apartmentAppointment/my/pagequery", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentAppointmentVo>> pagequery4lodgerId(
			@ApiParam(value = "apartmentAppointmentQuery", required = false) @RequestBody(required = false) ApartmentAppointmentQuery apartmentAppointmentQuery,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId
			) {
		try {
			return ApiResponse.success(apartmentAppointmentService.pagequery4userId(apartmentAppointmentQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "租客预约")
	@RequestMapping(value = "/apartmentAppointment/lodger/pagequery", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentAppointmentVo>> pagequery4landlordId(
			@ApiParam(value = "apartmentAppointmentQuery", required = false) @RequestBody(required = false) ApartmentAppointmentQuery apartmentAppointmentQuery,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(apartmentAppointmentService.pagequery4userId(apartmentAppointmentQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value = "预约列表")
	@RequestMapping(value = "/apartmentAppointment/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentAppointmentVo>> list(
			@ApiParam(value = "apartmentAppointmentQuery", required = false) @RequestBody(required = false) ApartmentAppointmentQuery apartmentAppointmentQuery) {
		try {
			return ApiResponse.success(apartmentAppointmentService.list(apartmentAppointmentQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}