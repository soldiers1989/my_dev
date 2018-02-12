package com.ddf.entity.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.ddf.entity.base.dto.DataEntity;
import com.ddf.entity.member.eo.AlipayStatus;
import com.ddf.entity.member.eo.AlipayZhimaCreditStatus;
import com.ddf.entity.member.eo.RealNameStatus;
import com.ddf.entity.member.eo.RealNameType;
import com.ddf.entity.member.eo.UserSex;
import com.ddf.entity.member.eo.UserStatus;
/**
 * user Entity
 * @author robot
 * @version 2018-01-31
 */
@ApiModel(description = "User")
public class User extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户名")
	private String userName;
	@ApiModelProperty(value = "用户密码")
	private String password;
	@ApiModelProperty(value = "支付密码")
	private String payPassword;
	@ApiModelProperty(value = "手机号码(必须唯一)")
	private String mobile;
	@ApiModelProperty(value = "头像")
	private String headImg;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "枚举类型,性别（男，女）")
	private UserSex sex;
	@ApiModelProperty(value = "生日")
	private Date birthday;
	@ApiModelProperty(value = "行业(字典选)")
	private String industry;
	@ApiModelProperty(value = "个性签名")
	private String personalitySign;
	@ApiModelProperty(value = "个人画像(生活方式、关于个人、兴趣爱好、饮食习惯 标签多选)")
	private String personas;
	@ApiModelProperty(value = "邀请人(手机号码)")
	private String inviter;
	@ApiModelProperty(value = "枚举类型,芝麻信用状态 认证中、已驳回、已认证 对应芝麻信用表状态")
	private AlipayZhimaCreditStatus zhimaCreditFlag;
	@ApiModelProperty(value = "枚举类型,业务类型（个人-personal，企业-company）")
	private RealNameType businessType;
	@ApiModelProperty(value = "枚举类型,实名认证状态:未审核，审核通过，驳回  对应实名认证表状态")
	private RealNameStatus realNameFlag;
	@ApiModelProperty(value = "枚举类型,支付宝认证:未审核，审核通过，驳回")
	private AlipayStatus alipayFlay;
	@ApiModelProperty(value = "真房东计划(是否交押金)")
	private Boolean bondFlag;
	@ApiModelProperty(value = "接听状态(免骚扰)")
	private Boolean answerFlag;
	@ApiModelProperty(value = "枚举类型,账号状态:正常、冻结")
	private UserStatus status;
	
	public User() {
		super();
	}

	public User(String id){
		super(id);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public UserSex getSex() {
		return sex;
	}

	public void setSex(UserSex sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getPersonalitySign() {
		return personalitySign;
	}

	public void setPersonalitySign(String personalitySign) {
		this.personalitySign = personalitySign;
	}
	
	public String getPersonas() {
		return personas;
	}

	public void setPersonas(String personas) {
		this.personas = personas;
	}
	
	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}
	
	public RealNameType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(RealNameType businessType) {
		this.businessType = businessType;
	}

	public AlipayZhimaCreditStatus getZhimaCreditFlag() {
		return zhimaCreditFlag;
	}

	public void setZhimaCreditFlag(AlipayZhimaCreditStatus zhimaCreditFlag) {
		this.zhimaCreditFlag = zhimaCreditFlag;
	}

	public RealNameStatus getRealNameFlag() {
		return realNameFlag;
	}

	public void setRealNameFlag(RealNameStatus realNameFlag) {
		this.realNameFlag = realNameFlag;
	}

	public Boolean getBondFlag() {
		return bondFlag;
	}

	public void setBondFlag(Boolean bondFlag) {
		this.bondFlag = bondFlag;
	}
	
	public Boolean getAnswerFlag() {
		return answerFlag;
	}

	public void setAnswerFlag(Boolean answerFlag) {
		this.answerFlag = answerFlag;
	}
	
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public AlipayStatus getAlipayFlay() {
		return alipayFlay;
	}

	public void setAlipayFlay(AlipayStatus alipayFlay) {
		this.alipayFlay = alipayFlay;
	}
	
}