package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * mobile_message Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "MobileMessage")
public class MobileMessage extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	@ApiModelProperty(value = "业务类型")
	private String type;
	@ApiModelProperty(value = "短信内容")
	private String content;
	@ApiModelProperty(value = "短信开关")
	private Boolean switchStatus;
	
	public MobileMessage() {
		super();
	}

	public MobileMessage(String id){
		super(id);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Boolean getSwitchStatus() {
		return switchStatus;
	}

	public void setSwitchStatus(Boolean switchStatus) {
		this.switchStatus = switchStatus;
	}
	
}