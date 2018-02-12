package com.ddf.reference.capital;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawApply;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.vo.WithdrawApplyVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-capital"/*,fallback = WithdrawApplyReferenceFallback.class*/)
public interface WithdrawApplyReference {
	
	@RequestMapping(value = "/withdrawApply/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<WithdrawApplyVo>> pagequery( WithdrawApplyQuery withdrawApplyQuery);
	
	@RequestMapping(value = "/withdrawApply/query",method = {RequestMethod.GET})
	public ApiResponse<WithdrawApplyVo> query(@RequestParam("id")String id);
	
	@RequestMapping(value = "/withdrawApply/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(WithdrawApply withdrawApply);
	
	@RequestMapping(value = "/withdrawApply/4batchIdIsNull",method = {RequestMethod.GET})
	public ApiResponse<List<WithdrawApplyVo>> batchIdIsNull(@RequestParam("startDate")Date startDate,@RequestParam("endDate")Date endDate);
	
	@RequestMapping(value = "/withdrawApply/query/sum/user",method = {RequestMethod.GET})
	public ApiResponse<List<WithdrawApplyVo>> querySumUser(@RequestParam("batchId")String batchId,@RequestParam("mobile")String mobile);
	
	
}
