package com.ddf.entity.member.dto;


import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * bank_card Entity
 * @author robot
 * @version 2018-01-10
 */
@ApiModel(description = "BankCard")
public class BankCard extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户ID")
	private String userId;
	@ApiModelProperty(value = "姓名")
	private String realName;
	@ApiModelProperty(value = "身份证")
	private String idCard;
	@ApiModelProperty(value = "储蓄卡，信用卡")
	private String type;
	@ApiModelProperty(value = "银行卡好")
	private String cardNo;
	@ApiModelProperty(value = "预留手机号")
	private String mobile;
	
	public BankCard() {
		super();
	}

	public BankCard(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}