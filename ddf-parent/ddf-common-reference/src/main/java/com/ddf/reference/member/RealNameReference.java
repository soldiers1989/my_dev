package com.ddf.reference.member;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.member.dto.RealName;
import com.ddf.entity.member.query.RealNameQuery;
import com.ddf.entity.member.vo.RealNameVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-member"/*,fallback = RealNameReferenceFallback.class*/)
public interface RealNameReference {
	
	@ApiOperation(value="查询单个 实名认证")
	@RequestMapping(value = "/realName/query",method = {RequestMethod.GET})
	public ApiResponse<RealName> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 实名认证")
	@RequestMapping(value = "/realName/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(RealName realName);
	
	@ApiOperation(value="修改 实名认证 信息")
	@RequestMapping(value = "/realName/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(RealName realName);
	
	@ApiOperation(value="删除 实名认证 信息")
	@RequestMapping(value = "/realName/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);
	
	@ApiOperation(value="实名认证列表")
	@RequestMapping(value = "/realName/realNameQuery/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<RealNameVo>> batchqueryByRealNameQuery(RealNameQuery realNameQuery);
	
	@ApiOperation(value="我的 实名认证")
	@RequestMapping(value = "/realName/my/query",method = {RequestMethod.GET})
	public ApiResponse<RealNameVo> query4user( @RequestParam(value = "currentUserId") String currentUserId);
}
