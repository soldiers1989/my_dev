/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.rent.match.api;

import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;
import com.ddf.entity.rent.match.query.RentDemandMatchRecordQuery;
import com.ddf.entity.rent.match.vo.RentDemandMatchRecordVo;
import com.ddf.rent.match.service.simple.RentDemandMatchRecordService;

/**
 * rent_demand_match_record Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "RentDemandMatchRecordApi", tags = "需求匹配" )
@RestController
public class RentDemandMatchRecordApi extends BaseApi{

	@Autowired
	private RentDemandMatchRecordService rentDemandMatchRecordService;

	@ApiOperation(value="查询单个RentDemandMatchRecord")
	@RequestMapping(value = "/rentDemandMatchRecord/query",method = {RequestMethod.GET})
	public ApiResponse<RentDemandMatchRecord> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(rentDemandMatchRecordService.query4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建RentDemandMatchRecord")
	@RequestMapping(value = "/rentDemandMatchRecord/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody RentDemandMatchRecord rentDemandMatchRecord){
		try {
			Boolean bool = rentDemandMatchRecordService.createRentDemandMatchRecord(rentDemandMatchRecord);
			return ApiResponse.success(bool);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改RentDemandMatchRecord信息")
	@RequestMapping(value = "/rentDemandMatchRecord/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody RentDemandMatchRecord rentDemandMatchRecord){
		try {
			return ApiResponse.success(rentDemandMatchRecordService.modify(rentDemandMatchRecord));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除RentDemandMatchRecord信息")
	@RequestMapping(value = "/rentDemandMatchRecord/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			return ApiResponse.success(rentDemandMatchRecordService.remove(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="查询符房子的的RentDemandMatchRecord列表")
	@RequestMapping(value = "/rentDemandMatchRecord/house/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<RentDemandMatchRecordVo>> pagequery(@RequestBody RentDemandMatchRecordQuery query) {
		Page<RentDemandMatchRecordVo> matchRecordPage=null;
		try {
			matchRecordPage = rentDemandMatchRecordService.query4page(query);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
		return ApiResponse.success(matchRecordPage);
	}
	
	@ApiOperation(value="隐藏RentDemandMatchRecord信息")
	@RequestMapping(value = "/rentDemandMatchRecord/hide",method = {RequestMethod.POST})
	public ApiResponse<Boolean> hide(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			return ApiResponse.success(rentDemandMatchRecordService.hide4id(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="查询符合房子的的RentDemandMatchRecord列表，以租客聚合")
	@RequestMapping(value = "/rentDemandMatchRecord/house/groupbylodgerId/pagequery",method = {RequestMethod.GET})
	public ApiResponse<Page<RentDemandMatchRecordVo>> query4houseId4groupbylodgerId(@ApiParam(value = "pageNum",required = true) @RequestParam(required = true) int pageNum,
																	 @ApiParam(value = "pageSize",required = true) @RequestParam(required = true) int pageSize,
																	 @ApiParam(value = "houseId",required = true) @RequestParam(required = true) String houseId) {
		try {
			Page<RentDemandMatchRecordVo> demandMatchRecordVoPage = rentDemandMatchRecordService.query4houseId4groupbylodgerId(pageNum,pageSize,houseId);
			return ApiResponse.success(demandMatchRecordVoPage);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="隐藏RentDemandMatchRecord信息，按租客隐藏")
	@RequestMapping(value = "/rentDemandMatchRecord/lodger/hide",method = {RequestMethod.GET})
	public ApiResponse<Boolean> hide4lodgerId(@ApiParam(value = "lodgerId",required = true) @RequestParam(required=true)String lodgerId){
		try {
			Boolean aBoolean = rentDemandMatchRecordService.hide4lodgerId(lodgerId);
			return ApiResponse.success(aBoolean);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}