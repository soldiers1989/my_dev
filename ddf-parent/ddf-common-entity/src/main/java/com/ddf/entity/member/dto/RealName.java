package com.ddf.entity.member.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.ddf.entity.base.dto.DataEntity;
import com.ddf.entity.member.eo.RealNameStatus;
import com.ddf.entity.member.eo.RealNameType;

/**
 * real_name Entity
 * @author robot
 * @version 2018-01-10
 */
@ApiModel(description = "RealName")
public class RealName extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户ID")
	private String userId;
	@ApiModelProperty(value = "真实姓名")
	private String realName;
	@ApiModelProperty(value = "身份证")
	private String idCard;
	@ApiModelProperty(value = "正面")
	private String faceImg;
	@ApiModelProperty(value = "背面")
	private String backImg;
	@ApiModelProperty(value = "状态")
	private RealNameStatus status;
	@ApiModelProperty(value = "审核理由")
	private String reviewRemark;
	@ApiModelProperty(value = "类型：个人，企业")
	private RealNameType type;
	
	public RealName() {
		super();
	}

	public RealName(String id){
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
	
	public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}
	
	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}
	
	public RealNameStatus getStatus() {
		return status;
	}

	public void setStatus(RealNameStatus status) {
		this.status = status;
	}
	
	public String getReviewRemark() {
		return reviewRemark;
	}

	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}
	
	public RealNameType getType() {
		return type;
	}

	public void setType(RealNameType type) {
		this.type = type;
	}
	
}