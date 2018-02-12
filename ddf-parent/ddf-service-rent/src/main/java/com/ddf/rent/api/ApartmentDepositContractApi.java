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
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.rent.eo.ApartmentDepositContractStatus;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.rent.service.simple.ApartmentDepositContractService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * apartment_deposit_contract Api
 * 
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class ApartmentDepositContractApi extends BaseApi {

	@Autowired
	private ApartmentDepositContractService apartmentDepositContractService;

	@ApiOperation(value = "查询单个ApartmentDepositContract")
	@RequestMapping(value = "/apartmentDepositContract/query", method = { RequestMethod.GET })
	public ApiResponse<ApartmentDepositContract> query(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentDepositContractService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "创建ApartmentDepositContract")
	@RequestMapping(value = "/apartmentDepositContract/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(
			@ApiParam(value = "apartmentDepositContract", required = false) @RequestBody(required = false) ApartmentDepositContract apartmentDepositContract,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse
					.success(apartmentDepositContractService.createApartmentDepositContract(apartmentDepositContract));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "修改ApartmentDepositContract信息")
	@RequestMapping(value = "/apartmentDepositContract/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(
			@ApiParam(value = "apartmentDepositContract", required = false) @RequestBody(required = false) ApartmentDepositContract apartmentDepositContract,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse
					.success(apartmentDepositContractService.modifyApartmentDepositContract(apartmentDepositContract));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "删除ApartmentDepositContract信息")
	@RequestMapping(value = "/apartmentDepositContract/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(apartmentDepositContractService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value = "开启预定/关闭预定")
	@RequestMapping(value = "/apartmentDepositContract/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify4status(
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId,
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "status", required = true) @PathVariable(value="status") ApartmentDepositContractStatus status) {
		try {
			return ApiResponse.success(apartmentDepositContractService.review(id,status));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value = "预定列表")
	@RequestMapping(value = "/apartmentDepositContract/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentDepositContractVo>> list(
			@ApiParam(value = "apartmentDepositContract", required = false) @RequestBody(required = false) ApartmentDepositContractQuery apartmentDepositContractQuery) {
		try {
			return ApiResponse.success(apartmentDepositContractService.list(apartmentDepositContractQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}