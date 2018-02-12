/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.capital.service.simple.WithdrawApplyService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawApply;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.vo.WithdrawApplyVo;
import com.ddf.entity.exception.BusinessException;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.member.UserReference;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * withdraw_apply Api
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class WithdrawApplyApi extends BaseApi{

	private static Logger logger=LoggerFactory.getLogger(WithdrawApplyApi.class);
	
	@Autowired
	private WithdrawApplyService withdrawApplyService;
	
	@Autowired
	private UserReference userReference;

	@ApiOperation(value="查询单个WithdrawApply")
	@RequestMapping(value = "/withdrawApply/query",method = {RequestMethod.GET})
	public ApiResponse<WithdrawApplyVo> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try {
			WithdrawApply withdrawApply=withdrawApplyService.query4id(id);
			WithdrawApplyVo withdrawApplyVo=new WithdrawApplyVo();
			BeanUtils.copyProperties(withdrawApply, withdrawApplyVo);
			withdrawApplyVo.setUser(userReference.query(withdrawApplyVo.getUserId()).getData());
			return ApiResponse.success(withdrawApplyVo);
		} catch (Exception e) {
			logger.error("查询单个WithdrawApply异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建WithdrawApply")
	@RequestMapping(value = "/withdrawApply/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute WithdrawApply withdrawApply){
		return ApiResponse.success(withdrawApplyService.create(withdrawApply));
	}
	
	@ApiOperation(value="提现申请")
	@RequestMapping(value = "/withdrawApply/apply",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ApiParam(value = "currentUserId",required = true) @RequestParam(required = true) String currentUserId,
										@ApiParam(value = "amount",required = true) @RequestParam(required = true) String amount){
		try {
			withdrawApplyService.create(currentUserId,new BigDecimal(amount));
			return ApiResponse.success(true);
		}catch (BusinessException e) {
			return ApiResponse.buildDefinedMessage(e.getMessage());
		}catch (Exception e) {
			logger.error("提现申请,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		} 
	}

	@ApiOperation(value="修改WithdrawApply信息")
	@RequestMapping(value = "/withdrawApply/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody WithdrawApply withdrawApply){
		try {
			return ApiResponse.success(withdrawApplyService.modify(withdrawApply));
		} catch (Exception e) {
			logger.error("修改WithdrawApply信息异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除WithdrawApply信息")
	@RequestMapping(value = "/withdrawApply/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(withdrawApplyService.remove(id));
	}

	@ApiOperation(value="分页查询withdrawApply")
	@RequestMapping(value = "/withdrawApply/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<WithdrawApplyVo>> pagequery(@RequestBody WithdrawApplyQuery withdrawApplyQuery) {
		try {
			Page<WithdrawApplyVo> page=withdrawApplyService.query4page(withdrawApplyQuery);
			return ApiResponse.success(page);
		} catch (Exception e) {
			logger.error("分页查询withdrawApply,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@RequestMapping(value = "/withdrawApply/4batchIdIsNull",method = {RequestMethod.GET})
	public ApiResponse<List<WithdrawApplyVo>> batchIdIsNull(@RequestParam(required=false)Date startDate,
														@RequestParam(required=false)Date endDate) {
		try {
			List<WithdrawApplyVo> page=withdrawApplyService.queryList4batchIdIsNull(startDate,endDate);
			return ApiResponse.success(page);
		} catch (Exception e) {
			logger.error("查询4batchIdIsNull,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@RequestMapping(value = "/withdrawApply/query/sum/user",method = {RequestMethod.GET})
	public ApiResponse<List<WithdrawApplyVo>> querySumUser(@RequestParam(required=true)String batchId,
											@RequestParam(required=false)String mobile){
		try {
			return ApiResponse.success(withdrawApplyService.querySumUser(batchId,mobile));
		}catch (Exception e) {
			logger.error("按人明细查询异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		} 
	}
}