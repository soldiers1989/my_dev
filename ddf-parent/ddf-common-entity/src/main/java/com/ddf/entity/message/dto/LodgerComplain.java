package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * lodger_complain Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "LodgerComplain")
public class LodgerComplain extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房客ID（被评论人）")
	private String lodgerId;
	@ApiModelProperty(value = "房东ID（评论人）")
	private String landlordId;
	@ApiModelProperty(value = "中介，无租房需求等枚举")
	private String type;
	@ApiModelProperty(value = "图片")
	private String img;
	
	public LodgerComplain() {
		super();
	}

	public LodgerComplain(String id){
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