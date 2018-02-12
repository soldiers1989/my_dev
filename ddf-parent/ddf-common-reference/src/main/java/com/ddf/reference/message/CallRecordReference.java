package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.CallRecord;
import com.ddf.entity.message.query.CallRecordQuery;
import com.ddf.entity.message.vo.CallRecordVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = CallRecordReferenceFallback.class*/)
public interface CallRecordReference {

	@ApiOperation(value="查询单个 通话记录 ")
	@RequestMapping(value = "/callRecord/query",method = {RequestMethod.GET})
	public ApiResponse<CallRecord> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 通话记录")
	@RequestMapping(value = "/callRecord/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(CallRecord callRecord);

	@ApiOperation(value="修改 通话记录 信息")
	@RequestMapping(value = "/callRecord/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(CallRecord callRecord);

	@ApiOperation(value="删除 通话记录 信息")
	@RequestMapping(value = "/callRecord/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按被叫用户查询  通话记录 列表")
	@RequestMapping(value = "/callRecord/dstUserId/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4dstUserId(
			@RequestParam(value = "currentUserId") String currentUserId,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);
	
	@ApiOperation(value="按主叫用户查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/srcUserId/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4srcUserId(
			@RequestParam(value = "currentUserId") String currentUserId,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);
	
	@ApiOperation(value="按被叫号码查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/dstMobile/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4dstMobile(
			@RequestParam(value = "dstMobile") String dstMobile,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);
	
	@ApiOperation(value="按主叫号码查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/srcMobile/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<CallRecordVo>> query4srcMobile(
			@RequestParam(value = "srcMobile") String srcMobile,
			@RequestParam(value = "pageNum") int pageNum,
			@RequestParam(value = "pageSize") int pageSize);
	
	
	@ApiOperation(value="通话记录对象 查询  通话记录  列表")
	@RequestMapping(value = "/callRecord/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<CallRecordVo>> batchquery(CallRecordQuery callRecordQuery);
}
