package com.ddf.entity.message.dto;

import java.util.Date;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * house_opinion Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "HouseOpinion")
public class HouseOpinion extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房客ID（评论人）")
	private String lodgerId;
	@ApiModelProperty(value = "房东ID")
	private String landlordId;
	@ApiModelProperty(value = "租房ID")
	private String houseId;
	@ApiModelProperty(value = "房源名称")
	private String houseName;
	@ApiModelProperty(value = "看房时间")
	private Date seeHouseDate;
	@ApiModelProperty(value = "星级")
	private Integer houseLevel;
	@ApiModelProperty(value = "房源备注")
	private String houseRemark;
	@ApiModelProperty(value = "房源图片")
	private String houseImg;
	@ApiModelProperty(value = "房源标签")
	private String houseLabel;
	
	public HouseOpinion() {
		super();
	}

	public HouseOpinion(String id){
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
	
	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSeeHouseDate() {
		return seeHouseDate;
	}

	public void setSeeHouseDate(Date seeHouseDate) {
		this.seeHouseDate = seeHouseDate;
	}
	
	public Integer getHouseLevel() {
		return houseLevel;
	}

	public void setHouseLevel(Integer houseLevel) {
		this.houseLevel = houseLevel;
	}
	
	public String getHouseRemark() {
		return houseRemark;
	}

	public void setHouseRemark(String houseRemark) {
		this.houseRemark = houseRemark;
	}
	
	public String getHouseImg() {
		return houseImg;
	}

	public void setHouseImg(String houseImg) {
		this.houseImg = houseImg;
	}
	
	public String getHouseLabel() {
		return houseLabel;
	}

	public void setHouseLabel(String houseLabel) {
		this.houseLabel = houseLabel;
	}
	
}