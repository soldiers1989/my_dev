package com.ddf.entity.rent.dto;

import java.math.BigDecimal;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * spider_house Entity
 * @author robot
 * @version 2018-01-19
 */
@ApiModel(description = "SpiderHouse")
public class SpiderHouse extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = " 源网站地址")
	private String resouceUrl;
	@ApiModelProperty(value = "用户id")
	private String provinceId;
	@ApiModelProperty(value = "city_id")
	private String cityId;
	@ApiModelProperty(value = "district_id")
	private String districtId;
	@ApiModelProperty(value = "circle_id")
	private String circleId;
	@ApiModelProperty(value = "xiaoqu_id")
	private String xiaoquId;
	@ApiModelProperty(value = "room")
	private Integer room;
	@ApiModelProperty(value = "amount")
	private BigDecimal amount;
	
	public SpiderHouse() {
		super();
	}

	public SpiderHouse(String id){
		super(id);
	}

	public String getResouceUrl() {
		return resouceUrl;
	}

	public void setResouceUrl(String resouceUrl) {
		this.resouceUrl = resouceUrl;
	}
	
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public String getCircleId() {
		return circleId;
	}

	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	
	public String getXiaoquId() {
		return xiaoquId;
	}

	public void setXiaoquId(String xiaoquId) {
		this.xiaoquId = xiaoquId;
	}
	
	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}