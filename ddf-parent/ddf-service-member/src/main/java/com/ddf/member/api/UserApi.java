/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.eo.AlipayStatus;
import com.ddf.entity.member.eo.AlipayZhimaCreditStatus;
import com.ddf.entity.member.query.UserQuery;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.member.service.simple.UserService;
import com.ddf.entity.member.eo.RealNameStatus;

/**
 * user Api
 * @author robot
 * @version 2018-01-05
 */
@Api(value = "UserApi", tags = "用户" )
@RestController
public class UserApi extends BaseApi{

	@Autowired
	private UserService userService;
	
	@Value("${member}")
	private String member;
	@Value("${ddf.datasource.username}")
	private String username;

	@ApiOperation(value="查询单个用户")
	@RequestMapping(value = "/user/query",method = {RequestMethod.GET})
	public ApiResponse<User> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(userService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个用户，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建用户")
	@RequestMapping(value = "/user/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody User user){
		try{
			boolean bool = userService.checkUserName(user.getUserName());
			if(bool){
				return ApiResponse.fail(ApiResponseResult.MEMBER_REGISTER_EXIST_ERROR);
			}
			return ApiResponse.success(userService.create(user));
		}catch(Exception e){
			logger.error("创建用户，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改用户信息（全部）")
	@RequestMapping(value = "/user/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody User user){
		try{
			User u = userService.query4id(user.getId());
			if(u==null){
				return ApiResponse.fail(ApiResponseResult.MEMBER_NOTEXIST_ERROR);
			}
			boolean bool1 = userService.checkUserName(user.getUserName());//判断账号是否存在
			//账号存在且不为自己提示错误
			if(bool1==true && !u.getUserName().equals(user.getUserName())){
				return ApiResponse.fail(ApiResponseResult.MEMBER_REGISTER_EXIST_ERROR);
			}
			return ApiResponse.success(userService.modify(user));
		}catch(Exception e){
			logger.error("修改用户信息（全部），异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除用户信息")
	@RequestMapping(value = "/user/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(userService.remove(id));
		}catch(Exception e){
			logger.error("删除用户信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	
	@ApiOperation(value="手机号查询单个用户")
	@RequestMapping(value = "/user/mobile/query",method = {RequestMethod.GET})
	public ApiResponse<UserVo> queryByMobile(@ApiParam(value = "手机号",required = true) @RequestParam(required = true) String mobile) {
		try{
			return ApiResponse.success(userService.queryByMobile(mobile));
		}catch(Exception e){
			logger.error("手机号查询单个用户，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="修改用户基本信息（不包括密码和状态设置、手机号、账号）")
	@RequestMapping(value = "/user/info/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modifyInfo(@RequestBody User user){
		try{
			return ApiResponse.success(userService.modifyInfo(user));
		}catch(Exception e){
			logger.error("修改用户基本信息（不包括密码和状态设置），异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="根据时间asc顺序 查询  用户列表")
	@RequestMapping(value = "/user/createDateAsc/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<UserVo>> batchqueryByCreateDateAsc(
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			Page<UserVo> page = userService.batchqueryByCreateDateAsc(pageNum, pageSize);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("根据时间asc顺序 查询  用户列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="用户列表")
	@RequestMapping(value = "/user/userQuery/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<UserVo>> batchqueryByUserQuery(
			@ApiParam(value = "用户参数",required = false) @RequestBody(required=false) UserQuery userQuery) {
		try{
			Page<UserVo> page = userService.query4page(userQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("查询  用户列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	@ApiOperation(value="验证账号是否注册")
	@RequestMapping(value = "/user/userName/check",method = {RequestMethod.GET})
	public  ApiResponse<Boolean> checkUserName(@ApiParam(value = "登录名",required = true) @RequestParam(required = true) String userName) {
		try{
			boolean bool = userService.checkUserName(userName);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("验证账号是否注册，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="注册")
	@RequestMapping(value = "/user/register",method = {RequestMethod.GET})
	public  ApiResponse<String> register(
				@ApiParam(value = "登录名",required = true) @RequestParam(required = true) String userName,
				@ApiParam(value = "密码",required = true) @RequestParam(required = true) String password,
				@ApiParam(value = "设备号",required = true) @RequestParam(required = true) String deviceNo) {
		try{
			if(userService.checkUserName(userName)){
				return ApiResponse.fail(ApiResponseResult.MEMBER_REGISTER_EXIST_ERROR);
			}
			UserVo currentUser = userService.register(userName,password,deviceNo);
			if(currentUser == null){
				return ApiResponse.fail(ApiResponseResult.MEMBER_REGISTER_ERROR);
			}
			String user_token = userService.cacheLoginUser(currentUser.getId(), deviceNo);
			return ApiResponse.success(user_token);
		}catch(Exception e){
			logger.error("注册，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="登录")
	@RequestMapping(value = "/user/login",method = {RequestMethod.GET})
	public ApiResponse<String> login(
			@ApiParam(value = "登录名",required = true) @RequestParam(required = true) String userName,
			@ApiParam(value = "密码",required = true) @RequestParam(required = true) String password,
			@ApiParam(value = "设备号",required = true) @RequestParam(required = true) String deviceNo) {
		try{
			UserVo currentUser = userService.login(userName, password);
			if(currentUser == null){
				return ApiResponse.fail(ApiResponseResult.MEMBER_LOGIN_ERROR);
			}
			if(userService.checkUserFrozen(userName)){
				return ApiResponse.fail(ApiResponseResult.MEMBER_LOGIN_FROZEN_ERROR);
			}
			String user_token = userService.cacheLoginUser(currentUser.getId(), deviceNo);
			return ApiResponse.success(user_token);
		}catch(Exception e){
			logger.error("登录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="根据user_token获得user对象")
	@RequestMapping(value = "/user/userToken/query",method = {RequestMethod.GET})
	public ApiResponse<User> queryUser4userToken(@ApiParam(value = "user_token",required = true) @RequestParam(required = true) String user_token){
		return ApiResponse.success(userService.queryUser4userToken(user_token));
	}
	
	@ApiOperation(value="退出登录")
	@RequestMapping(value = "/user/loginOut",method = {RequestMethod.GET})
	public ApiResponse<Boolean> loginOut(@ApiParam(value = "user_token",required = true) @RequestParam(required = true) String user_token) {
		try{
			boolean bool = userService.loginOut(user_token);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("退出登录，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="忘记密码重设密码")
	@RequestMapping(value = "/password/forget",method = {RequestMethod.GET})
	public ApiResponse<Boolean> forgetPassword(
			@ApiParam(value = "登录名",required = true) @RequestParam(required = true) String userName,
			@ApiParam(value = "密码",required = true) @RequestParam(required = true) String password) {
		try{
			if(!userService.checkUserName(userName)){
				return ApiResponse.fail(ApiResponseResult.MEMBER_NOTEXIST_ERROR);
			}
			boolean bool = userService.forgetPassword(userName,password);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("忘记密码重设密码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="修改密码")
	@RequestMapping(value = "/user/password/reset",method = {RequestMethod.GET})
	public ApiResponse<Boolean> resetPassword(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "新密码",required = true) @RequestParam(required = true) String newPassword) {
		try{
			if(userService.query4id(currentUserId)==null){
				return ApiResponse.fail(ApiResponseResult.MEMBER_NOTEXIST_ERROR);
			}
			boolean bool = userService.resetPassword(currentUserId,newPassword);
			if(bool==true){
				return ApiResponse.success(true);
			}else{
				return ApiResponse.fail(ApiResponseResult.COMMON_PARAM_ERROR);
			}
		}catch(Exception e){
			logger.error("修改密码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
//	@ApiOperation(value="修改密码")
//	@RequestMapping(value = "/user/password/reset",method = {RequestMethod.GET})
//	public ApiResponse<Boolean> resetPassword(
//			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
//			@ApiParam(value = "原密码",required = true) @RequestParam(required = true) String oldPassword,
//			@ApiParam(value = "新密码",required = true) @RequestParam(required = true) String newPassword) {
//		try{
//			if(userService.query4id(currentUserId)==null){
//				return ApiResponse.fail(ApiResponseResult.MEMBER_NOTEXIST_ERROR);
//			}
//			boolean bool = userService.resetPassword(currentUserId,oldPassword,newPassword);
//			if(bool==true){
//				return ApiResponse.success(true);
//			}else{
//				return ApiResponse.fail(ApiResponseResult.MEMBER_LOGIN_PWD_ERROR);
//			}
//		}catch(Exception e){
//			logger.error("修改密码，异常："+e.getMessage(),e);
//			return ApiResponse.fail(ApiResponseResult.ERROR);
//		}
//	}
	
	@ApiOperation(value="修改手机号")
	@RequestMapping(value = "/user/mobile/modify",method = {RequestMethod.GET})
	public ApiResponse<Boolean> modifyMobile(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "手机号",required = true) @RequestParam(required = true) String mobile){
		try{
			User user = userService.query4id(currentUserId);
			if(user==null){
				return ApiResponse.fail(ApiResponseResult.MEMBER_NOTEXIST_ERROR);
			}
			//判断手机号码是否为当前手机号码，是则提示错误
			if(mobile.equals(user.getMobile())){
				return ApiResponse.fail(ApiResponseResult.MEMBER_MODIFY_MOBILE_SELF_ERROR);
			}
			boolean bool1 = userService.checkUserName(mobile);//判断账号是否存在
			//手机号码已存在，提示错误
			if(bool1==true){
				return ApiResponse.fail(ApiResponseResult.MEMBER_MODIFY_MOBILE_EXIST_ERROR);
			}
			boolean bool = userService.modifyMobile(currentUserId,mobile);
			if(bool==true){
				return ApiResponse.success(true);
			}else{
				return ApiResponse.fail(ApiResponseResult.MEMBER_MODIFY_MOBILE_ERROR);
			}
		}catch(Exception e){
			logger.error("修改手机号，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="修改支付密码")
	@RequestMapping(value = "/user/payPassword/reset",method = {RequestMethod.GET})
	public ApiResponse<Boolean> resetPayPassword(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "原密码",required = true) @RequestParam(required = true) String oldPayPassword,
			@ApiParam(value = "新密码",required = true) @RequestParam(required = true) String newPayPassword) {
		try{
			if(userService.query4id(currentUserId)==null){
				return ApiResponse.fail(ApiResponseResult.MEMBER_NOTEXIST_ERROR);
			}
			boolean bool = userService.resetPayPassword(currentUserId,oldPayPassword,newPayPassword);
			if(bool==true){
				return ApiResponse.success(true);
			}else{
				return ApiResponse.fail(ApiResponseResult.MEMBER_PAY_PWD_ERROR);
			}
		}catch(Exception e){
			logger.error("修改支付密码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	

	@ApiOperation(value="设置支付密码")
	@RequestMapping(value = "/user/payPassword/set",method = {RequestMethod.GET})
	public ApiResponse<Boolean> setPayPassword(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "新密码",required = true) @RequestParam(required = true) String newPayPassword) {
		try{
			if(userService.query4id(currentUserId)==null){
				return ApiResponse.fail(ApiResponseResult.MEMBER_NOTEXIST_ERROR);
			}
			boolean bool = userService.setPayPassword(currentUserId,newPayPassword);
			if(bool==true){
				return ApiResponse.success(true);
			}else{
				return ApiResponse.fail(ApiResponseResult.MEMBER_PAY_PWD_ERROR);
			}
		}catch(Exception e){
			logger.error("设置支付密码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="设置实名状态")
	@RequestMapping(value = "/user/realNameFlag/set",method = {RequestMethod.GET})
	public ApiResponse<Boolean> setRealNameFlag(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "实名认证状态",required = true) @RequestParam(required = true) RealNameStatus realNameFlag) {
		try{
			boolean bool = userService.setRealNameFlag(currentUserId,realNameFlag);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("设置实名状态，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	
	@ApiOperation(value="设置芝麻信用状态")
	@RequestMapping(value = "/user/zhimaCreditFlag/set",method = {RequestMethod.GET})
	public ApiResponse<Boolean> setZhimaCreditFlag(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "芝麻信用状态",required = true) @RequestParam(required = true) AlipayZhimaCreditStatus zhimaCreditFlag) {
		try{
			boolean bool = userService.setZhimaCreditFlag(currentUserId,zhimaCreditFlag);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("设置芝麻信用状态，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="设置支付宝认证状态")
	@RequestMapping(value = "/user/alipayFlay/set",method = {RequestMethod.GET})
	public ApiResponse<Boolean> setAlipayFlay(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "支付宝认证状态",required = true) @RequestParam(required = true) AlipayStatus alipayStatus) {
		try{
			boolean bool = userService.setAlipayFlay(currentUserId,alipayStatus);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("设置支付宝认证状态，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="设置保证金状态")
	@RequestMapping(value = "/user/bondFlag/set",method = {RequestMethod.GET})
	public ApiResponse<Boolean> setBondFlag(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "租客保证",required = true) @RequestParam(required = true) boolean bondFlag) {
		try{
			boolean bool = userService.setBondFlag(currentUserId,bondFlag);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("设置保证金状态，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="设置接听状态")
	@RequestMapping(value = "/user/answerFlag/set",method = {RequestMethod.GET})
	public ApiResponse<Boolean> setAnswerFlag(
			@ApiParam(value = "当前用户ID",required = true) @RequestParam(required=true) String currentUserId,
			@ApiParam(value = "接听状态",required = true) @RequestParam(required = true) boolean answerFlag) {
		try{
			boolean bool = userService.setAnswerFlag(currentUserId,answerFlag);
			return ApiResponse.success(bool);
		}catch(Exception e){
			logger.error("设置接听状态，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
}