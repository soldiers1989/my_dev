package com.ddf.entity.rent.dto;


import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * rent_param Entity
 * @author robot
 * @version 2018-01-16
 */
@ApiModel(description = "RentParam")
public class RentParam extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "param_name")
	private String paramName;
	@ApiModelProperty(value = "param_value")
	private String paramValue;
	
	public RentParam() {
		super();
	}

	public RentParam(String id){
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