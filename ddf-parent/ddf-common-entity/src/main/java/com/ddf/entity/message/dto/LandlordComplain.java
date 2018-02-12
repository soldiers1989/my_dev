package com.ddf.entity.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.ddf.entity.base.dto.DataEntity;

/**
 * landlord_complain Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "LandlordComplain")
public class LandlordComplain extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房客ID(评论人）")
	private String lodgerId;
	@ApiModelProperty(value = "房东ID（被评论人）")
	private String landlordId;
	@ApiModelProperty(value = "虚假房源，中介，不是本人等字典")
	private String type;
	@ApiModelProperty(value = "图片")
	private String img;
	
	public LandlordComplain() {
		super();
	}

	public LandlordComplain(String id){
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}