package com.ddf.entity.rent.dto;

import java.math.BigDecimal;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * rent_demand Entity
 * @author robot
 * @version 2018-02-03
 */
@ApiModel(description = "RentDemand")
public class RentDemand extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "租客ID")
	private String lodgerId;
	@ApiModelProperty(value = "位置ID")
	private String areaIds;
	@ApiModelProperty(value = "小区")
	private String xiaoquIds;
	@ApiModelProperty(value = "地铁线ID")
	private String metroLineIds;
	@ApiModelProperty(value = "地铁站ID")
	private String metorStationIds;
	@ApiModelProperty(value = "最小金额")
	private BigDecimal minAmout;
	@ApiModelProperty(value = "最大金额")
	private BigDecimal maxAmout;
	@ApiModelProperty(value = "户型")
	private String huxing;
	@ApiModelProperty(value = "房源标签")
	private String houseLabelIds;
	@ApiModelProperty(value = "枚举类型，整租whole_rent，合租share_rent")
	private RentDemandApartmentType apartmentType;
	@ApiModelProperty(value = "枚举类型，匹配状态(open,close)")
	private RentDemandMatchStatus matchStatus;
	@ApiModelProperty(value = "隐藏状态(0-正常，1-隐藏)")
	private Boolean hideFlag;
	
	public RentDemand() {
		super();
	}

	public RentDemand(String id){
		super(id);
	}

	public String getLodgerId() {
		return lodgerId;
	}

	public void setLodgerId(String lodgerId) {
		this.lodgerId = lodgerId;
	}
	
	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}
	
	public String getXiaoquIds() {
		return xiaoquIds;
	}

	public void setXiaoquIds(String xiaoquIds) {
		this.xiaoquIds = xiaoquIds;
	}
	
	public String getMetroLineIds() {
		return metroLineIds;
	}

	public void setMetroLineIds(String metroLineIds) {
		this.metroLineIds = metroLineIds;
	}
	
	public String getMetorStationIds() {
		return metorStationIds;
	}

	public void setMetorStationIds(String metorStationIds) {
		this.metorStationIds = metorStationIds;
	}
	
	public BigDecimal getMinAmout() {
		return minAmout;
	}

	public void setMinAmout(BigDecimal minAmout) {
		this.minAmout = minAmout;
	}
	
	public BigDecimal getMaxAmout() {
		return maxAmout;
	}

	public void setMaxAmout(BigDecimal maxAmout) {
		this.maxAmout = maxAmout;
	}
	
	public String getHuxing() {
		return huxing;
	}

	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}
	
	public String getHouseLabelIds() {
		return houseLabelIds;
	}

	public void setHouseLabelIds(String houseLabelIds) {
		this.houseLabelIds = houseLabelIds;
	}
	
	public RentDemandApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(RentDemandApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}
	
	public RentDemandMatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(RentDemandMatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}
	
	public Boolean getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(Boolean hideFlag) {
		this.hideFlag = hideFlag;
	}
	
}