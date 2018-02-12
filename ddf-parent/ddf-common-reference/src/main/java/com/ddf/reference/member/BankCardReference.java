package com.ddf.reference.member;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.member.dto.BankCard;
import com.ddf.entity.member.vo.BankCardVo;
import com.ddf.entity.response.ApiResponse;


@FeignClient(value = "service-member"/*,fallback = BankCardReferenceFallback.class*/)
public interface BankCardReference {

	@ApiOperation(value="查询单个 用户银行卡")
	@RequestMapping(value = "/bankCard/query",method = {RequestMethod.GET})
	public ApiResponse<BankCard> query(@RequestParam(value= "id") String id);
	
	@ApiOperation(value="创建 用户银行卡")
	@RequestMapping(value = "/bankCard/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody BankCard bankCard);
	
	@ApiOperation(value="修改 用户银行卡 信息")
	@RequestMapping(value = "/bankCard/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody BankCard bankCard);
	
	@ApiOperation(value="删除 用户银行卡 信息")
	@RequestMapping(value = "/bankCard/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);
	
	@ApiOperation(value="我的 用户银行卡")
	@RequestMapping(value = "/bankCard/my/query",method = {RequestMethod.GET})
	public ApiResponse<List<BankCardVo>> query4user(@RequestParam(value = "currentUserId") String currentUserId);
	
	
}
