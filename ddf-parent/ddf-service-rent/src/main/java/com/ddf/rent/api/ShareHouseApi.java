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
import com.ddf.entity.rent.dto.ShareHouse;
import com.ddf.entity.rent.eo.ShareHouseStatus;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.rent.service.simple.ShareHouseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * share_house Api
 * 
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class ShareHouseApi extends BaseApi {

	@Autowired
	private ShareHouseService shareHouseService;

	@ApiOperation(value = "查询单个ShareHouse")
	@RequestMapping(value = "/shareHouse/query", method = { RequestMethod.GET })
	public ApiResponse<ShareHouse> query(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(shareHouseService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "创建ShareHouse")
	@RequestMapping(value = "/shareHouse/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(
			@ApiParam(value = "shareHouse", required = false) @RequestBody(required = false) ShareHouse shareHouse,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(shareHouseService.createShareHouse(shareHouse));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "修改ShareHouse信息")
	@RequestMapping(value = "/shareHouse/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(
			@ApiParam(value = "shareHouse", required = false) @RequestBody(required = false) ShareHouse shareHouse,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(shareHouseService.modifyShareHouse(shareHouse));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "删除ShareHouse信息--级联删除合租房间")
	@RequestMapping(value = "/shareHouse/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(shareHouseService.remove4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value = "我发布的合租房源")
	@RequestMapping(value = "/shareHouse/my/pagequery", method = { RequestMethod.POST })
	public ApiResponse<Page<ShareHouseVo>> pagequery4my(
			@ApiParam(value = "shareHouseQuery", required = false) @RequestBody(required = false) ShareHouseQuery shareHouseQuery,
			@ApiParam(value = "currentUserId", required = true) @RequestParam(required = true) String currentUserId) {
		try {
			return ApiResponse.success(shareHouseService.pagequery4my(shareHouseQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "合租房源列表")
	@RequestMapping(value = "/shareHouse/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ShareHouseVo>> list(
			@ApiParam(value = "shareHouseQuery", required = false) @RequestBody(required = false) ShareHouseQuery shareHouseQuery) {
		try {
			return ApiResponse.success(shareHouseService.list(shareHouseQuery));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value = "合租房源审核通过/合租房源审核驳回")
	@RequestMapping(value = "/shareHouse/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(
			@ApiParam(value = "id", required = true) @RequestParam(required = true) String id,
			@ApiParam(value = "status", required = true) @PathVariable("status") ShareHouseStatus status) {
		try {
			return ApiResponse.success(shareHouseService.review(id, status));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}	

}