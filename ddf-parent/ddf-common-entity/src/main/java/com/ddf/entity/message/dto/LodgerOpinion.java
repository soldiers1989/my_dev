package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * lodger_opinion Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "LodgerOpinion")
public class LodgerOpinion extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房客ID（被评论人）")
	private String lodgerId;
	@ApiModelProperty(value = "房东ID（评论人）")
	private String landlordId;
	@ApiModelProperty(value = "服务等级")
	private Integer lodgerLevel;
	@ApiModelProperty(value = "服务态度内容")
	private String lodgerRemark;
	@ApiModelProperty(value = "图片")
	private String lodgerImg;
	@ApiModelProperty(value = "作息规律，不抽烟等标签")
	private String lodgerLabel;
	
	public LodgerOpinion() {
		super();
	}

	public LodgerOpinion(String id){
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
	
	public Integer getLodgerLevel() {
		return lodgerLevel;
	}

	public void setLodgerLevel(Integer lodgerLevel) {
		this.lodgerLevel = lodgerLevel;
	}
	
	public String getLodgerRemark() {
		return lodgerRemark;
	}

	public void setLodgerRemark(String lodgerRemark) {
		this.lodgerRemark = lodgerRemark;
	}
	
	public String getLodgerImg() {
		return lodgerImg;
	}

	public void setLodgerImg(String lodgerImg) {
		this.lodgerImg = lodgerImg;
	}
	
	public String getLodgerLabel() {
		return lodgerLabel;
	}

	public void setLodgerLabel(String lodgerLabel) {
		this.lodgerLabel = lodgerLabel;
	}
	
}