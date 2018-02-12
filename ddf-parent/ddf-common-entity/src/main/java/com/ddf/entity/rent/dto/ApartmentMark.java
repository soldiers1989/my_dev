package com.ddf.entity.rent.dto;

import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * apartment_mark Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "ApartmentMark")
public class ApartmentMark extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户id")
	private String userId;
	@ApiModelProperty(value = "房间id")
	private String apartmentId;
	@ApiModelProperty(value = "枚举类型，整租whole_rent，合租share_rent")
	private ApartmentMarkApartmentType apartmentType;
	
	public ApartmentMark() {
		super();
	}

	public ApartmentMark(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}
	
	public ApartmentMarkApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(ApartmentMarkApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}
	
}