package com.ddf.entity.sms.dto;

import com.ddf.entity.sms.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * short_message Entity
 * @author robot
 * @version 2018-01-30
 */
@ApiModel(description = "ShortMessage")
public class ShortMessage extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	@ApiModelProperty(value = "枚举类型,业务类型")
	private ShortMessageType type;
	@ApiModelProperty(value = "短信内容")
	private String content;
	@ApiModelProperty(value = "短信开关")
	private Boolean switchStatus;
	
	public ShortMessage() {
		super();
	}

	public ShortMessage(String id){
		super(id);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public ShortMessageType getType() {
		return type;
	}

	public void setType(ShortMessageType type) {
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