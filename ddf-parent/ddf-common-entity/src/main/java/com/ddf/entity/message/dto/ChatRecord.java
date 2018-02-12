package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * chat_record Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "ChatRecord")
public class ChatRecord extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房客ID")
	private String lodgerId;
	@ApiModelProperty(value = "房东ID")
	private String landlordId;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "类型")
	private String type;
	
	public ChatRecord() {
		super();
	}

	public ChatRecord(String id){
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}