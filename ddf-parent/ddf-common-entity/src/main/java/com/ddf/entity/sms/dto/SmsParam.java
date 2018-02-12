package com.ddf.entity.sms.dto;

import com.ddf.entity.sms.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sms_param Entity
 * @author robot
 * @version 2018-01-30
 */
@ApiModel(description = "SmsParam")
public class SmsParam extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "参数名")
	private String paramName;
	@ApiModelProperty(value = "参数值")
	private String paramValue;
	
	public SmsParam() {
		super();
	}

	public SmsParam(String id){
		super(id);
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
}