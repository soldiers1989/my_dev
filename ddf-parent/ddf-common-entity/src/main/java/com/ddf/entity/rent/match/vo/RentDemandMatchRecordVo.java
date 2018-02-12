package com.ddf.entity.rent.match.vo;

import com.ddf.entity.rent.eo.RentDemandApartmentType;
import com.ddf.entity.rent.eo.RentDemandMatchStatus;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * rent_demand_match_record EntityVo
 * @author robot
 * @version 2018-01-22
 */
public class RentDemandMatchRecordVo extends RentDemandMatchRecord {
	
	private static final long serialVersionUID = 1L;
	//枚举类型，整租whole_rent，合租share_rent")
	private String rentTypeStr;
	//枚举类型，匹配状态(open,close)")
	private String matchStatusStr;
	//隐藏状态(1-正常，0-隐藏)")
	private String hideFlagStr;
	private String lodger;
	private String pCityStr;
	private String areaIdsStr;
	private Date pubTime;
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setLodger(String lodger) {
		this.lodger = lodger;
	}

	public void setpCityStr(String pCityStr) {
		this.pCityStr = pCityStr;
	}

	public void setAreaIdsStr(String areaIdsStr) {
		this.areaIdsStr = areaIdsStr;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getLodger() {
		return lodger;
	}

	public String getpCityStr() {
		return pCityStr;
	}

	public String getAreaIdsStr() {
		return areaIdsStr;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public String getRentTypeStr() {
		if (super.getRentType() == null) return "";
		return super.getRentType().getExplain();
	}

	public String getMatchStatusStr() {
		if (super.getMatchStatus() == null) return "";
		return super.getMatchStatus().getExplain();
	}

	public String getHideFlagStr() {
		if (super.getHideFlag() == null) return "";
		return super.getHideFlag()? "否":"是";
	}

}