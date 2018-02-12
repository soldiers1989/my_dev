/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.match.api;

import com.ddf.entity.response.ApiResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.match.dto.ApartmentMatchRecord;
import com.ddf.entity.rent.match.query.ApartmentMatchRecordQuery;
import com.ddf.entity.rent.match.vo.ApartmentMatchRecordVo;
import com.ddf.rent.match.service.simple.ApartmentMatchRecordService;
import com.ddf.entity.response.ApiResponse;

/**
 * apartment_match_record Api
 * @author robot
 * @version 2018-02-05
 */
@RestController
public class ApartmentMatchRecordApi extends BaseApi{

	@Autowired
	private ApartmentMatchRecordService apartmentMatchRecordService;

	@ApiOperation(value="查询单个ApartmentMatchRecord")
	@RequestMapping(value = "/apartmentMatchRecord/query",method = {RequestMethod.GET})
	public ApiResponse<ApartmentMatchRecord> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(apartmentMatchRecordService.query4id(id));
	}

	@ApiOperation(value="创建ApartmentMatchRecord")
	@RequestMapping(value = "/apartmentMatchRecord/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody ApartmentMatchRecord apartmentMatchRecord){
		return ApiResponse.success(apartmentMatchRecordService.create(apartmentMatchRecord));
	}

	@ApiOperation(value="修改ApartmentMatchRecord信息")
	@RequestMapping(value = "/apartmentMatchRecord/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody ApartmentMatchRecord apartmentMatchRecord){
		return ApiResponse.success(apartmentMatchRecordService.modify(apartmentMatchRecord));
	}

	@ApiOperation(value="删除ApartmentMatchRecord信息")
	@RequestMapping(value = "/apartmentMatchRecord/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(apartmentMatchRecordService.remove(id));
	}

	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="查询符合租房需求的HouseMatchRecord列表")
	@RequestMapping(value = "/apartmentMatchRecord/rentDemand/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<ApartmentMatchRecordVo>> pagequery(@RequestBody ApartmentMatchRecordQuery query) {
		Page<ApartmentMatchRecordVo> matchRecordPage=null;
		try {
			matchRecordPage = apartmentMatchRecordService.query4page(query);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(matchRecordPage);
	}

	@ApiOperation(value="隐藏apartmentMatchRecord信息")
	@RequestMapping(value = "/apartmentMatchRecord/hide",method = {RequestMethod.POST})
	public ApiResponse<Boolean> hide(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			Boolean bool = apartmentMatchRecordService.hide4id(id);
			return ApiResponse.success(bool);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}