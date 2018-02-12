package com.ddf.reference.member;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.query.UserQuery;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-member"/*,fallback = UserReferenceFallback.class*/)
public interface UserReference {
	
	@ApiOperation(value="查询单个用户")
	@RequestMapping(value = "/user/query",method = {RequestMethod.GET})
	public ApiResponse<User> query(@RequestParam(value="id") String id);
	
	@ApiOperation(value="创建用户")
	@RequestMapping(value = "/user/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create( User user);
	
	@ApiOperation(value="修改用户信息（全部）")
	@RequestMapping(value = "/user/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify( User user);
	
	@ApiOperation(value="删除用户信息")
	@RequestMapping(value = "/user/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

	@ApiOperation(value="手机号查询单个用户")
	@RequestMapping(value = "/user/mobile/query",method = {RequestMethod.GET})
	public ApiResponse<UserVo> queryByMobile(@RequestParam(value = "mobile") String mobile);

	@ApiOperation(value="根据时间asc顺序 查询  用户列表")
	@RequestMapping(value="/user/createDateAsc/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<UserVo>> batchqueryByCreateDateAsc(@RequestParam(value = "pageNum") int pageNum,@RequestParam(value = "pageSize") int pageSize);
	
	@ApiOperation(value="用户列表")
	@RequestMapping(value = "/user/userQuery/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<UserVo>> batchqueryByUserQuery(UserQuery userQuery);
	
	@ApiOperation(value="设置保证金状态")
	@RequestMapping(value = "/user/bondFlag/set",method = {RequestMethod.GET})
	ApiResponse<Boolean> setBondFlag(@RequestParam(value="currentUserId") String currentUserId,
			@RequestParam(value="bondFlag") boolean bondFlag);
	
	@ApiOperation(value="设置接听状态")
	@RequestMapping(value = "/user/answerFlag/set",method = {RequestMethod.GET})
	public ApiResponse<Boolean> setAnswerFlag(@RequestParam(value = "currentUserId") String currentUserId,@RequestParam(value = "answerFlag") boolean answerFlag);
	
}
