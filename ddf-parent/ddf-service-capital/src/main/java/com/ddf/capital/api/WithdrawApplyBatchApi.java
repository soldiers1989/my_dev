/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.capital.alipay.util.AlipayNotify;
import com.ddf.capital.service.simple.WithdrawApplyBatchService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawApplyBatch;
import com.ddf.entity.capital.eo.WithdrawApplyBatchStatus;
import com.ddf.entity.capital.query.WithdrawApplyBatchQuery;
import com.ddf.entity.capital.vo.WithdrawApplyBatchVo;
import com.ddf.entity.exception.BusinessException;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * withdraw_apply_batch Api
 * @author robot
 * @version 2018-02-02
 */
@RestController
public class WithdrawApplyBatchApi extends BaseApi{

	private static Logger logger=LoggerFactory.getLogger(WithdrawApplyBatchApi.class);
	
	@Autowired
	private WithdrawApplyBatchService withdrawApplyBatchService;

	@ApiOperation(value="查询单个WithdrawApplyBatch")
	@RequestMapping(value = "/withdrawApplyBatch/query",method = {RequestMethod.GET})
	public ApiResponse<WithdrawApplyBatch> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try {
			return ApiResponse.success(withdrawApplyBatchService.query4id(id));
		}catch (Exception e) {
			logger.error("查询单个WithdrawApplyBatch："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建WithdrawApplyBatch")
	@RequestMapping(value = "/withdrawApplyBatch/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@ModelAttribute WithdrawApplyBatch withdrawApplyBatch){
		return ApiResponse.success(withdrawApplyBatchService.create(withdrawApplyBatch));
	}

	@ApiOperation(value="修改WithdrawApplyBatch信息")
	@RequestMapping(value = "/withdrawApplyBatch/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute WithdrawApplyBatch withdrawApplyBatch){
		return ApiResponse.success(withdrawApplyBatchService.modify(withdrawApplyBatch));
	}

	@RequestMapping(value = "/withdrawApplyBatch/remove",method = {RequestMethod.GET})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try {
			withdrawApplyBatchService.remove4id(id);
			return ApiResponse.success(true);
		}catch (BusinessException e) {
			return ApiResponse.buildDefinedMessage(e.getMessage());
		}catch (Exception e) {
			logger.error("删除批次："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		} 
	}
	
	@ApiOperation(value="分页查询withdrawApplyBatch")
	@RequestMapping(value = "/withdrawApplyBatch/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<WithdrawApplyBatchVo>> pagequery(@RequestBody WithdrawApplyBatchQuery withdrawApplyBatchQuery) {
		try {
			Page<WithdrawApplyBatchVo> page=withdrawApplyBatchService.query4page(withdrawApplyBatchQuery);
			return ApiResponse.success(page);
		} catch (Exception e) {
			logger.error("分页查询withdrawApplyBatch,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@RequestMapping(value = "/withdrawApplyBatch/create",method = {RequestMethod.GET})
	public ApiResponse<Boolean> create(@RequestParam(required = true) String batchName,
										@RequestParam(required = true) String[] applyIds){
		try {
			withdrawApplyBatchService.create(batchName,applyIds);
			return ApiResponse.success(true);
		}catch (Exception e) {
			logger.error("创建提现批次异常,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		} 
		
	}
	
	@RequestMapping(value = "/withdrawApplyBatch/transfer/check",method = {RequestMethod.GET})
	public ApiResponse<Boolean> transferCheck(@RequestParam(required = true) String batchId){
		try {
			withdrawApplyBatchService.transferCheck(batchId);
			return ApiResponse.success(true);
		}catch (BusinessException e) {
			return ApiResponse.buildDefinedMessage(e.getMessage());
		}catch (Exception e) {
			logger.error("转账check异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		} 
		
	}
	
	@RequestMapping(value = "/withdrawApplyBatch/transfer",method = {RequestMethod.GET})
	public ApiResponse<String> transfer(@RequestParam(required = true) String alipayBatchNo,
										@RequestParam(required = true) String batchId){
		try {
			String form=withdrawApplyBatchService.transfer(alipayBatchNo,batchId);
			return ApiResponse.success(form);
		}catch (Exception e) {
			logger.error("转账异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		} 
		
	}
	
	@RequestMapping(value = "/withdrawApplyBatch/active",method = {RequestMethod.GET})
	public ApiResponse<Boolean> active(@RequestParam("id")String id){
		try {
			withdrawApplyBatchService.active(id);
			return ApiResponse.success(true);
		}catch (Exception e) {
			logger.error("激活异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		} 
	}
	
	@RequestMapping(value="/withdrawApplyBatch/alinotify",method={RequestMethod.GET,RequestMethod.POST})
	public void notify(HttpServletRequest request,HttpServletResponse response){
		logger.info("接收阿里批量支付异步通知开始");
		String batchNo = request.getParameter("batch_no");
		// 验证重复执行
		WithdrawApplyBatchQuery withdrawApplyBatchQuery=new WithdrawApplyBatchQuery();
		withdrawApplyBatchQuery.getWithdrawApplyBatch().setAlipayBatchNo(batchNo);
		WithdrawApplyBatch withdrawApplyBatch=null;
		List<WithdrawApplyBatchVo> withdrawApplyBatchVoList= withdrawApplyBatchService.findList(withdrawApplyBatchQuery);
		if(!ListUtil.isEmpty(withdrawApplyBatchVoList)) {
			withdrawApplyBatch=withdrawApplyBatchVoList.get(0);
		}
		if (withdrawApplyBatch==null||withdrawApplyBatch.getStatus() == WithdrawApplyBatchStatus.SUCCESS) {
			return;
		}
		try{
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//批量付款数据中转账成功的详细信息
			String success_details =request.getParameter("success_details");

			//批量付款数据中转账失败的详细信息
			String fail_details =request.getParameter("fail_details");

			List<String> alipayAccountList4success = getAlipayAccountList(success_details);
			List<String> alipayAccountList4fail = getAlipayAccountList(fail_details);
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			if(AlipayNotify.verify(params)){//验证成功
					
				//System.out.println("success");	//请不要修改或删除
				//System.out.println(success_details);	//请不要修改或删除
				withdrawApplyBatch.setStatus(WithdrawApplyBatchStatus.SUCCESS);
				withdrawApplyBatchService.modify(withdrawApplyBatch);
				withdrawApplyBatchService.batchTransferSuccess(withdrawApplyBatch.getId(), alipayAccountList4success);
				withdrawApplyBatchService.batchTransferFail(withdrawApplyBatch.getId(), alipayAccountList4fail);
			}else{//验证失败
				System.out.println("fail");
				System.out.println(fail_details);
			}
		}catch (Exception e) {
			logger.info("接收阿里批量支付异步通知异常,异常信息："+e.getMessage(),e);
		}finally{
				try {
					response.getWriter().write("success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		logger.info("接收阿里批量支付异步通知结束");
		}
	
	private List<String> getAlipayAccountList(String details){
		if(StringUtil.isEmpty(details)){
			return new ArrayList<String>();
		}
		String[] bigArr = details.split("\\|");
		List<String> alipayAccountList = new ArrayList<String>(); 
		for (String str : bigArr) {
			String[] smallArr = str.split("\\^");
			alipayAccountList.add(smallArr[1]);
		}
		return alipayAccountList;
	}

}