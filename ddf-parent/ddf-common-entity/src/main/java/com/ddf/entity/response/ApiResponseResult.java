package com.ddf.entity.response;

public enum ApiResponseResult {
	/***************************共有************************************************/
	SUCCESS("成功"),
	ERROR("系统异常"),
	
	
	COMMON_PARAM_ERROR("参数错误"),
    COMMON_PARAM_SIGN_ERROR("参数签名无效"),
    COMMON_USER_TOKEN_ERROR("user_token无效"),
    COMMON_TOKEN_USERID_ERROR("user_token和当前用户id不一致"),
    
    COMMON_ID_ERROR("获取ID失败"),
    COMMON_DATE_ERROR("获取当前时间失败"),
    COMMON_USER_BATCHQUERY_ERROR("获取用户列表失败"),
    BUSS_ERROR("业务异常"),
	
	/******************************会员*********************************************/
	MEMBER_LOGIN_PWD_ERROR("登录密码不正确"),
	MEMBER_LOGIN_ERROR("登录账号或密码不正确"),
	MEMBER_LOGIN_FROZEN_ERROR("该账号已冻结"),
	MEMBER_REGISTER_EXIST_ERROR("该账号已经注册"),
	MEMBER_NOTEXIST_ERROR("该账号不存在"),
	MEMBER_PAY_PWD_ERROR("支付密码不正确"),
	MEMBER_PWD_NOT_SAME("密码不一致正确"),
	MEMBER_REGISTER_ERROR("注册失败"),
	REALNAME_NOTEXIST_ERROR("该实名认证不存在"),
	MEMBER_MODIFY_MOBILE_EXIST_ERROR("该手机号码一被占用,修改失败"),
	MEMBER_MODIFY_MOBILE_ERROR("修改手机号失败"),
	MEMBER_MODIFY_MOBILE_SELF_ERROR("修改手机号不能与当前手机号一样"),
	/******************************租房*******************************************/
	RENT_SUCCESS("成功"),
	/******************************租房匹配********************************************/
	RENT_MATCH_SUCCESS("成功"),
	/******************************资金*********************************************/
	CAPITAL_SUCCESS("成功"),
	/******************************消息********************************************/
	MESSAGE_SUCCESS("成功"),
	/******************************文件系统********************************************/
	FILE_PUT_ERROR("保存失败"),
	FILE_GET_ERROR("获取失败"),
	FILE_REMOVE_ERROR("删除失败"),
	/*******************************短信********************************************/
	SMS_SUCCESS("成功"),
	SMS_ERROR("发送短信失败"),
	SMS_OVERTOP_HOUR_ERROR("本小时短信发送次数已达上限，请稍后再试！"),
	SMS_OVERTOP_DAY_ERROR("今日短信发送次数已达上限！"),
	SMS_IMGCODE_ERROR("图形验证码不正确"),
	SMS_CODE_ERROR("短信验证码错误");
	
	private String message;
    ApiResponseResult(String message) {
        this.message = message;
    }

    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
