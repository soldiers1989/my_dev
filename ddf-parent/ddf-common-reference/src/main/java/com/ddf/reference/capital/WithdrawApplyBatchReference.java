package com.ddf.reference.capital;

import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawApplyBatch;
import com.ddf.entity.capital.query.WithdrawApplyBatchQuery;
import com.ddf.entity.capital.vo.WithdrawApplyBatchVo;
import com.ddf.entity.response.ApiResponse;

import io.swagger.annotations.ApiParam;

@FeignClient(value = "service-capital"/*,fallback = WithdrawApplyBatchReferenceFallback.class*/)
public interface WithdrawApplyBatchReference {
	
	@RequestMapping(value = "/withdrawApplyBatch/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<WithdrawApplyBatchVo>> pagequery( WithdrawApplyBatchQuery withdrawApplyBatchQuery);
	
	@RequestMapping(value = "/withdrawApplyBatch/create",method = {RequestMethod.GET})
	public ApiResponse<Boolean> create( @RequestParam("batchName")String batchName, @RequestParam("applyIds")String[] applyIds);
	
	@RequestMapping(value = "/withdrawApplyBatch/transfer/check",method = {RequestMethod.GET})
	public ApiResponse<Boolean> transferCheck(@RequestParam("batchId")String batchId);
	
	@RequestMapping(value = "/withdrawApplyBatch/transfer",method = {RequestMethod.GET})
	public ApiResponse<String> transfer(@RequestParam("alipayBatchNo")String alipayBatchNo,@RequestParam("batchId")String batchId);
	
	@RequestMapping(value = "/withdrawApplyBatch/remove",method = {RequestMethod.GET})
	public ApiResponse<Boolean> remove(@RequestParam("id")String id);
	
	@RequestMapping(value = "/withdrawApplyBatch/active",method = {RequestMethod.GET})
	public ApiResponse<Boolean> active(@RequestParam("id")String id);
	
	@RequestMapping(value = "/withdrawApplyBatch/query",method = {RequestMethod.GET})
	public ApiResponse<WithdrawApplyBatch> query( @RequestParam("id") String id);
}
