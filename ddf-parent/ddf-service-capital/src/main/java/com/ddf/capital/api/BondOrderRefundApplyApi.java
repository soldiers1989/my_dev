/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.capital.service.simple.BondOrderRefundApplyService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.BondOrderRefundApply;
import com.ddf.entity.capital.query.BondOrderRefundApplyQuery;
import com.ddf.entity.capital.vo.BondOrderRefundApplyVo;
import com.ddf.entity.exception.BusinessException;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * bond_order_refund_apply Api
 * @author robot
 * @version 2018-01-22
 */
@RestController
public class BondOrderRefundApplyApi extends BaseApi{

	@Autowired
	private BondOrderRefundApplyService bondOrderRefundApplyService;

	@ApiOperation(value="查询单个BondOrderRefundApply")
	@RequestMapping(value = "/bondOrderRefundApply/query",method = {RequestMethod.GET})
	public ApiResponse<BondOrderRefundApply> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(bondOrderRefundApplyService.query4id(id));
	}

	@ApiOperation(value="创建BondOrderRefundApply")
	@RequestMapping(value = "/bondOrderRefundApply/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody BondOrderRefundApply bondOrderRefundApply){
		try {
			bondOrderRefundApplyService.createOrder(bondOrderRefundApply);
			return ApiResponse.success(true);
		}catch (BusinessException e) {
			return ApiResponse.buildDefinedMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("创建退保证金订单异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="创建BondOrderRefundApply")
	@RequestMapping(value = "/bondOrderRefundApply/create",method = {RequestMethod.GET})
	public ApiResponse<Boolean> create(@ApiParam(value = "currentUserId",required = true) @RequestParam(required = true) String currentUserId,
			@ApiParam(value = "bondOrderId",required = true) @RequestParam(required = true) String bondOrderId){
		try {
			BondOrderRefundApply bondOrderRefundApply=new BondOrderRefundApply();
			bondOrderRefundApply.setBondOrderId(bondOrderId);
			bondOrderRefundApplyService.createOrder(bondOrderRefundApply);
			return ApiResponse.success(true);
		}catch (BusinessException e) {
			return ApiResponse.buildDefinedMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("创建退保证金订单异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改BondOrderRefundApply信息")
	@RequestMapping(value = "/bondOrderRefundApply/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute BondOrderRefundApply bondOrderRefundApply){
		return ApiResponse.success(bondOrderRefundApplyService.modify(bondOrderRefundApply));
	}

	@ApiOperation(value="删除BondOrderRefundApply信息")
	@RequestMapping(value = "/bondOrderRefundApply/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(bondOrderRefundApplyService.remove(id));
	}
	
	@ApiOperation(value="根据保证金id查找")
	@RequestMapping(value = "/bondOrderRefundApply/bondOrder/query",method = {RequestMethod.POST})
	public ApiResponse<BondOrderRefundApplyVo> queryBybondOrderId(@ApiParam(value = "bondOrderId",required = true) @RequestParam(required=true)String bondOrderId){
		try {
			BondOrderRefundApplyVo bondOrderRefundApplyVo=bondOrderRefundApplyService.query4bondOrderId(bondOrderId);
			return ApiResponse.success(bondOrderRefundApplyVo);
		} catch (Exception e) {
			logger.error("根据保证金id查找订单异常，异常信息："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="分页查询BondOrderRefundApply")
	@RequestMapping(value = "/bondOrderRefundApply/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<BondOrderRefundApplyVo>> batchquery4landlordId(@RequestBody BondOrderRefundApplyQuery bondOrderRefundApplyQuery) {
		try {
			Page<BondOrderRefundApplyVo> page=bondOrderRefundApplyService.query4page(bondOrderRefundApplyQuery);
			return ApiResponse.success(page);
		} catch (Exception e) {
			logger.error("分页查询BondOrderRefundApply,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

}