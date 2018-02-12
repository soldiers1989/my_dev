package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * landlord_opinion Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "LandlordOpinion")
public class LandlordOpinion extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房客ID（评论人）")
	private String lodgerId;
	@ApiModelProperty(value = "房东ID（被评论人）")
	private String landlordId;
	@ApiModelProperty(value = "服务等级")
	private Integer careLevel;
	@ApiModelProperty(value = "服务态度内容")
	private String careRemark;
	@ApiModelProperty(value = "服务图片")
	private String careImg;
	@ApiModelProperty(value = "服务标签")
	private String careLabel;
	
	public LandlordOpinion() {
		super();
	}

	public LandlordOpinion(String id){
		super(id);
	}

	public String getLodgerId() {
		return lodgerId;
	}

	public void setLodgerId(String lodgerId) {
		this.lodgerId = lodgerId;
	}
	
	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}
	
	public Integer getCareLevel() {
		return careLevel;
	}

	public void setCareLevel(Integer careLevel) {
		this.careLevel = careLevel;
	}
	
	public String getCareRemark() {
		return careRemark;
	}

	public void setCareRemark(String careRemark) {
		this.careRemark = careRemark;
	}
	
	public String getCareImg() {
		return careImg;
	}

	public void setCareImg(String careImg) {
		this.careImg = careImg;
	}
	
	public String getCareLabel() {
		return careLabel;
	}

	public void setCareLabel(String careLabel) {
		this.careLabel = careLabel;
	}
	
}